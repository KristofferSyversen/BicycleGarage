package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Utils.Constants;
import BicycleGarage.Database;

public class CheckOutBicycleMenuItem extends JMenuItem implements
		ActionListener {
	private Database database;

	public CheckOutBicycleMenuItem(String menuText, Database database) {
		super(menuText);
		this.database = database;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		String barcode = JOptionPane.showInputDialog("Enter bicycle barcode:");
		try {
			database.checkOutBicycle(barcode);
			database.writeToFile(Constants.DATABASE_FILE_NAME);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getParent(),
					"Fail: " + e.getMessage());
		}
	}
}