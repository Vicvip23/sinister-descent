package com.sinisterorder.entity.npc;

import java.io.FileReader;

import com.google.gson.Gson;

public abstract class NpcFactory {
	static Gson gson = new Gson();
	public static Npc fromJson(String entityId) {

	try {
		FileReader reader = new FileReader("src/main/resources/enemy/enemy.json");
		Npc[] npcs = gson.fromJson(reader, Npc[].class);

		for (Npc npc : npcs) {
			if(entityId.equals(npc.getEntityId())) {
				npc.setHealth(npc.getMaxHealth());
				npc.createInventory();
				return npc;
			}
		}

		reader.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
}
