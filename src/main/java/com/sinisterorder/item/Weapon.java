package com.sinisterorder.item;

import java.util.ArrayList;

import com.sinisterorder.attack.Attack;

public class Weapon extends Item{
	private int damage;
	private ArrayList<Attack> attacks;

	public int getDamage() {
		return damage;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	@Override
	public void scaleByLevel(int level) {
		super.scaleByLevel(level);
		this.damage = this.damage * (1 + (this.getLevel() / 10));
	}
}
