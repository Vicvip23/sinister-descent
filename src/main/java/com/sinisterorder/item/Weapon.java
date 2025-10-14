package com.sinisterorder.item;

import java.util.ArrayList;

public class Weapon extends Item{
	private int damage;
	private String attackset;
	private ArrayList<String> uniqueAttacks;

	public int getDamage() {
		return this.damage;
	}

	public ArrayList<String> getUniqueAttacks() {
		return this.uniqueAttacks;
	}

	public String getAttackset() {
		return this.attackset;
	}

	@Override
	public void scaleByLevel(int level) {
		super.scaleByLevel(level);
		this.damage = this.damage * (1 + (this.getLevel() / 10));
	}
}
