package BicycleGarage;

import java.util.ArrayList;

public class User {
	private String name;
	private int id;
	private String barcode;
	private ArrayList<Bicycle> bicycles;

	public User(String n, int i, String b){
		name = n;
		id = i;
		barcode = b;
		bicycles = new ArrayList<Bicycle>();
	}
	
	public void addBicycle(Bicycle b){
		bicycles.add(b);
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
		for(Bicycle b: bicycles){
			if(b.equals(bicycle)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Bicycle> getBicycles(){
		return bicycles;
	}
	
	public boolean equals(User u){
		return this.barcode.equals(u.barcode);
	}
	
	public String toString() {
		return name + " #bikes: " + bicycles.size();
	}
}
