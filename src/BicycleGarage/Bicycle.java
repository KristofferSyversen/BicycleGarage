package BicycleGarage;

public class Bicycle {
	private User owner;
	private String barcode;
	// More...
	public Bicycle(User o, String b) {
		owner = o;
		barcode = b;
	}
	
	public User getOwner(){
		return owner;
	}
	
	public String getBarcode(){
		return barcode;
	}
}
