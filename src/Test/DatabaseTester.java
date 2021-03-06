package Test;

import static org.junit.Assert.*;

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

	// TODO Rewrite pretty much everything

	@Before
	public void setup() {
		emptyDatabase = new Database();
		database = Database.getGenericDatabase();
	}

	@Test
	public void testAddUser() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		database.getUser(user.getBarcode());
		users = database.getUserList();
		assertTrue("Expected 4 but was " + users.size(), users.size() == 4);
	}

	@Test
	public void testRemoveUser() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		database.removeUser(user.getBarcode());
		try {
			database.getUser(user.getBarcode());
			fail();
		} catch (IllegalArgumentException e) {
		}
		users = database.getUserList();
		assertTrue("Expected 3 but was " + users.size(), users.size() == 3);
	}

	@Test
	public void testAddBicycle() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		database.addBicycle(user.getBarcode());
		ArrayList<Bicycle> userBikes = database.getUser(user.getBarcode())
				.getBicycles();
		assertTrue(userBikes.size() > 0);
		bicycles = database.getBicycleList();
		assertTrue("Expected 5 but was " + bicycles.size(),
				bicycles.size() == 5);
	}

	@Test
	public void testRemoveBicycle() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		Bicycle bicycle = database.addBicycle(user.getBarcode());
		database.removeBicycle(bicycle.getBarcode());
		bicycles = database.getBicycleList();
		assertTrue("Expected 4 but was " + bicycles.size(),
				bicycles.size() == 4);
	}

	@Test
	public void testCheckInBicycle() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		Bicycle bike = database.addBicycle(user.getBarcode());
		database.checkInBicycle(bike.getBarcode());
		assertTrue(
				"Expected true but was "
						+ database.isInGarage(bike.getBarcode()),
				database.isInGarage(bike.getBarcode()));
	}

	@Test
	public void testCheckOutBicycle() {
		User user = new User("Charlie", 4, BarcodeGenerator.getCode());
		database.addUser(user);
		Bicycle bicycle = database.addBicycle(user.getBarcode());

		database.checkInBicycle(bicycle.getBarcode());
		database.checkOutBicycle(bicycle.getBarcode());
		assertTrue(
				"Expected false but was "
						+ database.isInGarage(bicycle.getBarcode()),
				!database.isInGarage(bicycle.getBarcode()));
	}

	@Test
	public void testUserExists() {
		String b = BarcodeGenerator.getCode();
		User user = new User("Charlie", 4, b);

		database.addUser(user);
		assertTrue("Expected true but was " + database.userExists(b),
				database.userExists(b));

		database.removeUser(user.getBarcode());
		assertTrue("Expected false but was " + database.userExists(b),
				!database.userExists(b));
	}

	@Test
	public void testGetUser() {
		String b = BarcodeGenerator.getCode();
		User user = new User("Charlie", 4, b);

		database.addUser(user);
		assertTrue("Expected true but was " + database.getUser(b).equals(user),
				database.getUser(b).equals(user));
	}

	@Test
	public void testHasBicycleOrUser() {
		String b = BarcodeGenerator.getCode();
		User user = new User("Charlie", 4, b);
		database.addUser(user);
		Bicycle bicycle = database.addBicycle(b);
		assertTrue(database.hasBicycleOrUser(bicycle.getBarcode()));
		assertTrue("Expected true but was " + database.userExists(b),
				database.userExists(b));

		database.removeUser(b);
		assertTrue("Expected false but was " + database.userExists(b),
				!database.userExists(b));
	}
	
	@Test
	public void testWriteToFile(){
		String fileName = "databaseFile.udb";
		assertTrue(database.writeToFile(fileName));
	}
	
	@Test
	public void testReadFromFile(){
		String fileName = "databaseFile.udb";
		assertTrue(database.writeToFile(fileName));
		
		Database db = new Database(fileName);
		ArrayList<User> databaseUsers = database.getUserList();
		ArrayList<User> dbUsers = db.getUserList();
		assertTrue("Expected true but was " + (databaseUsers.size() == dbUsers.size()),
				databaseUsers.size() == dbUsers.size());
		for(int i = 0; i < dbUsers.size(); i++){
			assertTrue(dbUsers.get(i).equals(databaseUsers.get(i)));
			assertTrue(dbUsers.get(i).getName().equals(databaseUsers.get(i).getName()));
			
			ArrayList<Bicycle> databaseBikes = databaseUsers.get(i).getBicycles();
			ArrayList<Bicycle> dbBikes = dbUsers.get(i).getBicycles();
			assertTrue(databaseBikes.size() == dbBikes.size());
			for(int j = 0; j < dbBikes.size(); j++){
				assertTrue(dbBikes.get(j).equals(databaseBikes.get(j)));
			}
		}
	}
	
	@Test
	public void testReadNonExistingFile(){
		Database db = new Database("Not-A-Real-File");
		assertTrue(db.getUserList().size() == 0);
	}
}
