package com.sinisterorder.entity.player;

import java.util.ArrayList;

import com.sinisterorder.attack.Attack;
import com.sinisterorder.attack.AttackFactory;
import com.sinisterorder.attack.AttacksetFactory;
import com.sinisterorder.entity.Entity;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.MenuFactory;
import com.sinisterorder.ui.MenuUtils;

public class Player extends Entity{
	private ChoiceMenu playerMenu;
	private boolean runPrimary;
	private boolean runSecondary;
	private boolean unlockSell;

	public Player() {
		this.createInventory();
		playerMenu = MenuFactory.create("player_menu", "");
	}

	public void startInventoryManagerUi(boolean unlockSell) {
		runPrimary = true;
		this.unlockSell = unlockSell;

		while(runPrimary) {
			playerMenu.setTitle("Your Inventory");
			playerMenu.addLabel("inventory_art", MenuUtils.inventoryArt);

			if(inventory.weaponManager.getEquippedWeapon() == null) {
				playerMenu.addLabel("balance", "\nWeapons: " + inventory.weaponManager.list().size());
			} else {
				playerMenu.addLabel("balance", "\nWeapons: " + (inventory.weaponManager.list().size() + 1));
			}

			playerMenu.addLabel("balance", "\nConsumables: " + inventory.consumableManager.list().size());
			playerMenu.addLabel("balance", "\nItems: " + inventory.itemManager.list().size());
			playerMenu.addLabel("balance", "\nBalance: " + inventory.purseManager.getMoney());

			playerMenu.createOption("weapon_manager", "Manage weapons", () -> {
				playerMenu.fullWipe();
				displayWeapons();
			});

			playerMenu.createOption("consumable_manager", "Manage consumables", () -> {
				playerMenu.fullWipe();
				displayConsumables();
			});

			playerMenu.createOption("item_manager", "Manage misc items", () -> {
				playerMenu.fullWipe();
				displayItems();
			});

			playerMenu.createOption("return", "Return", () -> {
				runPrimary = false;
			});

			playerMenu.run();
			playerMenu.fullWipe();
		}
	}

	private void displayWeapons() {
		runSecondary = true;

		while(runSecondary) {
			playerMenu.setTitle("Weapons in inventory");

			if(inventory.weaponManager.getEquippedWeapon() == null) {
				playerMenu.addLabel("equipped", String.format("%s: %s\n", "Equipped weapon", "none"));
			} else {
				playerMenu.addLabel("equipped", String.format("%s: %s\n", "Equipped weapon", inventory.weaponManager.getEquippedWeapon().getName()));
			}

			for (int i = 1; i <= this.inventory.weaponManager.list().size(); ++i) {
				if(i % 3 == 0 && i != this.inventory.weaponManager.list().size()) {
					playerMenu.addLabel("" + i, String.format("%d. %s\n", i, this.inventory.weaponManager.get(i - 1).getName()));
				} else {
					playerMenu.addLabel("" + i, String.format("%d. %s\t", i, this.inventory.weaponManager.get(i - 1).getName()));
				}
			}

			if(inventory.weaponManager.list().size() > 0) {
				playerMenu.createOption("more_info", "More info", () -> {
					playerMenu.createQuery("item_selector", "Input which weapon you'd like more information about", "free", 0, inventory.weaponManager.list().size() - 1);
					int selectedItem = playerMenu.query.run();

					playerMenu.fullWipe();
					playerMenu.setTitle("Details");

					playerMenu.addLabel("", String.format("Name: %s\nSell value: %d\nDamage: %d\nLevel: %d\nDescription: %s", 
						inventory.weaponManager.get(selectedItem).getName(),
						inventory.weaponManager.get(selectedItem).getValue(),
						inventory.weaponManager.get(selectedItem).getDamage(),
						inventory.weaponManager.get(selectedItem).getLevel(),
						inventory.weaponManager.get(selectedItem).getDescription()
					));

					playerMenu.createOption("return", "Return", () -> {});
					playerMenu.run();
				});

				playerMenu.createOption("equip", "Equip", () -> {
					playerMenu.createQuery("item_selector", "Input which weapon you'd like to equip", "free", 0, inventory.weaponManager.list().size() - 1);
					int selectedItem = playerMenu.query.run();

					this.inventory.weaponManager.equip(selectedItem);
				});

				playerMenu.createOption("unequip", "Unequip", () -> {
					this.inventory.weaponManager.unequip();
				});

				if(unlockSell) {
					playerMenu.createOption("sell", "Sell weapon", () -> {
						playerMenu.createQuery("item_selector", "Input which weapon you'd like to sell", "free", 0, inventory.weaponManager.list().size() - 1);
						int selectedItem = playerMenu.query.run();

						this.inventory.purseManager.addMoney(this.inventory.weaponManager.get(selectedItem).getValue() / 2);
						this.inventory.weaponManager.remove(selectedItem);
					});
				}
			}


			playerMenu.createOption("return", "Return", () -> {
				runSecondary = false;
			});

			playerMenu.run();
			playerMenu.fullWipe();
		}
	}
	
