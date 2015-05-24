package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class LoadDatabaseFromFileMenuItem extends JMenuItem implements
		ActionListener {
	private DatabaseManager dbManager;

	public LoadDatabaseFromFileMenuItem(String menuText, DatabaseManager dbManager) {
		super(menuText);
		this.dbManager = dbManager;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		//String barcode = JOptionPane.showInputDialog("Enter filepath: ");
		//dbManager.
	}
}