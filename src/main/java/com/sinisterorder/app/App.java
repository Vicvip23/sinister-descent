package com.sinisterorder.app;

import com.sinisterorder.entity.Entity;
import com.sinisterorder.entity.npc.Npc;
import com.sinisterorder.entity.player.Player;
import com.sinisterorder.scene.scenes.*;
import com.sinisterorder.ui.MainMenu;
/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Shop shop = new Shop();
		Player player = new Player();
		Battle battle = new Battle();
		shop.setClient(player);
		MainMenu.run();
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		
		shop.run();
		Entity enemy = new Npc();
		enemy.setHealth(20);
		enemy.setMaxHealth(20);
		enemy.setArmor(1);
		enemy.setName("Test Wolf");
		enemy.inventory.weaponManager.add("wooden_stick");
		enemy.inventory.weaponManager.equip(0);
		battle.run(player, enemy);
		
	}
}
