package BicycleGarage;

import java.util.ArrayList;

public class User {
	private String name;
	private int id;
	private String barcode;
	private ArrayList<Bicycle> bikes;

	public User(String n, int i, String b){
		name = n;
		id = i;
		barcode = b;
		bikes = new ArrayList<Bicycle>();
	}
	
	public void addBicycle(Bicycle b){
		bikes.add(b);
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getBarcode(){
		return barcode;
	}
	
	public boolean ownsBicycle(Bicycle bicycle) {
		for(Bicycle b: bikes){
			if(b.equals(bicycle)){
				return true;
			}
		}
		return false;
	}
}
