package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import Utils.Constants;
import BicycleGarage.Database;

public class RemoveBicycleMenuItem extends JMenuItem implements ActionListener {
	private Database database;
	private JTextPane statusBar;
	private DefaultListModel listModel;
	private int choice;

	public RemoveBicycleMenuItem(String menuText, Database database) {
		super(menuText);
		this.database = database;

		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
			String barcode = JOptionPane
					.showInputDialog("Enter the barcode of the bicycle to remove: ");
			try {
				database.removeBicycle(barcode);
				JOptionPane.showMessageDialog(null, "Bicycle Removed.");
				database.writeToFile(Constants.DATABASE_FILE_NAME);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getParent(),
						"Fail: " + e.getMessage());
			}
	}
}