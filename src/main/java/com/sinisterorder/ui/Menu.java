package com.sinisterorder.ui;

import java.util.ArrayList;

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

	public ArrayList<String> getOptionNames() {
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < options.size(); i++) {
			output.add(options.get(i).getName());
		}

		return output;
	}

	public void runOption(String optionId) {
		for (MenuOption i : options) {
			if(i.getName().equals(optionId)) {
				i.runAction();
			}
		}
	}

	// TODO: Remove debugging feature before merging to main
	@Override
	public String toString() {
		return "Id: " + this.menuId + "; Title: " + this.menuTitle + "; Options: " + this.getOptionNames().toString();
	}
}