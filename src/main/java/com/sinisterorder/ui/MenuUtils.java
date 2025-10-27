package com.sinisterorder.ui;

public class MenuUtils {
	static public final String inventoryArt = """	
			@@@@@@               @@@@@-
			@@  @@@@@@@@@@@@@@@@@@@  @%
			@@  @@               @@  @%
			@@  @@               @@  @%
			@@  @@               @@  @%
			@@  @@    @@@@@@@    @@  @%
			@@@@@@@@@@@%   @@@@@@@@@@@%
			 @@  @@   @%...@@   @@  @@ 
			 @@  @@    #@@#+    @@  @@ 
			 @@  @@      -      @@  @@ 
			 @@  @@             @@  @@ 
			 @@@@@@@@@@@@@@@@@@@@@@@@@""";

	static public final String titleText = """
  _________.__       .__          __                 ________                                     __   
 /   _____/|__| ____ |__| _______/  |_  ___________  \\______ \\   ____   ______ ____  ____   _____/  |_ 
 \\_____  \\ |  |/    \\|  |/  ___/\\   __\\/ __ \\_  __ \\  |    |  \\_/ __ \\ /  ___// ___\\/ __ \\ /    \\   __\\
 /        \\|  |   |  \\  |\\___ \\  |  | \\  ___/|  | \\/  |    `   \\  ___/ \\___ \\\\  \\__\\  ___/|   |  \\  |  
/_______  /|__|___|  /__/____  > |__|  \\___  >__|    /_______  /\\___  >____  >\\___  >___  >___|  /__|  
        \\/         \\/        \\/            \\/                \\/     \\/     \\/     \\/    \\/     \\/      
			""";

	static public void clear() {
		System.out.print("\033\143");
	}

	static public void separator() {
		System.out.println("\n==============================");
	}

	static private void printWithDelay(String text, int delay) {
		for (char symbol : text.toCharArray()) {
			System.out.print(symbol);
			wait(delay);
		}
	}

	static private void wait(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static protected void openingPoem() {
		clear();
		printWithDelay("Legends of old\n", 100);
		wait(250);
		printWithDelay("Have mentions untold,\n", 100);
		wait(250);
		printWithDelay("Of treasures that lay\n", 100);
		wait(250);
		printWithDelay("in deep dark's embrace.\n\n", 100);
		wait(400);
		printWithDelay("Those who set out\n", 100);
		wait(250);
		printWithDelay("To claim that fate whole,\n", 100);
		wait(250);
		printWithDelay("Must face their own\n", 100);
		wait(400);
		clear();
		printWithDelay("\033[0;1mSinister Descent.\033[0;0m", 150);
		wait(250);
	};
}
