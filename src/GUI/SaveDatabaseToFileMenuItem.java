package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.Database;

public class SaveDatabaseToFileMenuItem extends JMenuItem implements
		ActionListener {
	private Database database;

	public SaveDatabaseToFileMenuItem(String menuText, Database db) {
		super(menuText);
		this.database = db;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		int choice = JOptionPane.showConfirmDialog(null, 
		"Are you sure you want to save?", 
		"Load State.", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION){
			database.writeToFile("databaseFile.udb");
		}
	}
}