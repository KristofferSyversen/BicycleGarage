package BicycleGarage;

import java.util.ArrayList;
import java.util.Collections;

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
		this.bicyclesInGarage = bicyclesInGarage;
	}

	/**
	 * 
	 * @param userFile
	 */
	public Database(String userFile) {
		// Parse the user file and place the user data where it belongs.
		users = new ArrayList<User>();
		bicyclesInGarage = new ArrayList<Bicycle>();

		try {
			parseData(FileIO.readFromFile(userFile));
			for(int i = 0; i < users.size(); i++) {
				BarcodeGenerator.setBarcodeAsUnavailable(users.get(i).getBarcode());
				ArrayList<Bicycle> bicycles = users.get(i).getBicycles();
				for(int j = 0; j < bicycles.size(); j++) {
					BarcodeGenerator.setBarcodeAsUnavailable(bicycles.get(j).getBarcode());
				}
			}
		} catch (Exception e) {
			users = new ArrayList<User>();
			bicyclesInGarage = new ArrayList<Bicycle>();
		}
	}

	public Database() {
		users = new ArrayList<User>();
		bicyclesInGarage = new ArrayList<Bicycle>();
	}

	/*
	 * Examples of methods to implement:
	 * 
	 * + addUser(User) + removeUser(User) + addBicycle(Bicycle) +
	 * removeBicycle(Bicycle) + checkOutBicycle(Bicycle) +
	 * checkInBicycle(Bicycle) + getUserList() + getBikeList()
	 */

	private void parseData(String data) {
		if (data.length() != 0) {
			String[] dataParts = data.split("%%%%%");
			String[] lines = dataParts[0].split("\n");

			for (int i = 0; i < lines.length; i++) {
				int id = -1;
				String line = lines[i];
				String userName = parseField(line);
				line = line.substring(userName.length() + 1);
				try {
					id = Integer.parseInt(parseField(line)); // throws
																// NumberFormatException
					line = line.substring(String.valueOf(id).length() + 1);
				} catch (NumberFormatException e) {
					// Ignore incomplete user
				}

				String userBarcode = parseField(line);
				line = line.substring(userBarcode.length() + 1);

				if (!userName.isEmpty() && !userBarcode.isEmpty() && id != -1) {
					User user = new User(userName, id, userBarcode);
					users.add(user);

					while (line.length() > 0) {
						String bicycleBarcode = parseField(line);
						Bicycle bicycle = new Bicycle(bicycleBarcode);
						user.addBicycle(bicycle);
						line = line.substring(bicycleBarcode.length() + 1);
					}
				}
			}
			lines = dataParts[1].split("\n");
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i];
				if (line.length() > 0) {
					Bicycle bicycle = getBicycle(line);
					if (bicycle != null)
						bicyclesInGarage.add(bicycle);
				}
			}
		}
	}

	private String parseField(String line) {
		String field = "";
		for (int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if (ch == '%') {
				break;
			} else {
				field += line.charAt(i); // SPLIIT
			}
		}
		return field;
	}

	public boolean writeToFile(String userFile) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			sb.append(user.getName() + '%' + user.getId() + '%' + user.getBarcode() + '%');
			ArrayList<Bicycle> userBicycles = user.getBicycles();
			for (int j = 0; j < userBicycles.size(); j++) {
				sb.append(userBicycles.get(j).getBarcode() + '%');
			}
			sb.append('\n');
		}
		sb.append("%%%%%");
		for (int i = 0; i < bicyclesInGarage.size(); i++) {
			sb.append('\n' + bicyclesInGarage.get(i).getBarcode());
		}
		return FileIO.writeToFile(userFile, sb.toString());
	}

	/**
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		} else {
			throw new IllegalArgumentException("Barcode already exists!");
		}
	}

	public void removeUser(String barcode) {
		User user = getUser(barcode);
		for (Bicycle b : user.getBicycles()) {
			if (bicyclesInGarage.contains(b)) {
				throw new IllegalArgumentException("User has bicycles in garage, remove these first!");
			}
		}
		for(Bicycle b: user.getBicycles()){
			BarcodeGenerator.setBarcodeAsAvailable(b.getBarcode());
		}
		users.remove(user);
		BarcodeGenerator.setBarcodeAsAvailable(user.getBarcode());
	}

	public Bicycle addBicycle(String userBarcode){
		User user = getUser(userBarcode);
		Bicycle bicycle = new Bicycle(BarcodeGenerator.getCode());
		user.addBicycle(bicycle);
		return bicycle;
	}
	
	public void removeBicycle(String barcode) {
		Bicycle removeBike = getBicycle(barcode);
		if (bicyclesInGarage.contains(removeBike)) {
			throw new IllegalArgumentException(
					"Bicycle can't be removed since it is currently in the garage!");
		}
		BarcodeGenerator.setBarcodeAsAvailable(barcode);
		for (User u : users) {
			if (u.removeBicycle(removeBike)) {
				break;
			}
		}

	}

	/**
	 * 
	 * @param bicycle
	 */
	public void checkInBicycle(String barcode) {
		Bicycle bicycle = getBicycle(barcode);
		if (!bicyclesInGarage.contains(bicycle)) {
			bicyclesInGarage.add(bicycle);
			return;
		} else {
			throw new IllegalArgumentException("Bicycle is already in garage!");
		}
	}

	public User getUser(String barcode) {
		for (User u : users) {
			if (u.getBarcode().equals(barcode)) {
				return u;
			}
		}
		throw new IllegalArgumentException("No such user exists!");
	}

	public void checkOutBicycle(String barcode) {
		Bicycle bicycle = getBicycle(barcode);
		if(bicyclesInGarage.contains(bicycle)){
			bicyclesInGarage.remove(bicycle);
		} else {
			throw new IllegalArgumentException("Bicycle isn't currently in garage!");
		}
	}

	public Bicycle getBicycle(String barcode) {
		for (User u : users) {
			for (Bicycle b : u.getBicycles()) {
				if (b.getBarcode().equals(barcode)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException("No such bicycle exists!");
	}



	public ArrayList<User> getUserList() {
		Collections.sort(users);
		return users;
	}

	public ArrayList<Bicycle> getBicycleList(){
		ArrayList<Bicycle> bicycles = new ArrayList<Bicycle>();
		for(User u: users){
			bicycles.addAll(u.getBicycles());
		}
		return bicycles;
	}
	
	public ArrayList<Bicycle> getBicyclesInGarageList() {
		return bicyclesInGarage;
	}
	
	public boolean hasBicycleOrUser(String barcode){
		try{
			getUser(barcode);
		} catch (IllegalArgumentException e){
			try{
				getBicycle(barcode);
			} catch(IllegalArgumentException e2){
				return false;
			}
		}
		return true;
	}
	
	public boolean bicycleExists(String barcode){
		try{
			getBicycle(barcode);
		} catch (IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	public boolean userExists(String barcode){
		try{
			getUser(barcode);
		} catch (IllegalArgumentException e){
			return false;
		}
		return true;
	}
	
	public boolean isInGarage(String barcode){
		for(Bicycle b: bicyclesInGarage){
			if(b.getBarcode().equals(barcode)){
				return true;
			}
		}
		return false;
	}

	public static Database getGenericDatabase() {
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
		bicyclesInGarage.add(old);
		bicyclesInGarage.add(bmx);

		Database database = new Database(users, bicycles, bicyclesInGarage);
		return database;
	}
}
