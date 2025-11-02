package com.sinisterorder.ui;

// Helper class to play the starting sequence and display the main menu
// Define class as abstract to prevent instancing
public abstract class MainMenu {
	private static ChoiceMenu menu;

	public static void run() {
		//MenuUtils.openingPoem();
		buildMenu();
		menu.run();
	}

	private static void buildMenu() {
		menu = MenuFactory.fromJson("main_menu");

		menu.addLabel("title", MenuUtils.titleText);

		menu.setAction("start", () -> {});

		menu.setAction("exit", () -> {
			System.out.println("See you next time.");
			System.exit(0);
		});
	}

}
