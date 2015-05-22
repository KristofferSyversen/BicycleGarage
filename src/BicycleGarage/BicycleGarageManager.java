package BicycleGarage;

import java.util.Timer;
import java.util.TimerTask;

import HardwareInterfaces.BarcodePrinter;
import HardwareInterfaces.ElectronicLock;
import HardwareInterfaces.PinCodeTerminal;
import Utils.Constants;
import Utils.Logger;

public class BicycleGarageManager {
	private Database database;
	private Logger logger;

	private BarcodePrinter barcodePrinter;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal pinCodeTerminal; // Colour : (static) RED_LED = 0,
												// GREEN_LED = 1;
	
	private Timer timer;
	private boolean firstBarcodeScanned = false;
	private String firstBarcode = "empty";


	
	/**
	 * 
	 */
	public BicycleGarageManager(Database db, Logger logger) {
		this.database = db;
		this.logger = logger;
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
	 * Will be called when a user has used the bar code reader at the entry
	 * door. Bicycle ID should be a string of 5 characters, where every
	 * character can be '0', '1',... '9'.
	 */
	public void entryBarcode(String barcode) {
		// Implement this based on use cases. Here we can use all the database
		// methods + open doors + blink LEDs.

		logger.log("EntryScanner scanned : " + barcode + ".");

		if (database.hasBicycleOrUser(barcode)) { // Use case 5.1.1/5.1.2
			
			
			Bicycle bicycle = database.getBicycle(barcode);
			if(bicycle == null) {
				logger.log("Checking in bicycle: " + barcode);
				database.checkInBicycle(bicycle);
				
				logger.log("Unlocking entry door lock for registered barcode: " + barcode + ".");
				entryLock.open(Constants.UNLOCK_TIME);
			} else {
				logger.log("Bicycle: " + barcode + " not found.");
			}
		
			
		} else {
			// Barcode not registered to either a bicycle or a user.
			logger.log("Unknown barcode scanned @ entry: " + barcode + ".");
			pinCodeTerminal.lightLED(PinCodeTerminal.RED_LED,Constants.RED_LED_TIME); // Light RED_LED for 3 seconds according to spec.
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
		
		//TODO Divide into private methods to make it more readable
		
		logger.log("ExitScanner scanned : " + barcode + ".");
		
		if (/*remove"!"*/!database.hasBicycleOrUser(barcode)) { // Use case 5.1.1/5.1.2 // Remove ! to fix logic.
			if(firstBarcodeScanned) { // The first barcode is already scanned and stored.
				logger.log("Secound barcode scanned: " + barcode);
				
				Bicycle bicycle = null;
				User user = null;
				
				if(database.bicycleExists(barcode)) {
					bicycle = database.getBicycle(barcode);
				} else if(database.bicycleExists(firstBarcode)) {
					bicycle = database.getBicycle(barcode);
				} else {
					logger.log("Barcode error bicycle.");
					System.exit(0); // meh...
				}
				
				if(database.userExists(barcode)) {
					user = database.getUser(barcode);
				} else if(database.userExists(firstBarcode)) {
					user = database.getUser(firstBarcode);
				} else {
					logger.log("Barcode error user.");
					System.exit(0); // meh...
				}
				
				if(user.ownsBicycle(bicycle)) {
					if(database.isInGarage(bicycle)) {
						logger.log("Checking out bicycle.");
						
						exitLock.open(Constants.UNLOCK_TIME);
						database.checkOutBicycle(bicycle);
					} else {
						logger.log("Bicycle not in garage database.");
					}
					
				} else {
					logger.log("User does not own the specified bicycle!");
				}
				
				
			} else { // First time a barcode is scanned, use this branch to set a timer and store the barcode.
				
				firstBarcode = barcode;
				firstBarcodeScanned = true;
				
				logger.log("Barcode timer started, reseting in " + Constants.TIME_BETWEEN_BARCODES + " seconds.");
				
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  firstBarcodeScanned = false;
						  firstBarcode = "empty";
						  
						  logger.log("Barcode timer stopping, resetting first barcode.");
					  }
					}, Constants.TIME_BETWEEN_BARCODES*1000);
			}
		} else {
			// Barcode not registered to either a bicycle or a user.
			logger.log("Unknown barcode scanned @ exit: " + barcode + ".");
		}
		
		
	}

	// We will never use this!
	/**
	 * Will be called when a user has pressed a key at the keypad at the entry
	 * door. The following characters could be pressed: '0', '1',... '9', '*',
	 * '#'.
	 */
	public void entryCharacter(char c) {

	}
	// ***********************
}
