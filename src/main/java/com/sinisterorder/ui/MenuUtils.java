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

	static public void clear() {
		System.out.print("\033\143");
	}

	static public void separator() {
		System.out.println("\n==============================");
	}
}
