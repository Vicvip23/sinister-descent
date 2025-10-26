package com.sinisterorder.ui;

import com.sinisterorder.scene.Scene;

public class MainMenu {
	private static ChoiceMenu menu;
	private static Scene toRun;

	public static void run(Scene proceedingScene) {
		toRun = proceedingScene;
		MenuUtils.openingPoem();
		buildMenu();
	}

	private static void buildMenu() {
		menu = MenuFactory.createFromJson("main_menu");

		menu.addLabel("title", MenuUtils.titleText);

		menu.addAction("start", () -> {
			toRun.run();
		});

		menu.addAction("exit", () -> {
			System.out.println("See you next time.");
			System.exit(0);
		});
	}

}
