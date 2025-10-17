package com.sinisterorder.item;

import java.io.FileReader;

import com.google.gson.Gson;

public class ItemFactory {
	
	Gson gson = new Gson();
	
	public GenericItem fromJson(String itemId, String type){

		switch (type) {
			case "item":
				try {
					FileReader reader = new FileReader("src/main/resources/item/item.json");
					GenericItem[] items = gson.fromJson(reader, Item[].class);

					for (GenericItem item : items) {
						if(itemId.equals(item.getId())){
						return item;
						}
					}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "weapon":
				try {
					FileReader reader = new FileReader("src/main/resources/item/weapon.json");
					GenericItem[] weapons = gson.fromJson(reader, Weapon[].class);

					for (GenericItem weapon : weapons) {
						if(itemId.equals(weapon.getId())){
							return weapon;
						}
					}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "consumable":
				try {
					FileReader reader = new FileReader("src/main/resources/item/consumable.json");
					GenericItem[] consumables = gson.fromJson(reader, Consumable[].class);

					for (GenericItem consumable : consumables) {
						if(itemId.equals(consumable.getId())){
						return consumable;
					}
				}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			default:
				return null;
		}
		return null;
	}
}
