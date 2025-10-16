package com.sinisterorder.app;

import com.google.gson.Gson;
import com.sinisterorder.item.Item;
import com.sinisterorder.item.ItemFactory;
import com.sinisterorder.ui.*;
/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		MenuFactory menuFactory = new MenuFactory();
		Menu testMenu = menuFactory.create("testmenu");
		testMenu.addAction("optionone", () -> {
			System.out.println("say whaaat?");
		});

		testMenu.runOption("optionone");
		testMenu.display();
	}
}
