package com.sinisterorder.item;

import java.util.ArrayList;
import java.util.Random;

import com.sinisterorder.entity.Entity;

public class ItemUtils {
	private static Random random = new Random();

	// Helper enum
	public static enum ItemType {
		item,
		weapon,
		consumable
	};

	// Generate random items from pool
	// TODO: Rework to support weighed randomness
	public static Item[] generateBattleDrops(Entity killed) {
		String[] possibleDrops = killed.getDrops();
		ArrayList<GenericItem> drops = new ArrayList<>();
		
		if(possibleDrops.length > 0) {
			for(int i = 0; i < 3; ++i) {
				drops.add(ItemFactory.fromJson(possibleDrops[random.nextInt(possibleDrops.length)]));
			}
		} else return null;

		Item[] output = new Item[drops.size()];
		output = drops.toArray(output); 
		return output;
	}



	// Item index for item pools
	public static final String[] itemIdList = {
		// --
		// "test_item",

		// 0
		"weathered_gold"
	};

	// Weapon index for weapon pools
	public static final String[] weaponIdList = {
		// --
		// "test_weapon",

		// 0
		"wooden_stick"
	};

	// Consumable index for consumable pools
	public static final String[] consumableIdList = {
		// --
		// "test_potion",

		// 0
		"small_health_potion"
	};
}
