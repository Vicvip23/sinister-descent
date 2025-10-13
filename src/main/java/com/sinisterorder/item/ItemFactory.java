package com.sinisterorder.item;

import java.io.FileReader;

import com.google.gson.Gson;

public class ItemFactory {
	
	Gson gson = new Gson();
	
	public Item fromJson(String itemId, String type){

		switch (type) {
			case "item":
				try {
					FileReader reader = new FileReader("src/main/resources/item/item.json");
					Item[] items = gson.fromJson(reader, Item[].class);

					for (Item item : items) {
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
					Weapon[] items = gson.fromJson(reader, Weapon[].class);

					for (Weapon item : items) {
						if(itemId.equals(item.getId())){
							return item;
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
					Consumable[] items = gson.fromJson(reader, Consumable[].class);

					for (Consumable item : items) {
						if(itemId.equals(item.getId())){
						return item;
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
