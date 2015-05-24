package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

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
		//String barcode = JOptionPane.showInputDialog("Enter filepath: ");
		//dbManager.
	}
}