package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import BicycleGarage.Bicycle;
import BicycleGarage.Database;
import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class DatabaseManagerTester {
	private Database database;
	private DatabaseManager dbManager;
	private ArrayList<User> users;
	private ArrayList<Bicycle> bicycles;
	private ArrayList<Bicycle> bicyclesInGarage;
	
	@Before
	public void setup() {
		database = Database.databaseSetup();
		dbManager = new DatabaseManager(database);
		users = database.getUserList();
		bicycles = database.getBicycleList();
	}
	
	@Test
	public void testAddUser(){
		User newUser = new User("Charlie", 4, BarcodeGenerator.getCode());
		try {
			dbManager.addUser(newUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		users.add(newUser);
		for(User u: users){
			//TODO insert proper assertS
		}
	}
}
