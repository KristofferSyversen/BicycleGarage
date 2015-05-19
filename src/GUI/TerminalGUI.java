package GUI;

import java.util.InputMismatchException;
import java.util.Scanner;

import BicycleGarage.BicycleGarage;

public class TerminalGUI {
	Scanner scanner;
	BicycleGarage bg;

	public TerminalGUI() {
		scanner = new Scanner(System.in);
		bg = new BicycleGarage();
	}

	public static void main(String[] args) {
		new TerminalGUI().run();
	}

	public void run() {
		boolean exit = false;
		while (!exit) {
			printOptions();
			int option = parseCommand();
			
			switch (option) {
				case 1:		//TODO
					
				//...
					
				case 5: exit = true;
						break;
			}
		}
	}

	public int parseCommand() {
		int choice = -1;
		while (choice == -1) {
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid option! Try again.");
				printOptions();
				scanner = new Scanner(System.in);
			}
		}
		return choice;
	}

	private void printOptions() {
		String s = "";
		s += "1. Add user\n";
		s += "2. Add bicycle\n";
		s += "3. Print barcode\n";
		s += "4. Find or show\n";
		s += "5. Other\n";
		s += "6. Exit";
		System.out.println(s);
	}

	private void printFindOptions(){
		String s = "";
		s += "1. Find user\n";
		s += "2. Find bicycle\n";
		s += "3. Find bicycle in garage";
		s += "4. Show all users";
		s += "5. Show all bicycles";
		s += "6. Show all bicycles in garage";
		s += "7. Back";
		System.out.println(s);
	}
	
	private void printOtherOptions() {
		String s = "";
		s += "1. Remove user\n";
		s += "2. Remove bicycle\n";
		s += "3. Add bicycle to garage\n";
		s += "4. Remove bicycle from garage\n";
		s += "5. Back";
		System.out.println(s);
	}
}
