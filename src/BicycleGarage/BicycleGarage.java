package BicycleGarage;

import GUI.GUI;
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
 * @author kristoffer Note to self; maybe this should be be the gui?
 */
public class BicycleGarage {

	// Hardware
	private BicycleGarageManager bgm;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal terminal;

	private DatabaseManager dbm;
	private Database database;
	private Logger logger;

	private GUI gui;

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

		
		//TODO: CHANGE THIS TO THE PARSED INPUT< THIS IS ONLY FOR TESTING.
		database = Database.getGenericDatabase(); //new Database(databaseFile);

		dbm = new DatabaseManager(database);

		// Register hardware.
		bgm = new BicycleGarageManager(database, logger);

		entryLock = new ElectronicLockTestDriver("Entry lock");
		exitLock = new ElectronicLockTestDriver("Exit lock");

		BarcodePrinter printer = new BarcodePrinterTestDriver();
		terminal = new PinCodeTerminalTestDriver();

		bgm.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(bgm);

		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		readerEntry.register(bgm);
		readerExit.register(bgm);
	}

	/**
	 * 
	 */
	public void startOperatorGUI() {
		// Start the operator GUI which will use the database.

		

		gui = new GUI(dbm, bgm);

	}

	public void exit() {
		// Store all the data and clean up.
	}

	public static void main(String[] args) {
		BicycleGarage bg = new 		BicycleGarage();

		bg.startOperatorGUI();
		bg.exit();
	}
}