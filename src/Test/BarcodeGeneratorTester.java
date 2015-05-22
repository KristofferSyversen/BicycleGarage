package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Utils.BarcodeGenerator;

public class BarcodeGeneratorTester {

	@Test
	public void generateMultipleBarcodes() {
		ArrayList<String> codes = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			codes.add(BarcodeGenerator.getCode());
		}
		for (Integer i = 0; i < 5; i++) {
			String zeros="";
			while(i.SIZE <5){
				zeros += "0";
			}
			
			assertTrue("Expected: " + "0000" + i + " but was " + codes.get(i),
					codes.get(i).equals(i + "0000"));
		}
	}
}
