package Utils;

import java.io.File;

public class Logger {
	private File logFile;
	
	public Logger(String logFile) {
		this.logFile = new File(logFile);
	}

	public void log(String msg) {
		// Output message to file with format "date - msg".
		
	}
	
}