	private void displayItems() {
		runSecondary = true;

		while(runSecondary) {
			playerMenu.setTitle("Misc items in inventory");

			for (int i = 1; i <= this.inventory.itemManager.list().size(); ++i) {
				if(i % 3 == 0 && i != this.inventory.itemManager.list().size()) {
					playerMenu.addLabel("" + i, String.format("%d. %s\n", i, this.inventory.itemManager.get(i - 1).getName()));
				} else {
					playerMenu.addLabel("" + i, String.format("%d. %s\t", i, this.inventory.itemManager.get(i - 1).getName()));
				}
			}

			if(inventory.itemManager.list().size() > 0) {
				playerMenu.createOption("more_info", "More info", () -> {
					playerMenu.createQuery("item_selector", "Input which item you'd like more information about", "free", 0, inventory.itemManager.list().size() - 1);
					int selectedItem = playerMenu.query.run();

					playerMenu.fullWipe();
					playerMenu.setTitle("Details");

					playerMenu.addLabel("", String.format("Name: %s\nSell value: %d\nLevel: %d\nDescription: %s", 
						inventory.itemManager.get(selectedItem).getName(),
						inventory.itemManager.get(selectedItem).getValue(),
						inventory.itemManager.get(selectedItem).getLevel(),
						inventory.itemManager.get(selectedItem).getDescription()
					));

					playerMenu.createOption("return", "Return", () -> {});
					playerMenu.run();
				});

				if(unlockSell) {
					playerMenu.createOption("sell", "Sell item", () -> {
						playerMenu.createQuery("item_selector", "Input which item you'd like to sell", "free", 0, inventory.itemManager.list().size() - 1);
						int selectedItem = playerMenu.query.run();

						this.inventory.purseManager.addMoney(this.inventory.itemManager.get(selectedItem).getValue() / 2);
						this.inventory.itemManager.remove(selectedItem);
					});
				}
			}

			playerMenu.createOption("return", "Return", () -> {
				runSecondary = false;
			});

			playerMenu.run();
			playerMenu.fullWipe();
		}
	}

	private void displayConsumables() {
		runSecondary = true;

		while(runSecondary) {
			playerMenu.setTitle("Consumables in inventory");

			for (int i = 1; i <= this.inventory.consumableManager.list().size(); ++i) {
				if(i % 3 == 0 && i != this.inventory.consumableManager.list().size()) {
					playerMenu.addLabel("" + i, String.format("%d. %s\n", i, this.inventory.consumableManager.get(i - 1).getName()));
				} else {
					playerMenu.addLabel("" + i, String.format("%d. %s\t", i, this.inventory.consumableManager.get(i - 1).getName()));
				}
			}

			if(inventory.consumableManager.list().size() > 0) {
				playerMenu.createOption("more_info", "More info", () -> {
					playerMenu.createQuery("item_selector", "Input which consumable you'd like more information about", "free", 0, inventory.consumableManager.list().size() - 1);
					int selectedItem = playerMenu.query.run();

					playerMenu.fullWipe();
					playerMenu.setTitle("Details");

					playerMenu.addLabel("", String.format("Name: %s\nSell value: %d\nEffect description: %s\nUses left: %d\nLevel: %d\nDescription: %s", 
						inventory.consumableManager.get(selectedItem).getName(),
						inventory.consumableManager.get(selectedItem).getValue(),
						inventory.consumableManager.get(selectedItem).getEffectDescription(),
						inventory.consumableManager.get(selectedItem).getUses(),
						inventory.consumableManager.get(selectedItem).getLevel(),
						inventory.consumableManager.get(selectedItem).getDescription()
					));

					playerMenu.createOption("return", "Return", () -> {});
					playerMenu.run();
				});

				playerMenu.createOption("use", "Use consumable", () -> {
					playerMenu.createQuery("item_selector", "Input which consumable you'd like to use", "free", 0, inventory.consumableManager.list().size() - 1);
					int selectedItem = playerMenu.query.run();

					inventory.consumableManager.use(selectedItem);
				});

				if(unlockSell) {
					playerMenu.createOption("sell", "Sell consumable", () -> {
						playerMenu.createQuery("item_selector", "Input which consumable you'd like to sell", "free", 0, inventory.consumableManager.list().size() - 1);
						int selectedItem = playerMenu.query.run();

						this.inventory.purseManager.addMoney(this.inventory.consumableManager.get(selectedItem).getValue() / 2);
						this.inventory.consumableManager.remove(selectedItem);
					});
				}
			}

			playerMenu.createOption("return", "Return", () -> {
				runSecondary = false;
			});

			playerMenu.run();
			playerMenu.fullWipe();
		}
	}

	@Override
	public void attack(Entity target) {
		ArrayList<Attack> availableAttacks = new ArrayList<>();

		if(inventory.weaponManager.getEquippedWeapon() != null) {
			for (String attackId : AttacksetFactory.fromJson(inventory.weaponManager.getEquippedWeapon().getAttackset()).getAttackset()) {
				availableAttacks.add(AttackFactory.fromJson(attackId));
			}
			for (String attackId : inventory.weaponManager.getEquippedWeapon().getUniqueAttacks()) {
				availableAttacks.add(AttackFactory.fromJson(attackId));	
			}
		} else {
			inventory.weaponManager.add("fist");
			inventory.weaponManager.equip(0);
			availableAttacks.add(AttackFactory.fromJson("punch"));
		}

		playerMenu.createQuery("attack_selector", "Pick attack to use", "free", 0, availableAttacks.size() - 1);

		playerMenu.fullWipe();
		playerMenu.setTitle("Attacks");

		for (int i = 1; i <= availableAttacks.size(); ++i) {
			if(i % 3 == 0 && i != availableAttacks.size()) {
				playerMenu.addLabel("" + i, String.format("%d. %s\n", i, availableAttacks.get(i - 1).getAttackName()));
			} else {
				playerMenu.addLabel("" + i, String.format("%d. %s\t", i, availableAttacks.get(i - 1).getAttackName()));
			}
		}

		playerMenu.createOption("pick_attack", "Choose attack", () -> {
			Attack attack = availableAttacks.get(playerMenu.query.run());
		});
		playerMenu.run();
	}
}
