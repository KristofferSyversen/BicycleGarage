package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import BicycleGarage.Bicycle;
import BicycleGarage.DatabaseManager;
import BicycleGarage.User;

public class ChangeListContentMenuItem extends JMenuItem implements
		ActionListener {
	private DatabaseManager ddm;
	private JTextPane statusBar;
	private DefaultListModel listModel;
	private int content;

	public ChangeListContentMenuItem(String menuText, DatabaseManager ddm, JTextPane statusBar, DefaultListModel listModel, int content) {
		super(menuText);
		this.ddm = ddm;
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
		for (User user : ddm.getUserList()) {
			listModel.addElement(user.toString());
		}
	}

	private void populateListWithBicycles() {
		statusBar.setText("Registered bicycles.");
		listModel.clear();
		for (Bicycle bicycle : ddm.getBicycleList()) {
			listModel.addElement(bicycle.toString());
		}
	}

	private void populateListWithBicyclesInGarage() {
		statusBar.setText("Bicycles in the garage.");
		listModel.clear();
		for (Bicycle bicycle : ddm.getBicycleInGarageList()) {
			listModel.addElement(bicycle.toString());
		}
	}
}
