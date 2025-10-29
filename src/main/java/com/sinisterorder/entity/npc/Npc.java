package com.sinisterorder.entity.npc;

import com.sinisterorder.entity.Entity;
import com.sinisterorder.inventory.Inventory;

public class Npc extends Entity{
	public Npc() {
		createInventory();
	}

	public void createInventory() {
		this.inventory = new Inventory();
	}
	// TODO: implement Npc (enemy)
	@Override
	public void attack(Entity target) {
		// TODO Auto-generated method stub
		
	}
}