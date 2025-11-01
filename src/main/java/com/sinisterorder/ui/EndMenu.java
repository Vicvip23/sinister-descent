package com.sinisterorder.ui;

public class EndMenu {
	private static ChoiceMenu menu;

	public static void run() {
		buildMenu();
		menu.run();
	}

	private static void buildMenu() {
		menu = MenuFactory.createFromJson("end_menu");

		menu.addLabel("title", MenuUtils.skullArt);

		menu.addAction("exit", () -> {
			System.out.println("May you fare better in the future.");
			System.exit(0);
		});
	}

}
