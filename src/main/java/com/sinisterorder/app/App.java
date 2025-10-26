package com.sinisterorder.app;

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
		MainMenu.run(shop);
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
