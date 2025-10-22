package com.sinisterorder.app;

import com.google.gson.Gson;
import com.sinisterorder.inventory.Inventory;
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
		ChoiceMenu menu = menuFactory.createFromJson("examplemenu");
		ChoiceMenu menu2 = menuFactory.createFromJson("examplemenu2");
		System.out.println(menu);
		menu.addAction("example1", () -> {
			System.out.println("example one");
		});
		menu.addAction("example2", () -> {
			menu2.run();
		});
		menu.addLabel("label_art", """
		abcsdsdsdssd
		fsdsfsdf
		i hate people
		""");
		menu2.addAction("example1", () -> {
			System.out.println("here");
		});
		menu2.addAction("example2", () -> {
			System.out.println("here2");
		});
		menu.run();
	}
}
