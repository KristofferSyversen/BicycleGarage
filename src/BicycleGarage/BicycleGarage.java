package BicycleGarage;

import HardwareInterfaces.BarcodePrinter;
import HardwareInterfaces.BarcodeReader;
import HardwareInterfaces.ElectronicLock;
import HardwareInterfaces.PinCodeTerminal;
import HardwareSimulators.BarcodePrinterTestDriver;
import HardwareSimulators.BarcodeReaderEntryTestDriver;
import HardwareSimulators.BarcodeReaderExitTestDriver;
import HardwareSimulators.ElectronicLockTestDriver;
import HardwareSimulators.PinCodeTerminalTestDriver;
import Utils.FileIO;
import Utils.Logger;

/**
 * 
 * @author kristoffer
 * Note to self; maybe this should be be the gui?
 */
public class BicycleGarage {
	
	// Hardware
	private BicycleGarageManager manager;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal terminal;
	
	private Database database;
	private Logger logger;

	// Get these values from a config file instead.
	private String logFile = "logFile.log";
	private String databaseFile = "databaseFile.udb"; // ".User DataBase" file?
	
	/**
	 * 
	 */
	public BicycleGarage() {
		
		// Get filenames from config file instead.
		
		logger = new Logger(logFile);
		
		// Load the bicycle and user files into the database.
		try {
			database = new Database(FileIO.readFromFile(databaseFile));
		} catch(Exception e) { //TODO apply more specific exception
			logger.log("Unable to open database file!\n" + e);
		}
		
		// Register hardware.
		manager = new BicycleGarageManager(database, logger);
		
		entryLock = new ElectronicLockTestDriver("Entry lock");
		exitLock = new ElectronicLockTestDriver("Exit lock");
		
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		terminal = new PinCodeTerminalTestDriver();
		
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(manager);
		
		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		readerEntry.register(manager);
		readerExit.register(manager);
	}
	
	/**
	 * 
	 */
	public void startOperatorGUI() {
		// Start the operator GUI which will use the database.
		
		// Here we can use all the public database methods + blink the LED and open the door.
	}
	
	public void exit() {
		// Store all the data and clean up.
	}

	public static void main(String[] args) {
		BicycleGarage bg = new BicycleGarage();
		
		bg.startOperatorGUI();
		bg.exit();
	}
}