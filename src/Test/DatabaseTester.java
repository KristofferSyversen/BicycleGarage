package Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import BicycleGarage.Bicycle;
import BicycleGarage.Database;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class DatabaseTester {
	private Database database;
	private Database emptyDatabase;
	private ArrayList<Bicycle> bicycles;
	private ArrayList<User> users;
	
	
	@Before
	public void setup(){
		//Create users
		users = new ArrayList<User>();
		User user1 = new User("Ada", 1, BarcodeGenerator.getCode());
		User user2 = new User("Bob", 2, BarcodeGenerator.getCode());
		User user3 = new User("Eve", 3, BarcodeGenerator.getCode());
		users.add(user1);
		users.add(user2);
		users.add(user3);
		//Create bicycles
		bicycles = new ArrayList<Bicycle>();
		ArrayList<Bicycle> bicyclesInGarage = new ArrayList<Bicycle>();
		Bicycle bmx = new Bicycle(user1, BarcodeGenerator.getCode());
		Bicycle mc = new Bicycle(user2, BarcodeGenerator.getCode());
		Bicycle hoj = new Bicycle(user3, BarcodeGenerator.getCode());
		Bicycle old = new Bicycle(user3, BarcodeGenerator.getCode());
		bicycles.add(old);
		bicycles.add(hoj);
		bicycles.add(mc);
		bicycles.add(bmx);
		bicyclesInGarage.add(old);
		bicyclesInGarage.add(bmx);
		
		emptyDatabase = new Database();
		database = new Database(users, bicycles, bicyclesInGarage);
		
		
	}
	
	@Test
	public void testAddUser(){
		String barcode = BarcodeGenerator.getCode();
		User user = new User("Charlie", 4, barcode);
		database.addUser(user);
		assertTrue("Expected 4 but was " + users.size(), users.size() == 4 );
	}
	
	@Test
	public void testRemoveUser(){
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		database.removeUser(user);
		assertTrue("Expected 3 but was " + users.size(), users.size() == 3 );
	}
	
	@Test
	public void testAddBicycle(){
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		Bicycle bicycle = new Bicycle(user,BarcodeGenerator.getCode());
		database.addBicycle(bicycle);
		assertTrue("Expected 5 but was " + bicycles.size(), bicycles.size() == 5 );
	}
	
	@Test
	public void testRemoveBicycle(){
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		Bicycle bicycle = new Bicycle(user,BarcodeGenerator.getCode());
		database.addBicycle(bicycle);
		database.removeBicycle(bicycle);
		assertTrue("Expected 4 but was " + bicycles.size(), bicycles.size() == 4 );
	}
	
	@Test
	public void testCheckInBicycle(){
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		Bicycle bicycle = new Bicycle(user,BarcodeGenerator.getCode());
		database.addBicycle(bicycle);
		database.checkInBicycle(bicycle);
		assertTrue("Expected true but was " + database.isInGarage(bicycle), database.isInGarage(bicycle));
	}
}
