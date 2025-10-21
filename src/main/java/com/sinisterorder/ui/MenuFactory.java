package com.sinisterorder.ui;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class MenuFactory {
	Gson gson = new Gson();
	FileReader reader;
	ChoiceMenu menu;

	public ChoiceMenu create(String menuId) {

		try {
			reader = new FileReader("src/main/resources/menu/" + menuId + "/root.json");
			this.menu = gson.fromJson(reader, ChoiceMenu.class);
			
			String dirName = "src/main/resources/menu/" + menuId + "/option";
			Files.list(Paths.get(dirName)).sorted().forEach(this::addOption);

			reader.close();

			return menu;
		} catch (Exception e) {
			System.err.println("Given menuId does not match any existing menuId");
			e.printStackTrace();
			return null;
		}
	}

	private void addOption(Path option) {
		try {
			reader = new FileReader(option.toFile());
			menu.addOption(gson.fromJson(reader, MenuOption.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
