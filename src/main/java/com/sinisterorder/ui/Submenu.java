package com.sinisterorder.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.sinisterorder.handler.GenericActionHandler;

public class Submenu {
	private String submenuId;
	private String submenuTitle;
	private String inputMode;
	private int[] inputRange;
	private ArrayList<MenuOption> options = new ArrayList<MenuOption>();
	static private Scanner scanner = new Scanner(System.in);

	Submenu(String submenuId, String submenuTitle, String inputMode) throws Exception {
		this.submenuId = submenuId;
		this.submenuTitle = submenuTitle;
		this.inputMode = inputMode;
		this.options = new ArrayList<MenuOption>();

		if(inputMode == "free") {
			Exception exception = new Exception("Input mode \"free\" specified yet no range values were given");
			throw exception;
		}
	}
	
	Submenu(String submenuId, String submenuTitle, String inputMode, int inputRangeStart, int inputRangeEnd) {
		this.submenuId = submenuId;
		this.submenuTitle = submenuTitle;
		this.inputMode = inputMode;
		this.options = new ArrayList<MenuOption>();

		if(inputMode == "free") {
			inputRange[0] = inputRangeStart;
			inputRange[1] = inputRangeEnd;
		}
	}

	protected void initializeLists() {
		options = new ArrayList<MenuOption>();
	}

	public String getSubmenuId() {
		return submenuId;
	}

	public String getSubmenuTitle() {
		return submenuTitle;
	}

	protected void addOption(MenuOption option) {
		if(this.options == null) {
			this.options = new ArrayList<MenuOption>();
		}

		this.options.add(option);
	}

	public void addAction(String optionId, GenericActionHandler action) {
		for (MenuOption menuOption : options) {
			if(menuOption.getOptionId().equals(optionId)) {
				menuOption.setAction(action);
			}
		}
	}

	public ArrayList<String> getOptionNames() {
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < options.size(); i++) {
			output.add(options.get(i).getName());
		}

		return output;
	}

	public void runOption(String optionId) {
		for (MenuOption option : options) {
			if(option.getOptionId().equals(optionId)) {
				option.runAction();
			}
		}
	}

	private void displayOptionMode() {
		
		String titleText = String.format("%s  %s  %s", "--==", this.submenuTitle, "==--");
		System.out.printf("%s\n\n", titleText);

		for(int i = 0; i < options.size(); i += 2) {
			try {
				System.out.printf("%d) %s\t%d) %s", i + 1, options.get(i).getName(), i + 2, options.get(i+1).getName());
			} catch (Exception e) {
				System.out.printf("%d) %s", i + 1, options.get(i).getName());
			}
			System.out.println();
		}
	}

	private void displayFreeMode() {
		
	}

	public void run() {
		int input = -1;
		boolean ranBefore = false;
		displayOptionMode();
		while (input < 0 || input >= options.size()) {
			if(ranBefore){
				MenuUtils.clear();
				displayOptionMode();
				System.out.println("Invalid input, try again.");
			}
			while(!scanner.hasNextInt()){
				displayOptionMode();
				System.out.println("Invalid input, try again.");
				scanner.next();
			}
			ranBefore = true;
			input = scanner.nextInt() - 1;
		}

		options.get(input).runAction();
	}
}