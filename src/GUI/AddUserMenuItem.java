package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;
import BicycleGarage.User;
import Utils.BarcodeGenerator;
import Utils.Constants;

public class AddUserMenuItem extends JMenuItem implements ActionListener {
	private Database database;
	private BicycleGarageManager bgm;
	
	public AddUserMenuItem(String menuText, Database database, BicycleGarageManager bgm){
		super(menuText);
		this.database = database;
		this.bgm = bgm;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		String name = JOptionPane.showInputDialog("Enter user name:");
		if(name != null){
			String StringID = JOptionPane.showInputDialog("Enter user id:");
			int id = Integer.parseInt(StringID);
			if(id >= 0){
				try {
					String barcode = BarcodeGenerator.getCode();
					database.addUser(new User(name, id, barcode));
					bgm.printBarcode(barcode);
					database.writeToFile(Constants.DATABASE_FILE_NAME);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Fail: " + e.getMessage());
					
				}
			}
		}
	}
}