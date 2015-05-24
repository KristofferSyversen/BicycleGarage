package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BicycleGarage.BicycleGarageManager;

public class UnlockDoorMenuItem extends JMenuItem implements ActionListener {
	private BicycleGarageManager bgm;
	private int door;

	public UnlockDoorMenuItem(String menuText, BicycleGarageManager bgm,
			int door) {
		super(menuText);
		this.bgm = bgm;
		this.door = door;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		int time = 0;
		try {
			time = Integer.parseInt(JOptionPane.showInputDialog("Open for how long? (seconds)"));
		} catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Please enter a number");
			return;
		}
		
		//Entry
		if(door == 0) {
			bgm.unlockEntryDoor(time);
			JOptionPane.showMessageDialog(null, "Unlocking entry door for: " + time + " secounds.");		
		
		//Exit
		} else if(door == 1) {
			bgm.unlockExitDoor(time);
			JOptionPane.showMessageDialog(null, "Unlocking exit door for: " + time + " secounds.");
		}
	}
}
