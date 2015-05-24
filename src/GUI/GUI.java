package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import BicycleGarage.Bicycle;
import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;

public class GUI {
	private Database db;
	private BicycleGarageManager bgm;

	private JList<String> itemList;
	private DefaultListModel listModel;

	private JLabel statusBar;

	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private JCheckBoxMenuItem cbMenuItem;

	public GUI(Database db, BicycleGarageManager bgm) {
		this.db = db;
		this.bgm = bgm;

		JFrame mainFrame = new JFrame(
				"Bicycle garage operator controll system.");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		listModel = new DefaultListModel();
		itemList = new JList<String>(listModel);
		statusBar = new JLabel();

		statusBar.setText("Bicycles in the garage.");
		for (Bicycle bicycle : db.getBicyclesInGarageList()) {
			listModel.addElement(bicycle.toString());
		}

		mainFrame.add(statusBar, BorderLayout.NORTH);
		mainFrame.add(itemList, BorderLayout.CENTER);

		// Create the menu bar.
		menuBar = new JMenuBar();
		
		// File.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
				"Menu item to controll file input/output.");
		menuBar.add(menu);

		// Save database to file.
		menuItem = new SaveDatabaseToFileMenuItem("Save database to file.", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext()
				.setAccessibleDescription("Saves the database to a file.");
		menu.add(menuItem);	
		
		// Load database to file.
		menuItem = new LoadDatabaseFromFileMenuItem("Load database from file.", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext()
				.setAccessibleDescription("Loads a database from a file.");
		menu.add(menuItem);
		
		// Exit. //TODO: move mechanics to own class.
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	int option = JOptionPane.showConfirmDialog(null, "Are you shure you want to exit?");
	        	if(option == JOptionPane.YES_OPTION) {
	        		System.exit(0);	
	        	}            
	        }
	    });
		
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext()
				.setAccessibleDescription("Exit the app.");
		menu.add(menuItem);		

		// --------

		// Database.
		menu = new JMenu("Database");
		menu.setMnemonic(KeyEvent.VK_D);
		menu.getAccessibleContext().setAccessibleDescription(
				"Menu items to controll the database");
		menuBar.add(menu);

		// Add user
		menuItem = new AddUserMenuItem("Add user", db, bgm);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Adds a user to the database.");
		menu.add(menuItem);
		
		// Add bicycle
		menuItem = new AddBicycleMenuItem("Add bicycle", db, bgm);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Adds a bicycle to the database and a user.");
		menu.add(menuItem);

		// Remove user
		menuItem = new RemoveUserMenuItem("Remove user", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"removes a user from the database.");
		menu.add(menuItem);

		// Remove bicycle
		menuItem = new RemoveBicycleMenuItem("Remove bicycle", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"removes a bicycle from the database.");
		menu.add(menuItem);
		
		// Check in bicycle
		menuItem = new CheckInBicycleMenuItem("Check in bicycle", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Checks in a bicycle in the garage.");
		menu.add(menuItem);

		// Check out bicycle
		menuItem = new CheckOutBicycleMenuItem("Check out bicycle", db);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Checks out a bicycle from the garage.");
		menu.add(menuItem);

		// -----

		// View.
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
				"Menu items to controll the database");
		menuBar.add(menu);

		// List Users
		menuItem = new ChangeListContentMenuItem("List users", db, statusBar,
				listModel, 0);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext()
				.setAccessibleDescription("Lists users.");
		menu.add(menuItem);

		// List all bicycles
		menuItem = new ChangeListContentMenuItem("List bicycles", db,
				statusBar, listModel, 1);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Lists bicycles");
		menu.add(menuItem);

		// List bicycles in garage
		menuItem = new ChangeListContentMenuItem("List bicycles in garage",
				db, statusBar, listModel, 2);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"List bicycles in garage.");
		menu.add(menuItem);

		// -----

		// Hardware.
		menu = new JMenu("Hardware");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription(
				"This menu controlls the hardware");

		// Unlock entry door
		menuItem = new UnlockDoorMenuItem("Unlock entry door", bgm, 0);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Unlocks the entry door.");
		menu.add(menuItem);

		// Unlock exit door
		menuItem = new UnlockDoorMenuItem("Unlock exit door", bgm, 1);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Unlocks the exit door.");
		menu.add(menuItem);
		
		

		menuBar.add(menu);
		mainFrame.setJMenuBar(menuBar);

		// ///
		
		

		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}