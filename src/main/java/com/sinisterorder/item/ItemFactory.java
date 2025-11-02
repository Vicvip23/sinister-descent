package com.sinisterorder.item;

import java.io.FileReader;

import com.google.gson.Gson;

public class ItemFactory {
	private static Gson gson = new Gson();
	private static FileReader reader;
	
	// Try to read through all 3 item data files, return one with matching ID
	public static GenericItem fromJson(String itemId) {
		
		try {
			reader = new FileReader("src/main/resources/item/item.json");
			GenericItem[] items = gson.fromJson(reader, Item[].class);

			for (GenericItem item : items) {
				if(itemId.equals(item.getId())) {
					return item;
				}
			}
					
			reader = new FileReader("src/main/resources/item/weapon.json");
			GenericItem[] weapons = gson.fromJson(reader, Weapon[].class);
	
			for (GenericItem weapon : weapons) {
				if(itemId.equals(weapon.getId())) {
					return weapon;
				}
			}
					
			reader = new FileReader("src/main/resources/item/consumable.json");
			GenericItem[] consumables = gson.fromJson(reader, Consumable[].class);
	
			for (GenericItem consumable : consumables) {
				if(itemId.equals(consumable.getId())) {
					return consumable;
				}
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// No item found
		return null;
	}
}

