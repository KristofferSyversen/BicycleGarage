package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.DatabaseManager;

public class RemoveUserMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager dbManager;

	public RemoveUserMenuItem(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String barcode = JOptionPane
				.showInputDialog("WARNING - All bicycles associated with the user will also be removed!\nEnter user barcode: ");
		//dbManager.removeUser(barcode);
		
	}

}
