package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class CheckOutBicycleMenuItem extends JMenuItem implements
		ActionListener {
	private DatabaseManager dbManager;

	public CheckOutBicycleMenuItem(String menuText, DatabaseManager dbManager) {
		super(menuText);
		this.dbManager = dbManager;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		String barcode = JOptionPane.showInputDialog("Enter bicycle barcode:");
		try {
			dbManager.checkOutBicycle(barcode);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getParent(),
					"Fail: " + e.getMessage());
		}
	}
}