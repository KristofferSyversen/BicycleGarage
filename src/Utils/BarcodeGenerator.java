package Utils;

public class BarcodeGenerator {
	private static int codeNbr = 0;
	private static StringBuilder barcode;
	
	/**
	 * Returns a number starting from zero upwards. While the length of the number is
	 * less than 5, zeros will be appended to the end of the number until it has length 5.
	 * @return barcode
	 */
	public static String getCode() {
		
		if(codeNbr>99999){
			//ERROR
		}
		barcode = new StringBuilder();
		barcode.append(codeNbr);
		while(barcode.length() < 5){
			barcode.append("0");
		}
		codeNbr++; //TODO Handle codeNbr growing larger than max
		return barcode.toString();
	}
	
	public static void setCodeNbr(int newNbr){
		codeNbr = newNbr;
	}
}