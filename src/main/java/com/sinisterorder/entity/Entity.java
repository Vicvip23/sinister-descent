package com.sinisterorder.entity;

import com.sinisterorder.handler.EntityDamageHandler;
import com.sinisterorder.inventory.Inventory;

public abstract class Entity implements EntityDamageHandler{
	protected String entityId;
	protected String name;
	protected int maxHealth;
	protected int health;
	protected int armor;
	private String[] drops;
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

	public void setName(String name) {
		this.name = name;
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

		// Make sure there's no health overflow
		if(health > maxHealth) {
			health = maxHealth;
		};

		// Disallow setting of negative health
		if(health < 0) {
			health = 0;
		}
	}

	public String[] getDrops() {
		return this.drops;
	}

	// These two are required by Battle scene implementation
	// Allows for *relatively* easy local multiplayer implementation though! (VS mode)
	public abstract void attack(Entity target);
	public abstract String getLastAction();
}
