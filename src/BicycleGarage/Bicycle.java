package BicycleGarage;

public class Bicycle {
	private String barcode;

	public Bicycle(String b) {
		barcode = b;
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
