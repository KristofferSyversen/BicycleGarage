package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.BicycleGarageManager;
import BicycleGarage.DatabaseManager;
import BicycleGarage.User;
import Utils.BarcodeGenerator;

public class AddUserMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager dbManager;
	private BicycleGarageManager bgm;
	
	public AddUserMenuItem(String menuText, DatabaseManager dbManager, BicycleGarageManager bgm){
		super(menuText);
		this.dbManager = dbManager;
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
					dbManager.addUser(new User(name, id, barcode));
					bgm.printBarcode(barcode);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Fail: " + e.getMessage());
					
				}
			}
		}
	}
}