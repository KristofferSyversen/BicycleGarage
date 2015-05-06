package BicycleGarage;

public class BarcodeGenerator { //TODO Check how many numbers need to be returned
	private static int codeNbr = 0;
	private static StringBuilder barcode;
	
	public static String getCode() {
		barcode = new StringBuilder();
		barcode.append(codeNbr);
		while(barcode.length() < 6){
			barcode.append("0");
		}
		codeNbr++; //TODO Handle codeNbr growing larger than max
		return barcode.toString();
	}
}
