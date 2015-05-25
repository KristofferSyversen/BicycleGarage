package Utils;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {
	
	/**
	 * Reads the file with the name filePath.
	 * @return the content of the file as a String
	 */
	public static String readFromFile(String filePath) { // fileName?
		StringBuilder sb = new StringBuilder();
		try{
			Scanner scan = new Scanner(new File(filePath));
			while(scan.hasNext()){
				sb.append(scan.nextLine() +'\n');
			}
			scan.close();
		} catch (FileNotFoundException e) {

		}
		return sb.toString();
	}
	
	
	/**
	 * Writes the String to a file with the name filePath
	 * @param filePath Name of file
	 * @param content Content to be written to the file
	 * @return true if it could write to the file.
	 */
	public static boolean writeToFile(String filePath, String content){ // fileName?
		boolean written = false;
		try{
			PrintWriter writer = new PrintWriter(new File(filePath));
			writer.print(content);
			written = true;
			writer.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		return written;
	}
}