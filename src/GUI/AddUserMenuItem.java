package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class AddUserMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager dbManager;
	
	public AddUserMenuItem(String menuText, DatabaseManager dbManager){
		super(menuText);
		this.dbManager = dbManager;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String name = JOptionPane.showInputDialog("Enter user name:");
		if(name != null){
			int id = -1;
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter user id:"));
			if(id >= 0){
				try {
					dbManager.addUser(new User(name, id, BarcodeGenerator.getCode()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Fail: " + e.getMessage());
				}
			}
		}
	}

}
