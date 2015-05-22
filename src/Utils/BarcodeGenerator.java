package Utils;

import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;

public class BarcodeGenerator {
	private static int codeNbr = 0;
	private static StringBuilder barcode;
	private static ArrayList<Integer> takenBarcodes = new ArrayList<Integer>();

	/**
	 * Returns a number starting from zero up until 99999. While the length of
	 * the number is less than 5, zeros will be appended to the beginning of the
	 * number until it has length 5.
	 * 
	 * @return barcode
	 */
	public static String getCode() {
		barcode = new StringBuilder();
		barcode.append(codeNbr);
		barcode.reverse();
		while (barcode.length() < 5) {
			barcode.append("0");
		}
		barcode.reverse();
		takenBarcodes.add(codeNbr);
		int i = 0;
		while (takenBarcodes.contains(codeNbr)) {
			codeNbr += (codeNbr + 1) % 100000;
			i++;
			if (i > 100009) {
				throw new RejectedExecutionException("No available barcodes!");
			}
		}
		return barcode.toString();
	}

	public static void setCodeNbr(int newNbr) {
		codeNbr = newNbr;
	}

	public static void setBarcodeAsAvailable(String barcode) {
		int availableNbr = Integer.parseInt(barcode);
		if (takenBarcodes.contains(availableNbr)) {
			takenBarcodes.remove((Integer) availableNbr);
		} else {
			System.out
					.println("Warning: tried to set barcode as available which was already available");
		}
	}
}