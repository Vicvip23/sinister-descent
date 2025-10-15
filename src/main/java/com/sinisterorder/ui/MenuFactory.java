package com.sinisterorder.ui;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;

public class MenuFactory {
	Gson gson = new Gson();

	public Menu create(String menuId){
		Menu menu;
		try {
			System.out.println(menuId);
			FileReader reader = new FileReader("src/main/resources/menu/" + menuId + "/root.json");
            	menu = gson.fromJson(reader, Menu.class);
			File path = new File("src/main/resources/menu/" + menuId + "/option");
			System.out.println(path);
			File[] options = path.listFiles();
			System.err.println(path.listFiles());
			
			for (File file : options) {
				reader = new FileReader(file);
				MenuOption option = gson.fromJson(reader, MenuOption.class);
				menu.addOption(option);
			}
			
			reader.close();
			return menu;
		} catch (Exception e) {
			System.err.println("Given menuId does not match any existing menuId");
			e.printStackTrace();
			return null;
		}
	}
}
