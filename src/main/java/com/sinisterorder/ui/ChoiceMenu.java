package com.sinisterorder.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.sinisterorder.handler.GenericActionHandler;

public class ChoiceMenu {
	private String menuId;
	private String menuTitle;
	private ArrayList<MenuOption> options = new ArrayList<MenuOption>();
	private ArrayList<Label> labels = new ArrayList<Label>();

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

	public void addLabel(String labelId, String text) {
		labels.add(new Label(labelId, text));
	}

	public void setLabel(String labelId, String text) {
		for (Label label : labels) {
			if(label.getLabelId() == labelId) {
				label.setText(text);
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
		
		MenuUtils.clear();
		String titleText = String.format("%s  %s  %s", "----====", this.menuTitle, "====----");
		System.out.printf("%s\n\n", titleText);

		if(labels.size() > 0) {
			MenuUtils.separator();
			for(int i = 0; i < options.size(); i += 2) {
				try {
					System.out.printf("%s\t%s", labels.get(i).getText(), labels.get(i+1).getText());
				} catch (Exception e) {
					System.out.printf("%s", labels.get(i).getText());
				}
				System.out.println();
			}
			MenuUtils.separator();
			System.out.println();
		}

		for(int i = 0; i < options.size(); i += 2) {
			try {
				System.out.printf("%d) %s\t%d) %s", i + 1, options.get(i).getName(), i + 2, options.get(i+1).getName());
			} catch (Exception e) {
				System.out.printf("%d) %s", i + 1, options.get(i).getName());
			}
			System.out.println();
		}
		
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		boolean ranBefore = false;
		display();
		while (input < 0 || input >= options.size()) {
			if(ranBefore){
				MenuUtils.clear();
				display();
				System.out.println("Invalid input, try again.");
			}
			while(!scanner.hasNextInt()){
				display();
				System.out.println("Invalid input, try again.");
				scanner.next();
			}
			ranBefore = true;
			input = scanner.nextInt() - 1;
		}

		scanner.close();
		options.get(input).runAction();
	}
}