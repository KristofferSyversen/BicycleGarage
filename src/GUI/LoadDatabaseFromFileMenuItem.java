package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import BicycleGarage.Database;

public class LoadDatabaseFromFileMenuItem extends JMenuItem implements
		ActionListener {
	private Database database;

	public LoadDatabaseFromFileMenuItem(String menuText, Database dbManager) {
		super(menuText);
		this.database = dbManager;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		//String barcode = JOptionPane.showInputDialog("Enter filepath: ");
		//dbManager.
	}
}