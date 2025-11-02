package com.sinisterorder.inventory;

import com.sinisterorder.handler.InventoryManagerInterface;
import com.sinisterorder.item.*;

import java.util.ArrayList;

// Seems big until you realize it's all getters, setters and nearly identical managers.
public class Inventory {
	public ItemManager itemManager;
	public WeaponManager weaponManager;
	public ConsumableManager consumableManager;
	public PurseManager purseManager;

	public Inventory() {
		this.itemManager = new ItemManager();
		this.weaponManager = new WeaponManager();
		this.consumableManager = new ConsumableManager();
		this.purseManager = new PurseManager();
	}

	public void wipe() {
		itemManager.items.clear();
		weaponManager.weapons.clear();
		weaponManager.equippedWeapon = null;
		consumableManager.consumables.clear();
	}

	// Subclass for managing misc items
	public class ItemManager implements InventoryManagerInterface {
		private ArrayList<GenericItem> items = new ArrayList<GenericItem>();

		public ArrayList<GenericItem> list() {
			return items;
		}

		public void add(String itemId) {
			items.add(ItemFactory.fromJson(itemId));
		}

		public void add(Item item) {
			items.add(item);
		}

		public Item get(int index) {
			return (Item) items.get(index);
		}

		// TODO: Rework pointless method (istg how did I miss this when reviewing this)
		public ArrayList<Integer> get(String itemId) {
			ArrayList<Integer> listInt = new ArrayList<Integer>();

			for(int i = 0; i< items.size(); i++) {
				if(items.get(i).getId().equals(itemId)) {
					listInt.add(i);
				}
			}

			return listInt;
		}

		// TODO: add out of bounds check
		public void remove(int index) {
			items.remove(index);
		}

		public void remove(String itemId) {

			for(int i = 0; i < items.size(); i++) {
				if(items.get(i).getId().equals(itemId)) {
					items.remove(i);
					break;
				}
			}
		}

		// Remove given amount of items with given ID
		public void remove(String itemId, int amount) {

			for(int i = items.size() - 1; i >= 0 && amount > 0; --i, --amount) {
				if(items.get(i).getId().equals(itemId)) {
					items.remove(i);
				}
			}
		}
	}

	// Not going into detail with methods shared with ItemManager
	public class WeaponManager implements InventoryManagerInterface {
		private ArrayList<GenericItem> weapons = new ArrayList<GenericItem>();
		private Weapon equippedWeapon;

		public ArrayList<GenericItem> list() {
			return weapons;
		}

		public void add(String weaponId) {
			weapons.add(ItemFactory.fromJson(weaponId));
		}

		public void add(Weapon weapon) {
			weapons.add(weapon);
		}

		public Weapon get(int index) {
			return (Weapon) weapons.get(index);
		}

		// TODO: Another pointless method to rework
		public ArrayList<Integer> get(String weaponId) {
			ArrayList<Integer> listInt = new ArrayList<Integer>();

			for(int i = 0; i< weapons.size(); i++) {
				if(weapons.get(i).getId().equals(weaponId)) {
					listInt.add(i);
				}
			}

			return listInt;
		}

		public void remove(int index) {
			weapons.remove(index);
		}

		public void remove(String weaponId) {

			for(int i = 0; i<weapons.size(); i++) {
				if(weapons.get(i).getId().equals(weaponId)) {
					weapons.remove(i);
					break;
				}
			}
		}

		public void remove(String weaponId, int amount) {

			for(int i = weapons.size() - 1; i >= 0 && amount > 0; --i, --amount) {
				if(weapons.get(i).getId().equals(weaponId)) {
					weapons.remove(i);
				}
			}
		}

		// Set equipped weapon and remove it from inventory contents
		public void equip(int weaponIndex) {
			equippedWeapon = (Weapon) weapons.get(weaponIndex);
			weapons.remove(weaponIndex);
		}

		// Remove equipped weapon and put it back in inventory
		public void unequip() {
			if(equippedWeapon != null) {
				weapons.add(equippedWeapon);
				equippedWeapon = null;
			}
		}

		public Weapon getEquippedWeapon() {
			return equippedWeapon;
		}
	}

	// Not going into detail with methods shared with ItemManager
	public class ConsumableManager implements InventoryManagerInterface {
		private ArrayList<GenericItem> consumables = new ArrayList<GenericItem>();

		public ArrayList<GenericItem> list() {
			return consumables;
		}

		// Reduces amount of uses of given consumable and deletes it if uses are 0
		public void use(int index) {
			Consumable temp = (Consumable) consumables.get(index);
			temp.use();

			if(temp.getUses() == 0) {
				consumables.remove(index);
			}
		}

		public void add(String consumableId) {
			consumables.add(ItemFactory.fromJson(consumableId));
		}

		public void add(Consumable consumable) {
			consumables.add(consumable);
		}

		public Consumable get(int index) {
			return (Consumable) consumables.get(index);
		}

		// TODO: Rework pointless method
		public ArrayList<Integer> get(String consumableId) {
			ArrayList<Integer> listInt = new ArrayList<Integer>();

			for(int i = 0; i< consumables.size(); i++) {
				if(consumables.get(i).getId().equals(consumableId)) {
					listInt.add(i);
				}
			}

			return listInt;
		}

		public void remove(int index) {
			consumables.remove(index);
		}

		public void remove(String consumableId) {

			for(int i = 0; i<consumables.size(); i++){
				if(consumables.get(i).getId().equals(consumableId)) {
					consumables.remove(i);
					break;
				}
			}
		}

		public void remove(String consumableId, int amount) {

			for(int i = consumables.size() - 1; i >= 0 && amount > 0; --i, --amount){
				if(consumables.get(i).getId().equals(consumableId)) {
					consumables.remove(i);
				}
			}
		}

		public void printAll() {
			for (GenericItem item : consumables) {
				System.out.println(item.getName());
			}
		}
	}

	// Simple money handling
	// TODO: ensure money can't go negative
	public class PurseManager {
		private int money;

		PurseManager() {
			money = 0;
		}

		public void addMoney(int amnt) {
			money += amnt;
		}

		public void removeMoney(int amnt) {
			money -= amnt;
		}

		public void setMoney(int amnt) {
			money = amnt;
		}

		public int getMoney() {
			return money;
		}
	}
}
