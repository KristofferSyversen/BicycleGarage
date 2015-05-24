package Test;

import org.junit.Test;

import BicycleGarage.BicycleGarageManager;
import BicycleGarage.Database;
import GUI.GUI;
import Utils.Logger;

public class GUITest {
	
	@Test
	public void testGUI(){
		new GUI(new Database(), new BicycleGarageManager(new Database(), new Logger("")));
	}
}
