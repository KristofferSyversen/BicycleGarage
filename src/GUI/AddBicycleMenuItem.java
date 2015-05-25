package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Utils.Constants;
import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;

public class AddBicycleMenuItem extends JMenuItem implements ActionListener {
	private Database database;
	private BicycleGarageManager bgm;

	public AddBicycleMenuItem(String menuText, Database database,
			BicycleGarageManager bgm) {
		super(menuText);
		this.database = database;
		this.bgm = bgm;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		String userBarcode = JOptionPane
				.showInputDialog("To whom do you wish to register the bike? (Barcode)");
		if (userBarcode != null && database.getUser(userBarcode) != null) {
			try {
				database.addBicycle(userBarcode);
				bgm.printBarcode(userBarcode);
				database.writeToFile(Constants.DATABASE_FILE_NAME);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getParent(), "Fail: " + e.getMessage());
			}
		}
	}
}