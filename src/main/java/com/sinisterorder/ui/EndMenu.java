package com.sinisterorder.ui;

// Helper class to hold an instance of the game over menu
// Define abstract class to prevent instancing
public abstract class EndMenu {
	private static ChoiceMenu menu;

	public static void run() {
		buildMenu();
		menu.run();
	}

	private static void buildMenu() {
		menu = MenuFactory.fromJson("end_menu");

		menu.addLabel("title", MenuUtils.skullArt);

		menu.setAction("exit", () -> {
			System.out.println("May you fare better in the future.");
			System.exit(0);
		});
	}

}
