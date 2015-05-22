package BicycleGarage;

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
			//felmeddelande
		}
	}
	
	public void removeUser(String b){
		User user = database.getUser(b);
		if(/*user has no bikes*/){
			database.removeUser(user);
		} else {
			//felmeddelande
		}
	}
	
	public void addBicycle(User o, String b){
		Bicycle bicycle = new Bicycle(o, b);
		if()
	}
}
