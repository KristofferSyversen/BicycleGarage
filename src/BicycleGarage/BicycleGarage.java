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
import Utils.Constants;
import Utils.FileIO;
import Utils.Logger;

/**
 * Main class for Bicycle Garage project.
 * @author kristoffer Note to self; maybe this should be be the gui?
 */
public class BicycleGarage {

	// Hardware
	private BicycleGarageManager bgm;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal terminal;

	private Database database;
	private Logger logger;

	private GUI gui;

	// Get these values from a config file instead.
	private String logFile = "logFile.log";

	/**
	 * Constructor for BicycleGarage.
	 */
	public BicycleGarage() {

		// Get filenames from config file instead.

		logger = new Logger(logFile);
		database = new Database(Constants.DATABASE_FILE_NAME);

		// Register hardware.
		bgm = new BicycleGarageManager(database);

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
	 * Starts the operator GUI.
	 */
	public void startOperatorGUI() {
		gui = new GUI(database, bgm);
	}

	public static void main(String[] args) {
		BicycleGarage bg = new 		BicycleGarage();

		bg.startOperatorGUI();
	}
}