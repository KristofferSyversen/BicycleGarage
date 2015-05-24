package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import BicycleGarage.DatabaseManager;

public class RemoveBicycleMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager ddm;
	private JTextPane statusBar;
	private DefaultListModel listModel;
	private int choice;

	public RemoveBicycleMenuItem(String menuText, DatabaseManager ddm) {
		super(menuText);
		this.ddm = ddm;

		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {

			String barcode = JOptionPane
					.showInputDialog("Enter the barcode of the bicycle to remove: ");
			
			try {
				ddm.checkOutBicycle(barcode);
			} catch (Exception e) {
				//Bicycle was not in garage.
				JOptionPane.showMessageDialog(getParent(),
						"Fail: " + e.getMessage());
			}
			
			
			try {
				ddm.removeBicycle(barcode);
				JOptionPane.showMessageDialog(null, "Bicycle Removed.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getParent(),
						"Fail: " + e.getMessage());
			}
	}
}