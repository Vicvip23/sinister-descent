package com.sinisterorder.entity;

import com.sinisterorder.handler.EntityDamageHandler;
import com.sinisterorder.inventory.Inventory;

public abstract class Entity implements EntityDamageHandler{
	private String entityId;
	private String name;
	private int health;
	private int maxHealth;
	private int armor;
	public Inventory inventory;
	
	protected void createInventory() {
		inventory = new Inventory();
	}

	public String getEntityId() {
		return entityId;
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getHealth() {
		return health;
	}

	public void addHealth(int amnt) {
		health += amnt;

		if(health > maxHealth) {
			health = maxHealth;
		}
	}

	public void removeHealth(int amnt) {
		health -= amnt;

		if(health < 0) {
			health = 0;
		}
	}

	public void setHealth(int amnt) {
		health = amnt;

		if(health > maxHealth) {
			health = maxHealth;
		};

		if(health < 0) {
			health = 0;
		}
	}
}
