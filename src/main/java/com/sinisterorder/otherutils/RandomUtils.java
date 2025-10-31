package com.sinisterorder.otherutils;

import java.util.Random;

import com.sinisterorder.handler.RandomChoiceByWeightInterface;

public abstract class RandomUtils {
	private static Random random = new Random();

	// Roll for a 1 in [chance] outcome
	public static boolean chance(int chance) {
		return random.nextInt(chance) == 0 ? true : false;
	}

	// Pick an instance of possible outcomes based on their weights
	public static RandomChoiceByWeightInterface rollByWeight(RandomChoiceByWeightInterface[] possibilities) {
		int total = 0;

		for (RandomChoiceByWeightInterface item : possibilities) {
			total += item.getWeight();
		}

		int r = random.nextInt(total + 1);

		for (RandomChoiceByWeightInterface item : possibilities) {
			if(r < item.getWeight()) {
				return item;
			}
			r -= item.getWeight();
		}

		return possibilities[0];
	}
}
