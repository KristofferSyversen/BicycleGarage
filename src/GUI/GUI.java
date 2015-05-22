package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;

import BicycleGarage.BicycleGarageManager;
import BicycleGarage.DatabaseManager;
import BicycleGarage.User;

public class GUI {
	private DatabaseManager ddm;
	private BicycleGarageManager bgm;
	
	JList<String> itemList;

	public GUI(DatabaseManager ddm, BicycleGarageManager bgm) {
		this.ddm = ddm;
		this.bgm = bgm;

		JFrame mainFrame = new JFrame("Bicycle garage operator controll system.");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		itemList = new JList<String>();
		mainFrame.add(itemList, BorderLayout.NORTH);

		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}