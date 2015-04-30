package BicycleGarage;

import HardwareInterfaces.BarcodePrinter;
import HardwareInterfaces.ElectronicLock;
import HardwareInterfaces.PinCodeTerminal;

public class BicycleGarageManager {
	private Database db;
	// Pass in logger with constructor as well or use an append function?
	
	/*
	 * 
	 */
	public BicycleGarageManager(Database db) {
		this.db = db;
	}
	
	/*
	 * Register hardware so that BicycleGarageManager knows which drivers to
	 * access.
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		
	}

	/*
	 * Will be called when a user has used the bar code reader at the entry
	 * door. Bicycle ID should be a string of 5 characters, where every
	 * character can be '0', '1',... '9'.
	 */
	public void entryBarcode(String bicycleID) {
		// Implement this based on use cases. Here we can use all the database methods + open doors + blink LEDs.
	}

	/*
	 * Will be called when a user has used the bar code reader at the exit door.
	 * Bicycle ID should be a string of 5 characters, where every character can
	 * be '0', '1',... '9'.
	 */
	public void exitBarcode(String bicycleID) {
		// Implement this based on use cases. We requre the user to scan a bascode twice when checking out a bike. This method will 
		// therefore be called twice, solve this. Here we can use all the database methods + open doors + blink LEDs.
	}
	
	
	// We will never use this!
	/*
	 * Will be called when a user has pressed a key at the keypad at the entry
	 * door. The following characters could be pressed: '0', '1',... '9', '*',
	 * '#'.
	 */
	public void entryCharacter(char c) {
		
	}
	// ***********************
}