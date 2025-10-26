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
		ChoiceMenu menu = MenuFactory.createFromJson("examplemenu");
		ChoiceMenu menu2 = MenuFactory.createFromJson("examplemenu2");
		System.out.println(menu);
		menu2.createQuery("query1", "Specify which", "free", 0, 10);
		menu.addAction("example1", () -> {
			System.out.println("example one");
			menu.setLabel("label_art", "aaa\n");
			System.out.println("ert");
			menu.run();
		});
		menu2.addAction("example1", () -> {
			System.out.println("here");
			int result = menu2.query.run();
			System.out.println(result);
		});
		menu2.addAction("example2", () -> {
			menu.run();
		});
		menu.addAction("example2", () -> {
			menu2.run();
		});
		menu.addLabel("label_art", """
		abcsdsdsdssd
		fsdsfsdf
		i hate people
		""");
		menu.run();
	}
}
