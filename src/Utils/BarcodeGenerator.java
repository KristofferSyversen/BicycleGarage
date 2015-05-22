package Utils;

import java.util.ArrayList;

public class BarcodeGenerator {
	private static int codeNbr = 0;
	private static StringBuilder barcode;
	private static ArrayList<Integer> takenBarcodes;
	
	/**
	 * Returns a number starting from zero upwards. While the length of the number is
	 * less than 5, zeros will be appended to the end of the number until it has length 5.
	 * @return barcode
	 */
	public static String getCode() {
		
		barcode = new StringBuilder();
		barcode.append(codeNbr);
		barcode.reverse();
		while(barcode.length() < 5){
			barcode.append("0");
		}
		barcode.reverse();
		//while()
		codeNbr+=(codeNbr+1)%100000; //TODO Handle codeNbr growing larger than max
		return barcode.toString();
	}
	
	public static void setCodeNbr(int newNbr){
		codeNbr = newNbr;
	}
	
	public static void setBarcodeAsAvailable(String barcode){
		//TODO
	}
}