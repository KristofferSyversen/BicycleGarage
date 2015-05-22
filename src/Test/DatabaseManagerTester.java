package Test;

import org.junit.*;
import BicycleGarage.Database;
import BicycleGarage.DatabaseManager;

public class DatabaseManagerTester {
	private Database database;
	private DatabaseManager dbManager;
	
	@Before
	public void setup() {
		database = new Database();
		dbManager = new DatabaseManager(database);
	}
	
	@Test
	public void testAddUser(){
	}
}
