package com.sinisterorder.scene.scenes;

import java.util.Random;

import com.sinisterorder.entity.player.Player;
import com.sinisterorder.inventory.Inventory;
import com.sinisterorder.item.ItemUtils;
import com.sinisterorder.scene.Scene;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.MenuFactory;
import com.sinisterorder.ui.MenuUtils;

public class Shop extends Scene{
	private Player client;
	private Inventory inventory;
	private ChoiceMenu shopMenu;
	private int flavor;
	private boolean run;
	private int weaponSelectionSize;
	private int consumableSelectionSize;
	private static Random random = new Random();

	public Shop() {
		inventory = new Inventory();
		weaponSelectionSize = 4;
		consumableSelectionSize = 2;
	}

	public Shop(int weaponSelectionSize, int consumableSelectionSize) {
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

		for(int i = 0; i < weaponSelectionSize; i++) {
			inventory.weaponManager.add(ItemUtils.weaponIdList[random.nextInt(ItemUtils.weaponIdList.length)]);
		}

		for(int i = 0; i < consumableSelectionSize; i++) {
			inventory.consumableManager.add(ItemUtils.consumableIdList[random.nextInt(ItemUtils.consumableIdList.length)]);
		}
	}

	private void buildMenu() {

		shopMenu = MenuFactory.create("shop_menu", "Shop");

		shopMenu.addLabel("welcome", "Welcome to the shop.\n");

		flavor = random.nextInt(3);
		switch (flavor) {
			case 0:
				shopMenu.addLabel("flavor", "May these wares aid you in this sinister descent.\n");
				break;
			case 1:
				shopMenu.addLabel("flavor", "What can I get ya?\n");
				break;
			case 2:
				shopMenu.addLabel("flavor", "Be quick.\n");
				break;
		}

		shopMenu.addLabel("section_weapons", "\nOur weapons for sale:\n");

		for (int i = 1; i <= this.inventory.weaponManager.list().size(); ++i) {
			if(i % 3 == 0 && i != this.inventory.weaponManager.list().size()) {
				shopMenu.addLabel("" + i, String.format("%d. %s // %d\n", i, this.inventory.weaponManager.get(i - 1).getName(), this.inventory.weaponManager.get(i - 1).getValue()));
			} else {
				shopMenu.addLabel("" + i, String.format("%d. %s // %d\t", i, this.inventory.weaponManager.get(i - 1).getName(), this.inventory.weaponManager.get(i - 1).getValue()));
			}
		}

		shopMenu.addLabel("section_consumables", "\nOur consumables for sale:\n");

		for (int i = 1; i <= this.inventory.consumableManager.list().size(); ++i) {
			if(i % 3 == 0 && i != this.inventory.consumableManager.list().size()) {
				shopMenu.addLabel("" + i, String.format("%d. %s // %d\n", i, this.inventory.consumableManager.get(i - 1).getName(), this.inventory.consumableManager.get(i - 1).getValue()));
			} else {
				shopMenu.addLabel("" + i, String.format("%d. %s // %d\t", i, this.inventory.consumableManager.get(i - 1).getName(), this.inventory.consumableManager.get(i - 1).getValue()));
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

				MenuUtils.wait(1000);
			} else {
				client.inventory.weaponManager.add(inventory.weaponManager.get(selectedItem));
				client.inventory.purseManager.removeMoney(inventory.weaponManager.get(selectedItem).getValue());
				inventory.weaponManager.remove(selectedItem);
				System.out.println("Thank you for your purchase.");
				MenuUtils.wait(1000);
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
				
				MenuUtils.wait(1000);
			} else {
				client.inventory.consumableManager.add(inventory.consumableManager.get(selectedItem));
				client.inventory.purseManager.removeMoney(inventory.consumableManager.get(selectedItem).getValue());
				inventory.consumableManager.remove(selectedItem);
				System.out.println("Thank you for your purchase.");
				MenuUtils.wait(1000);
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
