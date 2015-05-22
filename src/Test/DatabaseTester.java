package Test;

import java.util.ArrayList;

import org.junit.Before;

import Utils.BarcodeGenerator;
import BicycleGarage.Bicycle;
import BicycleGarage.Database;
import BicycleGarage.User;

public class DatabaseTester {
	private Database database;
	private Database emptyDatabase;
	
	
	@Before
	public void setup(){
		//Create users
		ArrayList<User> users = new ArrayList<User>();
		User user1 = new User("Ada", 1, BarcodeGenerator.getCode());
		User user2 = new User("Bob", 2, BarcodeGenerator.getCode());
		User user3 = new User("Eve", 3, BarcodeGenerator.getCode());
		users.add(user1);
		users.add(user2);
		users.add(user3);
		//Create bicycles
		ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
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
}
