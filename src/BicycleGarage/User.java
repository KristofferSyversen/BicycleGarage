package BicycleGarage;

import java.util.ArrayList;


/**
 * Defines a user.
 * @author kristoffer
 *
 */
public class User implements Comparable{
	private String name;
	private int id;
	private String barcode;
	private ArrayList<Bicycle> bicycles;

	
	/**
	 * Constructor for User class.
	 * @param name
	 * @param id
	 * @param barcode
	 */
	public User(String n, int i, String b) {
		name = n;
		id = i;
		barcode = b;
		bicycles = new ArrayList<Bicycle>();
	}

	/**
	 * Adds a bicycle to the user.
	 * @param barcode
	 */
	public void addBicycle(Bicycle b) {
		bicycles.add(b);
	}

	/**
	 * Returns the name of the user.
	 * @return String - The name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the social security number of the user.
	 * @return String
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the barcode for the user.
	 * @return String
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Checks if the current user owns the given bike.
	 * @param bicycle
	 * @return True if user owns bike, else false.
	 */
	public boolean ownsBicycle(Bicycle bicycle) {
		for (Bicycle b : bicycles) {
			if (b.equals(bicycle)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the list of bicycles.
	 * @return ArrayList<Bicycle>
	 */
	public ArrayList<Bicycle> getBicycles() {
		return bicycles;
	}

	/**
	 * Removes the given bicycle.
	 * @param bicycle
	 * @return True if bicycle remove correctly, false otherwise.
	 */
	public boolean removeBicycle(Bicycle b){
		return bicycles.remove(b);
	}
	
	/**
	 * Defines equals behavior of user, compare to barcode.
	 * @param user
	 * @return True if equals, false otherwise.
	 */
	public boolean equals(User u) {
		return this.barcode.equals(u.getBarcode());
	}
	
	/**
	 * Compares one user with another.
	 * @return -1 if <, 0 if =, 1 if >
	 */
	public int compareTo(Object user){
		if(user instanceof User){
			User u = (User) user;
			return this.name.compareTo(u.name);
		}
		throw new IllegalArgumentException("Users can only be compared to users");
	}
	
	/**
	 * Returns a representation of the user.
	 * @return
	 */
	public String toString() {
		return name + " id: " + id + " barcode number: " + barcode + " #bikes: " + bicycles.size() ;
	}
}
