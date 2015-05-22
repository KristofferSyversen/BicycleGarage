package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import BicycleGarage.Bicycle;
import BicycleGarage.BicycleGarageManager;
import BicycleGarage.DatabaseManager;

public class GUI {
	private DatabaseManager ddm;
	private BicycleGarageManager bgm;

	private JList<String> itemList;
	private DefaultListModel listModel;
	
	
	//TODO: Change this to something immutable.
	private JTextPane statusBar;

	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private JCheckBoxMenuItem cbMenuItem;

	public GUI(DatabaseManager ddm, BicycleGarageManager bgm) {
		this.ddm = ddm;
		this.bgm = bgm;

		JFrame mainFrame = new JFrame(
				"Bicycle garage operator controll system.");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		listModel = new DefaultListModel();
		itemList = new JList<String>(listModel);
		statusBar = new JTextPane();

		statusBar.setText("Bicycles in the garage.");
		for (Bicycle bicycle : ddm.getBicycleInGarageList()) {
			listModel.addElement(bicycle.toString());
		}
		
		
		
		mainFrame.add(statusBar, BorderLayout.NORTH);		
		mainFrame.add(itemList, BorderLayout.CENTER);

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Database.
		menu = new JMenu("Database");
		menu.setMnemonic(KeyEvent.VK_D);
		menu.getAccessibleContext().setAccessibleDescription(
				"Menu items to controll the database");
		menuBar.add(menu);

		// Add user
		menuItem = new AddUserMenuItem("Add user", ddm);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Adds a user to the database.");
		menu.add(menuItem);

		// Remove user
		menuItem = new RemoveUserMenuItem("Remove user", ddm);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"removes a user from the database.");
		menu.add(menuItem);

		// -----

		// View.
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription(
				"Menu items to controll the database");
		menuBar.add(menu);

		// List Users
		menuItem = new ChangeListContentMenuItem("List users", ddm, statusBar, listModel, 0);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Lists users.");
		menu.add(menuItem);

		// List all bicycles
		menuItem = new ChangeListContentMenuItem("List bicycles", ddm, statusBar, listModel, 1);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Lists bicycles");
		menu.add(menuItem);

		// List bicycles in garage
		menuItem = new ChangeListContentMenuItem("List bicycles in garage", ddm, statusBar, listModel, 2);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
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
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
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