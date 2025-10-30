package com.sinisterorder.otherutils;

import java.util.Random;

public abstract class OtherUtils {
	private static Random random = new Random();

	// Roll for a 1 in x outcome
	public static boolean chance(int chance) {
		return random.nextInt(chance) == 0 ? true : false;
	}
}
