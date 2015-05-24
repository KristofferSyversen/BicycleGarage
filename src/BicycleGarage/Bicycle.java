package BicycleGarage;

public class Bicycle {
	private User owner;
	private String barcode;

	public Bicycle(String b) {
		barcode = b;
	}
	
	public User getOwner(){
		return owner;
	}
	
	public String getBarcode(){
		return barcode;
	}
	
	public boolean equals(Bicycle b){
		return this.barcode.equals(b.barcode);
	}
	
	public String toString() {
		return "Bicycle #" + barcode;
	}
}
