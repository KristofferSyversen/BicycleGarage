package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import BicycleGarage.DatabaseManager;

public class RemoveUserMenuItem extends JMenuItem implements ActionListener {
	private DatabaseManager ddm;
	private JTextPane statusBar;
	private DefaultListModel listModel;
	private int choice;

	public RemoveUserMenuItem(String menuText, DatabaseManager ddm) {
		super(menuText);
		this.ddm = ddm;

		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {

			String barcode = JOptionPane
					.showInputDialog("Enter the barcode of the user to remove: ");
			try {
				ddm.removeUser(barcode);
				JOptionPane.showMessageDialog(null, "User Removed.");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getParent(),
						"Fail: " + e.getMessage());
			}
	}
}