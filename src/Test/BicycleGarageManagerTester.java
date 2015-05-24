package Test;

import static org.junit.Assert.*;

import java.util.Timer;

import org.junit.Test;
import org.junit.Before;

import HardwareInterfaces.BarcodePrinter;
import HardwareInterfaces.ElectronicLock;
import HardwareInterfaces.PinCodeTerminal;
import HardwareSimulators.BarcodePrinterTestDriver;
import HardwareSimulators.ElectronicLockTestDriver;
import HardwareSimulators.PinCodeTerminalTestDriver;
import Utils.Constants;
import Utils.Logger;
import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;


public class BicycleGarageManagerTester {
	
	private String logFile = "logFile.log";
	private Database database;
	private Logger logger;
	
	private BarcodePrinter barcodePrinter;
	private ElectronicLock entryLock;
	private ElectronicLock exitLock;
	private PinCodeTerminal pinCodeTerminal;
	
	private BicycleGarageManager bgm;
	
	private Timer timer;
	private boolean firstBarcodeScanned = false;
	private String firstBarcode = "empty";
	
	@Before
	public void setup(){
		this.database = new Database();
		this.logger = new Logger(logFile);
		this.timer = new Timer();
		this.bgm = new BicycleGarageManager(database, logger);
		
		this.entryLock = new ElectronicLockTestDriver("Entry lock");
		this.exitLock = new ElectronicLockTestDriver("Exit lock");
		this.barcodePrinter = new BarcodePrinterTestDriver();
		this.pinCodeTerminal = new PinCodeTerminalTestDriver();
	}
	@Test
	public void testPrintBarcode() {
	}

}
