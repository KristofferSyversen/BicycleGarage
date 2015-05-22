package BicycleGarage;

import java.util.ArrayList;

public class DatabaseManager {
	private Database database;

	public DatabaseManager(Database database) {
		this.database = database;
	}
	
	public void addUser(String n, int i, String b) throws Exception {
		for (User u : database.getUserList()) {
			if (u.getBarcode().equals(b)) {
				throw new Exception("User barcode is already in use");
			}
		}
		User user = new User(n, i, b);
		database.addUser(user);
	}

	public void removeUser(String b) throws Exception {
		User user = database.getUser(b);
		if (database.userExists(b)) {
			for (Bicycle bicycle : user.getBicycles()) {
				database.removeBicycle(bicycle);
			}
			database.removeUser(user);
		} else {
			throw new Exception("User doesn't exist");
		}

	}

	public void addBicycle(User o, String b) throws Exception {
		for (Bicycle bicycle : database.getBicycleList()) {
			if (bicycle.getBarcode().equals(b)) {
				throw new Exception("Bicycle barcode is already in use");
			}
		}
		Bicycle bicycle = new Bicycle(o, b);
		database.addBicycle(bicycle);
	}

	public void removeBicycle(String b) throws Exception {
		if (database.bicycleExists(b)) {
			database.removeBicycle(database.getBicycle(b));
		} else {
			throw new Exception("Bicycle doesn't exist");
		}
	}

	public void checkOutBicycle(String b) throws Exception {
		Bicycle bicycle = database.getBicycle(b);
		if (database.isInGarage(bicycle)) {
			database.checkOutBicycle(bicycle);
		} else {
			throw new Exception("Bicycle is not in garage");
		}
	}

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

	public User getUser(String b){
		for(User user: getUserList()){
			if(user.getBarcode().equals(b)){
				return user;
			}
		}
		return null;
	}
}
