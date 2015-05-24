package BicycleGarage;

import java.util.ArrayList;

public class DatabaseManager {
	private Database database;

	public DatabaseManager(Database database) {
		this.database = database;
	}
	
	public void addUser(User user) throws Exception{		
		if(!database.addUser(user)){
			throw new Exception("User id is already in use!");
		}
	}

	public void removeUser(String barcode) throws Exception {
		User user = database.getUser(barcode);
		if (database.userExists(barcode)) {
			for (Bicycle bicycle : user.getBicycles()) {
				database.removeBicycle(bicycle);
			}
			database.removeUser(user);
		} else {
			throw new Exception("User doesn't exist");
		}

	}

	public void addBicycle(Bicycle bicycle) throws Exception {
		for (Bicycle b : database.getBicycleList()) {
			if (bicycle.getBarcode().equals(b)) {
				throw new Exception("Bicycle barcode is already in use");
			}
		}
		database.addBicycle(bicycle);
	}

	public void removeBicycle(String b) throws Exception {
		if (database.bicycleExists(b)) {
			database.removeBicycle(database.getBicycle(b));
		} else {
			throw new Exception("Bicycle doesn't exist");
		}
	}

	
	// TODO: something is wrong with the comparison here!!
	public void checkOutBicycle(String b) throws Exception {
		Bicycle bicycle = database.getBicycle(b);
		if (database.isInGarage(bicycle)) {
			database.checkOutBicycle(bicycle);
		} else {
			throw new Exception("Bicycle is not in garage");
		}
	}

	
	// TODO: something is wrong with the comparison here!!
	public void checkInBicycle(String b) throws Exception {
		Bicycle bicycle = database.getBicycle(b);
		if (!database.isInGarage(bicycle)) {
			database.checkInBicycle(bicycle);
		} else {
			throw new Exception("Bicycle is already in garage");
		}
	}

	public ArrayList<User> getUserList() {
		return database.getUserList();
	}

	public ArrayList<Bicycle> getBicycleList() {
		return database.getBicycleList();
	}

	public User getUser(String barcode){
		return database.getUser(barcode);
	}

	public ArrayList<Bicycle> getBicycleInGarageList() {
		return database.getBicyclesInGarageList();
	}
}
