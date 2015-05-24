package BicycleGarage;

import java.util.ArrayList;

public class User implements Comparable{
	private String name;
	private int id;
	private String barcode;
	private ArrayList<Bicycle> bicycles;

	public User(String n, int i, String b) {
		name = n;
		id = i;
		barcode = b;
		bicycles = new ArrayList<Bicycle>();
	}

	public void addBicycle(Bicycle b) {
		bicycles.add(b);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getBarcode() {
		return barcode;
	}

	public boolean ownsBicycle(Bicycle bicycle) {
		for (Bicycle b : bicycles) {
			if (b.equals(bicycle)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Bicycle> getBicycles() {
		return bicycles;
	}

	public boolean removeBicycle(Bicycle b){
		return bicycles.remove(b);
	}
	
	//Not consistent with compareTo method
	public boolean equals(User u) {
		return this.barcode.equals(u.getBarcode());
	}
	
	//Not consistent with equals method
	public int compareTo(Object user){
		if(user instanceof User){
			User u = (User) user;
			return this.name.compareTo(u.name);
		}
		throw new IllegalArgumentException("Users can only be compared to users");
	}
	
	public String toString() {
		return name + " id: " + id + " barcode number: " + barcode + " #bikes: " + bicycles.size() ;
	}
}
