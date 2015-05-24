package BicycleGarage;

import java.util.ArrayList;

import Utils.BarcodeGenerator;
import Utils.FileIO;

/**
 * 
 * @author kristoffer
 *
 */
public class Database {
	/*
	 * We have to decide how we want to store user/bike data, we can do eigther
	 * of the following: * Let each user have a list of bikes. * Have two lists,
	 * one for bikes and one for users and let the users contain references into
	 * the bicycles list(and vice versa maybe?). * Another idea?
	 * 
	 * We need a way to know which bikes are in the garage, we can eigher use
	 * boolean tags in the bike isntances or maybe a list in Database containing
	 * references to bikes in the bicycles list.
	 */
	private ArrayList<User> users; // Or some other container class.
	private ArrayList<Bicycle> bicycles; //
	private ArrayList<Bicycle> bicyclesInGarage;

	/**
	 * Constructor used primarily for testing
	 * 
	 * @param users
	 * @param bicycles
	 * @param bicyclesInGarage
	 */
	public Database(ArrayList<User> users, ArrayList<Bicycle> bicycles,
			ArrayList<Bicycle> bicyclesInGarage) {
		this.users = users;
		this.bicycles = bicycles;
		this.bicyclesInGarage = bicyclesInGarage;
	}

	/**
	 * 
	 * @param userFile
	 */
	public Database(String userFile) {
		// Parse the user file and place the user data where it belongs.
		users = new ArrayList<User>();
		bicycles = new ArrayList<Bicycle>();
		bicyclesInGarage = new ArrayList<Bicycle>();
		
		try {
			parseData(FileIO.readFromFile(userFile));
		} catch(Exception e) {
			
		}
	}

	public Database() {
		users = new ArrayList<User>();
		bicycles = new ArrayList<Bicycle>();
		bicyclesInGarage = new ArrayList<Bicycle>();
	}

	/*
	 * Examples of methods to implement:
	 * 
	 * + addUser(User) + removeUser(User) + addBicycle(Bicycle) +
	 * removeBicycle(Bicycle) + checkOutBicycle(Bicycle) +
	 * checkInBicycle(Bicycle) + getUserList() + getBikeList()
	 */
	
	private void parseData(String data){
		if(data.length() != 0){
			String[] dataParts = data.split("%%%%%");
			String[] lines = dataParts[0].split("\n");
			
			for(int i = 0; i < lines.length; i++){
				int id = -1;
				String line = lines[i];
				String userName = parseField(line);
				line = line.substring(userName.length() + 1);
				try{
					id = Integer.parseInt(parseField(line)); // throws NumberFormatException
					line = line.substring(String.valueOf(id).length() + 1);
				} catch (NumberFormatException e){
					// Ignore incomplete user
				}
				
				String userBarcode = parseField(line);
				line = line.substring(userBarcode.length() + 1);
					
				if(!userName.isEmpty() && !userBarcode.isEmpty() && id != -1){
					User user = new User(userName,id, userBarcode);
					users.add(user);
						
					while(line.length() > 0){
						String bicycleBarcode = parseField(line);
						Bicycle bicycle = new Bicycle(bicycleBarcode);
						user.addBicycle(bicycle);
						bicycles.add(bicycle);
						line = line.substring(bicycleBarcode.length() + 1);
					}
				}
			}
			lines = dataParts[1].split("\n");
			for(int i = 0; i < lines.length; i++){
				String line = lines[i];
				if(line.length() > 0){
					Bicycle bicycle = getBicycle(line);
					if(bicycle != null) bicyclesInGarage.add(bicycle);
				}
			}
		}
	}
	
	private String parseField(String line){
		String field = "";
		for(int i = 0; i < line.length(); i++){
			char ch = line.charAt(i);
			if(ch == '%'){
				break;
			} else {
				field += line.charAt(i);	// SPLIIT
			}			
		}
		return field;
	}
	
