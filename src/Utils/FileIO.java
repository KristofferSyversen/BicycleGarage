package Utils;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class FileIO {
	
	public FileIO(String userFile) {
		// TODO Auto-generated constructor stub
	}

	public static String readFromFile(String filePath) { // fileName?
		StringBuilder sb = new StringBuilder();
		try{
			Scanner scan = new Scanner(new File(filePath));
			while(scan.hasNext()){
				sb.append(scan.nextLine() +'\n');
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static boolean writeToFile(String filePath, String content){ // fileName?
		boolean written = false;
		try{
			PrintWriter writer = new PrintWriter(filePath);
			writer.print(content);
			written = true;
		} catch(IOException e){
			e.printStackTrace();
		}
		return written;
	}
}