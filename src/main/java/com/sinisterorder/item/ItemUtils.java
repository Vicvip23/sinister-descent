package com.sinisterorder.item;

import java.util.ArrayList;
import java.util.Random;

import com.sinisterorder.entity.Entity;

public class ItemUtils {
	private static Random random = new Random();

	public static enum ItemType {
		item,
		weapon,
		consumable
	};

	public static final String[] itemIdList = {
		// 0
		"test_item",
		// 1
		"weathered_gold"
	};

	public static final String[] weaponIdList = {
		// 0
		"test_weapon",
		// 1
		"wooden_stick"
	};

	public static final String[] consumableIdList = {
		// 0
		"test_potion",
		// 1
		"small_health_potion"
	};

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
}
