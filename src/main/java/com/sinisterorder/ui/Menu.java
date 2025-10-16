package com.sinisterorder.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.sinisterorder.handler.GenericActionHandler;

public class Menu {
	private String menuId;
	private String menuTitle;
	private ArrayList<MenuOption> options = new ArrayList<MenuOption>();

	public String getMenuId() {
		return menuId;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void addOption(MenuOption option) {
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

	public void display() {
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		boolean ranBefore = false;
		String titleText = String.format("%s  %s  %s", "----====", this.menuTitle, "====----");
		System.out.printf("%s\n\n", titleText);

		for(int i = 0; i < options.size(); i += 2) {
			try {
				System.out.printf("%d) %s\t%d) %s", i + 1, options.get(i).getName(), i + 2, options.get(i+1).getName());
			} catch (Exception e) {
				System.out.printf("%d) %s", i + 1, options.get(i).getName());
			}
			System.out.println();
		}
		while (input < 0 || input >= options.size()) {
			if(ranBefore){
				System.out.println("Invalid input, try again.");
			}
			while(!scanner.hasNextInt()){
				System.out.println("Invalid input, try again.");
				scanner.next();
			}
			ranBefore = true;
			input = scanner.nextInt() - 1;
		}
		addAction(titleText, null);
		scanner.close();
		options.get(input).runAction();
	}

	// TODO: Remove debugging feature before merging to main
	@Override
	public String toString() {
		return "Id: " + this.menuId + "; Title: " + this.menuTitle + "; Options: " + this.getOptionNames().toString();
	}
}