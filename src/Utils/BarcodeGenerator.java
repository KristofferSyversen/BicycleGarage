package Utils;

public class BarcodeGenerator {
	private static int codeNbr = 0;
	private static StringBuilder barcode;
	
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
}