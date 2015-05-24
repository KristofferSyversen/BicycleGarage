package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import BicycleGarage.Bicycle;
import BicycleGarage.Database;
import BicycleGarage.User;

public class ChangeListContentMenuItem extends JMenuItem implements
		ActionListener {
	private Database database;
	private JLabel statusBar;
	private DefaultListModel listModel;
	private int content;

	public ChangeListContentMenuItem(String menuText, Database database, JLabel statusBar, DefaultListModel listModel, int content) {
		super(menuText);
		this.database = database;
		this.statusBar = statusBar;
		this.listModel = listModel;
		this.content = content;
		
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(content == 0) {
			populateListWithUsers();
		}
		if(content == 1) {
			populateListWithBicycles();
		}
		if(content == 2) {
			populateListWithBicyclesInGarage();
		}
	}
	
	private void populateListWithUsers() {
		statusBar.setText("Registered users.");
		listModel.clear();
		for (User user : database.getUserList()) {
			listModel.addElement(user.toString());
		}
	}

	private void populateListWithBicycles() {
		statusBar.setText("Registered bicycles.");
		listModel.clear();
		for (Bicycle bicycle : database.getBicycleList()) {
			listModel.addElement(bicycle.toString());
		}
	}

	private void populateListWithBicyclesInGarage() {
		statusBar.setText("Bicycles in the garage.");
		listModel.clear();
		for (Bicycle bicycle : database.getBicyclesInGarageList()) {
			listModel.addElement(bicycle.toString());
		}
	}
}
