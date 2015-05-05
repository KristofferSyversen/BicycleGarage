package Utils;

import java.io.File;
import java.util.Date;

public class Logger {
	private File logFile;
	private Date time;
	
	public Logger(String logFile) {
		this.logFile = new File(logFile);
		time = new Date();
	}

	public void log(String msg) {
		// Output message to file with format "date - msg".
		System.out.println(time.toString() + " : " + msg);
	}
	
}
