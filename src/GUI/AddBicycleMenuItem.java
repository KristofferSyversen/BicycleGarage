package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.Bicycle;
import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

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
		String userBarcode = JOptionPane.showInputDialog("To whoom do you which to register the bike?:");
		if (userBarcode != null) {

			User user = database.getUser(userBarcode);
			if(user != null) {
				String barcode = BarcodeGenerator.getCode();
				Bicycle bicycle = new Bicycle(barcode);
				user.addBicycle(bicycle);
				
				try {
					database.addBicycle(userBarcode);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Error message?
		}
	}
}