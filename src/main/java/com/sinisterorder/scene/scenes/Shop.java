package com.sinisterorder.scene.scenes;

import java.util.Random;

import com.sinisterorder.entity.player.Player;
import com.sinisterorder.inventory.Inventory;
import com.sinisterorder.item.ItemUtils;
import com.sinisterorder.scene.Scene;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.MenuFactory;

public class Shop extends Scene{
	private Player client;
	private Inventory inventory;
	private ChoiceMenu shopMenu;
	private static Random random = new Random();
	private int flavor;
	private boolean run;

	public Shop() {
		inventory = new Inventory();
	}

	public void setClient(Player client) {
		this.client = client;
	}

	public void run() {
		run = true;

		generateInventory();
		while(run) {
			buildMenu();
			shopMenu.run();
		}
	}

	private void generateInventory() {

		inventory.wipe();

		for(int i = 0; i < 4; i++) {
			inventory.weaponManager.add(ItemUtils.weaponIdList[random.nextInt(ItemUtils.weaponIdList.length)]);
		}

		for(int i = 0; i < 2; i++) {
			inventory.consumableManager.add(ItemUtils.consumableIdList[random.nextInt(ItemUtils.consumableIdList.length)]);
		}
	}

	private void buildMenu() {

		shopMenu = MenuFactory.create("shop_menu", "Shop");

		shopMenu.addLabel("welcome", "Welcome to the shop.\n");
		flavor = random.nextInt(3);
		switch (flavor) {
			case 0:
				shopMenu.addLabel("flavor1", "May these wares aid you in this sinister descent.\n");
				break;
			case 1:
				shopMenu.addLabel("flavor2", "What can I get ya?\n");
				break;
			case 2:
				shopMenu.addLabel("flavor3", "Be quick.\n");
				break;
		}

		shopMenu.addLabel("section_weapons", "\nOur weapons for sale:\n");

		for (int i = 1; i <= this.inventory.weaponManager.list().size(); ++i) {
			if(i % 3 == 0 && i != this.inventory.weaponManager.list().size()) {
				shopMenu.addLabel("" + i, String.format("%d. %s\n", i, this.inventory.weaponManager.get(i - 1).getName()));
			} else {
				shopMenu.addLabel("" + i, String.format("%d. %s\t", i, this.inventory.weaponManager.get(i - 1).getName()));
			}
		}

		shopMenu.addLabel("section_consumables", "\nOur consumables for sale:\n");

		for (int i = 1; i <= this.inventory.consumableManager.list().size(); ++i) {
			if(i % 3 == 0 && i != this.inventory.consumableManager.list().size()) {
				shopMenu.addLabel("" + i, String.format("%d. %s\n", i, this.inventory.consumableManager.get(i - 1).getName()));
			} else {
				shopMenu.addLabel("" + i, String.format("%d. %s\t", i, this.inventory.consumableManager.get(i - 1).getName()));
			}
		}

		shopMenu.createOption("buy_weapon", "Purchase weapon", () -> {
			shopMenu.createQuery("item_selector", "What'd ya like to buy, kid?", "free", 0, inventory.weaponManager.list().size() - 1);
			int selectedItem = shopMenu.query.run();

			if(inventory.weaponManager.get(selectedItem).getValue() > client.inventory.purseManager.getMoney()) {
				flavor = random.nextInt(3);

				switch (flavor) {
					case 0:
						System.out.println("No dice.");
						break;
					case 1:
						System.out.println("My wares ain't free.");
						break;
					case 2:
						System.out.println("Come back when you're a little richer.");
						break;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				client.inventory.weaponManager.add(inventory.weaponManager.get(selectedItem));
				client.inventory.purseManager.removeMoney(inventory.weaponManager.get(selectedItem).getValue());
				inventory.weaponManager.remove(selectedItem);
				System.out.println("Thank you for your purchase.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		shopMenu.createOption("buy_consumable", "Purchase consumable", () -> {
			shopMenu.createQuery("item_selector", "What'd ya like to buy, kid?", "free", 0, inventory.consumableManager.list().size() - 1);
			int selectedItem = shopMenu.query.run();

			if(inventory.consumableManager.get(selectedItem).getValue() > client.inventory.purseManager.getMoney()) {
				flavor = random.nextInt(3);

				switch (flavor) {
					case 0:
						System.out.println("No dice.");
						break;
					case 1:
						System.out.println("My wares ain't free.");
						break;
					case 2:
						System.out.println("Come back when you're a little richer.");
						break;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				client.inventory.consumableManager.add(inventory.consumableManager.get(selectedItem));
				client.inventory.purseManager.removeMoney(inventory.consumableManager.get(selectedItem).getValue());
				inventory.consumableManager.remove(selectedItem);
				System.out.println("Thank you for your purchase.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		shopMenu.createOption("open_client_inventory", "Open own inventory", () -> {
			client.startInventoryManagerUi(true);
		});

		shopMenu.createOption("continue", "\033[0;1mDescend.\033[0;0m", () -> {
			run = false;
		});
	}
}
