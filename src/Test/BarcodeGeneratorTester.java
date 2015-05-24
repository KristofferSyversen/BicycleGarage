package Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Utils.BarcodeGenerator;

public class BarcodeGeneratorTester {

	@Before
	public void setup() {
		BarcodeGenerator.setCodeNbr(0);
	}

	@Test
	public void generateMultipleBarcodes() {
		ArrayList<String> codes = new ArrayList<String>();
		for (int i = 0; i < 100000; i++) {
			codes.add(BarcodeGenerator.getCode());
		}
		for(Integer i = 0; i < 100000; i++) {
			String zeros = "";
			while ((zeros + i).toString().length() < 5) {
				zeros += "0";
			}

			assertTrue("Expected " + i + zeros + " but was " + codes.get(i),
					codes.get(i).equals(i + zeros));
		}
		
		for(int i = 0; i < 100000; i++){
			String zeros = "";
			while ((zeros + i).toString().length() < 5) {
				zeros += "0";
			}
			BarcodeGenerator.setBarcodeAsAvailable(zeros + i);
		}
	}

	@Test
	public void generateBarcodeAfterSettingNewCodeNbr() {
		BarcodeGenerator.setCodeNbr(12345);
		String code = BarcodeGenerator.getCode();
		assertTrue("Expected 12345 but was " + code, code.equals("12345"));
		BarcodeGenerator.setBarcodeAsAvailable("12345");
	}
}
