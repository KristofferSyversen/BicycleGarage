package BicycleGarage;

import java.util.ArrayList;

public class DatabaseManager {
	private Database database;

	public DatabaseManager(Database database) {
		this.database = database;
	}
	
	public void addUser(String n, int i, String b){
		if(database.getUser(b).equals(null)){
			User user = new User(n, i, b);
			database.addUser(user);
		} else {
			//felmeddelande "User ID is already in use"
		}
	}
	
	public void removeUser(String b){
		User user = database.getUser(b);
		if(database.hasBicycleOrUser(b)){
			for(Bicycle bicycle: user.getBicycles()){
				database.removeBicycle(bicycle);
			}
			database.removeUser(user);
		} else {
			//Felmeddelande "User doesn't exist"
		}
		
	}
	
	public void addBicycle(User o, String b){
		Bicycle bicycle = new Bicycle(o, b);
		if(!database.bicycleExists(b)){
			database.addBicycle(bicycle);
		} else {
			//Felmeddelande "Bicycle already registerd"
		}
	}
	
	public void removeBicycle(String b){
		if(database.bicycleExists(b)){
			database.removeBicycle(database.getBicycle(b));
		} else {
			//felmeddelande "Bicycle doesn't exist"
		}
	}
	
	public ArrayList<User> getUserList(){
		return database.getUserList();
	}
	
	public ArrayList<Bicycle> getBicycleList(){
		return database.getBicycleList();
	}
	
	public void checkOutBicycle(String b){
		Bicycle bicycle = database.getBicycle(b);
		if(database.isInGarage(bicycle)){
			database.checkOutBicycle(bicycle);
		} else {
			//felmeddelande "Bicycle is not in garage"
		}
	}
	
	public void checkInBicycle(String b){
		Bicycle bicycle = database.getBicycle(b);
		if(!database.isInGarage(bicycle)){
			database.checkInBicycle(bicycle);
		} else {
			//felmeddelande "Bicycle is already in garage"
		}
	}
}
