package BicycleGarage;

import java.util.Timer;
import java.util.TimerTask;

import HardwareInterfaces.BarcodePrinter;
import HardwareInterfaces.ElectronicLock;
import HardwareInterfaces.PinCodeTerminal;
import Utils.Constants;

/**
 * Class responsible for handling the hardware interfaces.
 * @author kristoffer
 *
 */
public class BicycleGarageManager {
	private Database database;

	private BarcodePrinter barcodePrinter;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal pinCodeTerminal; // Colour : (static) RED_LED = 0,
												// GREEN_LED = 1;
	
	private Timer timer;
	private boolean firstBarcodeScanned = false;
	private String firstBarcode = "empty";


	
	/**
	 * Constructor for BicycleGarage.
	 */
	public BicycleGarageManager(Database db) {
		this.database = db;
		this.timer = new Timer();
	}

	/**
	 * Register hardware so that BicycleGarageManager knows which drivers to
	 * access.
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		this.barcodePrinter = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.pinCodeTerminal = terminal;
	}
	
	/**
	 * Prints given barcode.
	 * @param barcode
	 */
	public void printBarcode(String barcode) {
		barcodePrinter.printBarcode(barcode);
	}

	/**
	 * Will be called when a user has used the bar code reader at the entry
	 * door. Bicycle ID should be a string of 5 characters, where every
	 * character can be '0', '1',... '9'.
	 */
	public void entryBarcode(String barcode) {
		// Implement this based on use cases. Here we can use all the database
		// methods + open doors + blink LEDs.

		
		if (database.hasBicycleOrUser(barcode)) { // Use case 5.1.1/5.1.2
			try{
				database.checkInBicycle(barcode);
			} catch (IllegalArgumentException e){
				//Bicycle already registered to be in garage or barcode is a user
				//do nothing
			}
			entryLock.open(Constants.UNLOCK_TIME);
		} else {
			pinCodeTerminal.lightLED(PinCodeTerminal.RED_LED,Constants.RED_LED_TIME); 
		}
	}
	private Bicycle findBicycle(String barcode1, String barcode2){
		if(database.bicycleExists(barcode1)) {
			return database.getBicycle(barcode1);
		} else if(database.bicycleExists(barcode2)) {
			return database.getBicycle(barcode2);
		} else {
			return null;
		}
	}
	
	private User findUser(String barcode1, String barcode2){
		if(database.userExists(barcode1)) {
			return database.getUser(barcode1);
		} else if(database.userExists(barcode2)) {
			return database.getUser(barcode2);
		} else {
			return null;
		}
	}
	/**
	 * Will be called when a user has used the bar code reader at the exit door.
	 * Bicycle ID should be a string of 5 characters, where every character can
	 * be '0', '1',... '9'.
	 */
	public void exitBarcode(String barcode) {
		// Implement this based on use cases. We require the user to scan a
		// barcode twice when checking out a bike. This method will
		// therefore be called twice, solve this. Here we can use all the
		// database methods + open doors + blink LEDs.
		
		if (database.hasBicycleOrUser(barcode)) { // Use case 5.1.1/5.1.2 
			if(firstBarcodeScanned) { // The first barcode is already scanned and stored.
				
				Bicycle bicycle = null;
				User user = null;
				
				bicycle = findBicycle(barcode, firstBarcode);
				user = findUser(barcode, firstBarcode);
				
				if(bicycle == null||user == null){
				}
				else if(user.ownsBicycle(bicycle)) {
					if(database.isInGarage(bicycle.getBarcode())) {
						
						exitLock.open(Constants.UNLOCK_TIME);
						database.checkOutBicycle(bicycle.getBarcode());
					} else {
					}
					
				} else {
				}
				firstBarcodeScanned = false;
				firstBarcode = "empty";
				
				//timer.cancel();
				
			} else { // First time a barcode is scanned, use this branch to set a timer and store the barcode.
				
				firstBarcode = barcode;
				firstBarcodeScanned = true;
				System.out.println("timer started");
				
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  firstBarcodeScanned = false;
						  firstBarcode = "empty";
						  
					  }
					}, Constants.TIME_BETWEEN_BARCODES*1000);
			}
		} else {
			// Barcode not registered to either a bicycle or a user.
		}
		
		
	}
	
	
	/**
	 * Unlock the entry door for time seconds.
	 * @param time
	 */
	public void unlockEntryDoor(int time) {
		entryLock.open(time);
	}
	
	/**
	 * Unlock exit door for time seconds.
	 * @param time
	 */
	public void unlockExitDoor(int time) {
		exitLock.open(time);
	}

	/**
	 * Will be called when a user has pressed a key at the keypad at the entry
	 * door. The following characters could be pressed: '0', '1',... '9', '*',
	 * '#'.
	 */
	public void entryCharacter(char c) {

	}
}
