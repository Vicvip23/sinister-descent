package com.sinisterorder.inventory;

import com.sinisterorder.handler.InventoryManagerInterface;
import com.sinisterorder.item.*;

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

	ItemFactory itemFactory = new ItemFactory();

	public class ItemManager implements InventoryManagerInterface {

		private ArrayList<GenericItem> items = new ArrayList<GenericItem>();

		public ArrayList<GenericItem> list() {
			return items;
		}

		public void add(String itemId) {
			items.add(itemFactory.fromJson(itemId,"item"));
		}

		public GenericItem get(int index) {
			return items.get(index);
		}

		public GenericItem get(String itemId) {
			for (GenericItem item : items){
				if(item.getId().equals(itemId)){
					return item;
				}
			}
			return null;
		}

		public void remove(int index) {
			items.remove(index);
		}

		public void remove(String itemId) {
			for (GenericItem item : items){
				if(item.getId().equals(itemId)){
					items.remove(item.getId());
					break;
				}
			}
		}

		public void remove(String itemId, int amount) {
			for (GenericItem item : items){
				if(item.getId().equals(itemId)){
					amount--;
					if(amount > 0) {
						items.remove(item.getId());
					}else{
						break;
					}
				}
			}
		}
	}

	public class WeaponManager implements InventoryManagerInterface {

		private ArrayList<GenericItem> weapons = new ArrayList<GenericItem>();

		public ArrayList<GenericItem> list() {
			return weapons;
		}

		public void add(String weaponId) {
			weapons.add(itemFactory.fromJson(weaponId,"weapon"));
		}

		public GenericItem get(int index) {
			return weapons.get(index);
		}

		public GenericItem get(String weaponId) {
			for (GenericItem weapon : weapons){
				if(weapon.getId().equals(weaponId)){
					return weapon;
				}
			}
			return null;
		}

		public void remove(int index) {
			weapons.remove(index);
		}

		public void remove(String weaponId) {
			for (GenericItem weapon : weapons){
				if(weapon.getId().equals(weaponId)){
					weapons.remove(weapon.getId());
					break;
				}
			}
		}

		public void remove(String weaponId, int amount) {
			for (GenericItem weapon : weapons){
				if(weapon.getId().equals(weaponId)){
					amount--;
					if(amount > 0) {
						weapons.remove(weapon.getId());
					}else{
						break;
					}
				}
			}
		}

	}

	public class ConsumableManager implements InventoryManagerInterface {

		private ArrayList<GenericItem> consumables = new ArrayList<GenericItem>();

		public ArrayList<GenericItem> list() {
			return consumables;
		}

		public void add(String consumableId) {
			consumables.add(itemFactory.fromJson(consumableId,"consumable"));
		}

		public GenericItem get(int index) {
			return consumables.get(index);
		}

		public GenericItem get(String consumableId) {
			for (GenericItem consumable : consumables){
				if(consumable.getId().equals(consumableId)){
					return consumable;
				}
			}
			return null;
		}

		public void remove(int index) {
			consumables.remove(index);
		}

		public void remove(String consumableId) {
			for (GenericItem consumable : consumables){
				if(consumable.getId().equals(consumableId)){
					consumables.remove(consumable.getId());
					break;
				}
			}
		}

		public void remove(String consumableId, int amount) {
			for (GenericItem consumable : consumables){
				if(consumable.getId().equals(consumableId)){
					amount--;
					if(amount > 0) {
						consumables.remove(consumable.getId());
					}else{
						break;
					}
				}
			}
		}
	}
}
