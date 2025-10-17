package com.sinisterorder.item;

public class Consumable extends GenericItem{
	private String effect;
	private int usesLeft;

	public String getEffect() {
		return this.effect;
	}

	public int getUses() {
		return this.usesLeft;
	}

	public String use() {
		this.usesLeft--;
		return this.effect;
	}
}