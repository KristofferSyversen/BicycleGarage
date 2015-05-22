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
	 * Constructor used primarily for testing
	 * @param users
	 * @param bicycles
	 * @param bicyclesInGarage
	 */
	public Database(ArrayList<User> users, ArrayList<Bicycle> bicycles, ArrayList<Bicycle> bicyclesInGarage){
		this.users = users;
		this.bicycles = bicycles;
		this.bicyclesInGarage = bicyclesInGarage;
	}
	
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
	
	public Database(){
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
	public boolean addUser(User user) {
		if(!users.contains(user)){
			users.add(user);
			return true;
		}
		return false;
	}
	
	public boolean removeUser(User user){
		return users.remove(user);
	}
	
	/**
	 * 
	 * @param bicycle
	 */
	public boolean addBicycle(Bicycle bicycle) {
		if(!bicycles.contains(bicycle)){
			bicycles.add(bicycle);
			return true;
		}
		return false;
	}
	
	public boolean removeBicycle(Bicycle bicycle){
		return bicycles.remove(bicycle);
	}
	
	/**
	 * 
	 * @param bicycle
	 */
	public boolean checkInBicycle(Bicycle bicycle) {
		if(!bicyclesInGarage.contains(bicycle)) {
			bicyclesInGarage.add(bicycle);
			return true;
		}
		return false;
	}
	
	public boolean checkOutBicycle(Bicycle bicycle) {
		return bicyclesInGarage.remove(bicycle);
	}
	
	/**
	 * Generates a unique barcode.
	 */
	public String generateBarcode() {
		//TODO handle static variable overflow in BarcodeGenerator
		return BarcodeGenerator.getCode(); 
	}

	public boolean userExists(String barcode) {
		for(User u: users){
			if(u.getBarcode().equals(barcode)){
				return true;
			}
		}
		return false;
	}

	public User getUser(String barcode) {
		for(User u: users){
			if(u.getBarcode().equals(barcode)){
				return u;
			}
		}
		return null;
	}

	public boolean hasBicycleOrUser(String barcode) {
		if(userExists(barcode)||bicycleExists(barcode)){
			return true;
		}
		return false;
	}

	public boolean bicycleExists(String barcode) {
		for(Bicycle b: bicycles){
			if(b.getBarcode().equals(barcode)){
				return true;
			}
		}
		return false;
	}

	public Bicycle getBicycle(String barcode) {
		for(Bicycle b: bicycles){
			if(b.getBarcode().equals(barcode)){
				return b;
			}
		}
		return null;
	}

	public boolean isInGarage(Bicycle bicycle) { //TODO rewrite argument as String
		for(Bicycle b: bicycles){
			if(b.getBarcode().equals(bicycle)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<User> getUserList(){
		return users;
	}
	
	public ArrayList<Bicycle> getBicycleList(){
		return bicycles;
	}
}
