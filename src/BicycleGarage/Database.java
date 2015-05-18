package BicycleGarage;

import java.util.ArrayList;

import Utils.BarcodeGenerator;
import Utils.FileIO;

/**
 * 
 * @author kristoffer
 *
 */
public class Database {
	/*
	 *  We have to decide how we want to store user/bike data, we can do eigther of the following:
	 *  	* Let each user have a list of bikes.
	 *  	* Have two lists, one for bikes and one for users and let the users contain references into 
	 *  	  the bicycles list(and vice versa maybe?).
	 *  	* Another idea?
	 *  
	 *  We need a way to know which bikes are in the garage, we can eigher use boolean tags in the bike isntances or maybe a list 
	 *  in Database containing references to bikes in the bicycles list.
	 */
	private ArrayList<User> users; // Or some other container class.
	private ArrayList<Bicycle> bicycles; //
	private ArrayList<Bicycle> bicyclesInGarage;

	
	/**
	 * 
	 * @param userFile
	 */
	public Database(String userFile) {
		// Parse the user file and place the user data where it belongs.
		FileIO reader = new FileIO(userFile);
		// Initialize the data containers.
		users = new ArrayList<User>();
		bicycles = new ArrayList<Bicycle>();
		bicyclesInGarage = new ArrayList<Bicycle>();
	}
	
	/*
	 * Examples of methods to implement:
	 
	    + addUser(User)
		+ removeUser(User)
		+ addBicycle(Bicycle)
		+ removeBicycle(Bicycle)
		+ checkOutBicycle(Bicycle)
		+ checkInBicycle(Bicycle)
		+ getUserList()
		+ getBikeList()
	 */
	
	/**
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		if(!users.contains(user)){
			users.add(user);
		}
	}
	
	/**
	 * 
	 * @param bicycle
	 */
	public void addBicycle(Bicycle bicycle) {
		if(!bicycles.contains(bicycle)){
			bicycles.add(bicycle);
		}
	}
	
	/**
	 * 
	 * @param bicycle
	 */
	public void checkInBicycle(Bicycle bicycle) {
		if(!bicyclesInGarage.contains(bicycle)) {
			bicyclesInGarage.add(bicycle);
		}
	}
	
	public void checkOutBicycle(Bicycle bicycle) {
		bicyclesInGarage.remove(bicycle);
	}
	
	/**
	 * Generates a unique barcode.
	 */
	public String generateBarcode() {
		// Generate a barcode -> check if it is "free" -> return it.
		return BarcodeGenerator.getCode(); 
	}

	public boolean userExists(String barcode) {
		return false;
	}

	public User getUser(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean inDatabase(String barcode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean bicycleExists(String barcode) {
		// TODO Auto-generated method stub
		return false;
	}

	public Bicycle getBicycle(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public boolean isInGarage(Bicycle bicycle) {
		// TODO Auto-generated method stub
		return false;
	}
}
