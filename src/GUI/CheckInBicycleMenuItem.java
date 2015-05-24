package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class CheckInBicycleMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager dbManager;

	public CheckInBicycleMenuItem(String menuText, DatabaseManager dbManager) {
		super(menuText);
		this.dbManager = dbManager;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		String barcode = JOptionPane.showInputDialog("Enter bicycle barcode:");
		try {
			dbManager.checkInBicycle(barcode);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getParent(),
					"Fail: " + e.getMessage());
		}
	}
}