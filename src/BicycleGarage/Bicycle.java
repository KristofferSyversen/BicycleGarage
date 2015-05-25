package BicycleGarage;


/**
 * A class representing a bicycle.
 * @author kristoffer
 */
public class Bicycle {
	private String barcode;

	/**
	 * Constructor for Bicycle class.
	 * @param barcode
	 */
	public Bicycle(String b) {
		barcode = b;
	}
	
	/**
	 * Returns the bicycle barcode.
	 * @return - The bicycle barcode.
	 */
	public String getBarcode(){
		return barcode;
	}

	
	/**
	 * Defines the behavior of a compare to compare the barcode.
	 * @param barcode
	 * @return - true if equals, false if different.
	 */
	public boolean equals(Bicycle b){
		return this.barcode.equals(b.barcode);
	}
	
	
	/**
	 * To string override. Returns the bicycle barcode.
	 * @return - String containing the bicycle barcode.
	 */
	public String toString() {
		return "Bicycle #" + barcode;
	}
}
