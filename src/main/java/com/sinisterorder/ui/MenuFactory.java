package com.sinisterorder.ui;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

public abstract class MenuFactory {
	static private Gson gson = new Gson();
	static private FileReader reader;
	static private ChoiceMenu menu;

	static public ChoiceMenu create(String menuId, String menuTitle) {
		return new ChoiceMenu(menuId, menuTitle);
	}
	
	static public ChoiceMenu createFromJson(String menuId) {

		try {
			reader = new FileReader("src/main/resources/menu/" + menuId + "/root.json");
			menu = gson.fromJson(reader, ChoiceMenu.class);
			menu.initializeLists();
			
			String dirName = "src/main/resources/menu/" + menuId + "/option";
			Files.list(Paths.get(dirName)).sorted().forEach(MenuFactory::addOption);

			reader.close();

			return menu;
		} catch (Exception e) {
			System.err.println("Given menuId does not match any existing menuId");
			e.printStackTrace();
			return null;
		}
	}

	static private void addOption(Path option) {
		try {
			reader = new FileReader(option.toFile());
			menu.addOption(gson.fromJson(reader, MenuOption.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
