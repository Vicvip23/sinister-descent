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
		"weathered_gold",
		// 1
		"fur",
		// 2
		"blue_gem",
		// 3
		"worn_earring",
		// 4
		"rusted_key",
		// 5
		"spider_silk",
		// 6
		"ancient_coin",
		// 7
		"cracked_potion",
		// 8
		"wolf_tooth",
		// 9
		"emerald_shard"
	};

	// Weapon index for weapon pools
	public static final String[] weaponIdList = {
		// --
		// "test_weapon",

		// 0
		"wooden_stick",
		// 1
		"rusty_dagger",
		// 2
		"bone_sword",
		// 3
		"iron_axe",
		// 4
		"spiked_mace",
		//5
		"bone_sword"
	};

	// Consumable index for consumable pools
	public static final String[] consumableIdList = {
		// --
		// "test_potion",

		// 0
		"small_health_potion",
		// 1
		"stamina_draught",
		// 2
		"antidote_vial",
		// 3
		"spicy_mushroom"
	};
}
