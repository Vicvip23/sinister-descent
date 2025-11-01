package com.sinisterorder.item;

public class Consumable extends GenericItem {
	private String effectId;
	private String effectDescription;
	private int usesLeft;

	public String getEffectId() {
		return effectId;
	}

	public int getUses() {
		return usesLeft;
	}

	public String use() {
		this.usesLeft--;
		return effectId;
	}

	public String getEffectDescription() {
		return effectDescription;
	}
}