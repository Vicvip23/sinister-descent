package com.sinisterorder.app;

import com.sinisterorder.entity.player.Player;
import com.sinisterorder.scene.scenes.*;
/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Shop shop = new Shop();
		Player player = new Player();
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");
		player.inventory.itemManager.add("weathered_gold");

		shop.setClient(player);

		shop.run();
	}
}
