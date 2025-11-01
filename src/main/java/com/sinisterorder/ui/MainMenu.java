package com.sinisterorder.ui;

public class MainMenu {
	private static ChoiceMenu menu;

	public static void run() {
		//MenuUtils.openingPoem();
		buildMenu();
		menu.run();
	}

	private static void buildMenu() {
		menu = MenuFactory.createFromJson("main_menu");

		menu.addLabel("title", MenuUtils.titleText);

		menu.addAction("start", () -> {});

		menu.addAction("exit", () -> {
			System.out.println("See you next time.");
			System.exit(0);
		});
	}

}
