package Test;

import org.junit.*;

import BicycleGarage.Database;

public class DatabaseTester {
	private Database database;
	
	@Before
	public void setup(){
		database = new Database();
	}
}
