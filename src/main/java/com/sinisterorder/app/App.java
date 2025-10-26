package com.sinisterorder.app;


/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Player player = new Player();

		player.inventory.itemManager.add("test_item");
		player.inventory.itemManager.add("test_item");
		player.inventory.itemManager.add("test_item");
		player.inventory.itemManager.add("test_item");
		player.inventory.itemManager.add("test_item");
		player.inventory.itemManager.add("test_item");

		player.inventory.weaponManager.add("test_weapon");
		player.inventory.weaponManager.add("test_weapon");
		player.inventory.weaponManager.add("test_weapon");

		player.inventory.consumableManager.add("test_potion");
		player.inventory.consumableManager.add("test_potion");
		player.inventory.consumableManager.add("test_potion");
		player.inventory.consumableManager.add("test_potion");

		player.startInventoryManagerUi(true);
	}
}
