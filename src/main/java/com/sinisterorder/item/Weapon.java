package com.sinisterorder.item;

import java.util.Arrays;

public class Weapon extends GenericItem{
	private int damage;
	private String attackset;
	private String[] uniqueAttacks;

	public int getDamage() {
		return this.damage;
	}

	public String[] getUniqueAttacks() {
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
