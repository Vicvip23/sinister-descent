package com.sinisterorder.inventory;

import com.sinisterorder.handler.InventoryManagerInterface;
import com.sinisterorder.item.Item;

import java.util.ArrayList;

public class Inventory {
	public ItemManager itemManager;
	public WeaponManager weaponManager;
	public ConsumableManager consumableManager;

	Inventory() {
		this.itemManager = new ItemManager();
		this.weaponManager = new WeaponManager();
		this.consumableManager = new ConsumableManager();

	}

	public class ItemManager implements InventoryManagerInterface {
		private ArrayList<Item> items;

		public ArrayList<Item> list() {
			return items;
		}

		public void add(String itemId) {

		}

		public void get(int index) {

		}

		public void get(String itemId) {

		}

		public void remove(int index) {

		}

		public void remove(String itemId) {

		}

		public void remove(String itemId, int amount) {

		}
	}

	public class WeaponManager {

	}

	public class ConsumableManager {

	}
}