	public boolean writeToFile(String userFile, String bicycleFile){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < users.size(); i++){
			User user = users.get(i);
			sb.append(user.getName() + '%' + user.getId() + '%');
			ArrayList<Bicycle> userBicycles= user.getBicycles();
			for(int j = 0; j < userBicycles.size(); j++){
				sb.append(userBicycles.get(i).getBarcode() + '%');
			}
		}
		sb.append("%%%%%" + '\n');
		for(int i = 0; i < bicyclesInGarage.size(); i++){
			sb.append(bicyclesInGarage.get(i).getBarcode() + '\n');
		}
		return FileIO.writeToFile(userFile, sb.toString());
	}
	
	/**
	 * 
	 * @param user
	 */
	public boolean addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
			return true;
		}
		return false;
	}

	public boolean removeUser(User user) {
		return users.remove(user);
	}

	/**
	 * 
	 * @param bicycle
	 */
	public boolean addBicycle(Bicycle bicycle) {
		if (!bicycles.contains(bicycle)) {
			bicycles.add(bicycle);
			return true;
		}
		return false;
	}

	public boolean removeBicycle(Bicycle bicycle) {
		return bicycles.remove(bicycle);
	}

	/**
	 * 
	 * @param bicycle
	 */
	public boolean checkInBicycle(Bicycle bicycle) {
		if (!bicyclesInGarage.contains(bicycle)) {
			bicyclesInGarage.add(bicycle);
			return true;
		}
		return false;
	}

	public boolean checkOutBicycle(Bicycle bicycle) {
		return bicyclesInGarage.remove(bicycle);
	}

	/**
	 * Generates a unique barcode.
	 */
	public String generateBarcode() {
		// TODO handle static variable overflow in BarcodeGenerator
		return BarcodeGenerator.getCode();
	}

	public boolean userExists(String barcode) {
		for (User u : users) {
			if (u.getBarcode().equals(barcode)) {
				return true;
			}
		}
		return false;
	}

	public User getUser(String barcode) {
		for (User u : users) {
			if (u.getBarcode().equals(barcode)) {
				return u;
			}
		}
		return null;
	}

	public boolean hasBicycleOrUser(String barcode) {
		if (userExists(barcode) || bicycleExists(barcode)) {
			return true;
		}
		return false;
	}

	public boolean bicycleExists(String barcode) {
		for (Bicycle b : bicycles) {
			if (b.getBarcode().equals(barcode)) {
				return true;
			}
		}
		return false;
	}

	public Bicycle getBicycle(String barcode) {
		for (Bicycle b : bicycles) {
			if (b.getBarcode().equals(barcode)) {
				return b;
			}
		}
		return null;
	}

	public boolean isInGarage(Bicycle bicycle) { // TODO rewrite argument as
													// String
		for (Bicycle b : bicycles) {
			if (b.getBarcode().equals(bicycle)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<User> getUserList() {
		return users;
	}

	public ArrayList<Bicycle> getBicycleList() {
		return bicycles;
	}
	
	public ArrayList<Bicycle> getBicyclesInGarageList() {
		return bicyclesInGarage;
	}

	public static Database databaseSetup() {
		// Create users
		ArrayList<User> users = new ArrayList<User>();
		User user1 = new User("Ada", 1, BarcodeGenerator.getCode());
		User user2 = new User("Bob", 2, BarcodeGenerator.getCode());
		User user3 = new User("Eve", 3, BarcodeGenerator.getCode());
		users.add(user1);
		users.add(user2);
		users.add(user3);
		// Create bicycles
		ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
		ArrayList<Bicycle> bicyclesInGarage = new ArrayList<Bicycle>();
		Bicycle bmx = new Bicycle(BarcodeGenerator.getCode());
		Bicycle mc = new Bicycle(BarcodeGenerator.getCode());
		Bicycle hoj = new Bicycle(BarcodeGenerator.getCode());
		Bicycle old = new Bicycle(BarcodeGenerator.getCode());
		user1.addBicycle(bmx);
		user2.addBicycle(mc);
		user3.addBicycle(hoj);
		user3.addBicycle(old);
		bicycles.add(old);
		bicycles.add(hoj);
		bicycles.add(mc);
		bicycles.add(bmx);
		bicyclesInGarage.add(old);
		bicyclesInGarage.add(bmx);
		
		user1.addBicycle(bmx);
		user1.addBicycle(mc);
		user2.addBicycle(old);
		user1.addBicycle(hoj);

		Database database = new Database(users, bicycles, bicyclesInGarage);
		return database;
	}
}
