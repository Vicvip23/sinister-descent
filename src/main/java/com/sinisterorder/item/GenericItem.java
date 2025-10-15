package com.sinisterorder.item;

import com.sinisterorder.handler.LevelHandler;

public class GenericItem implements LevelHandler{
	private String itemId;
	private String itemName;
	private int sellValue;
	private String description;
	private int level;

	public String getId() {
		return this.itemId;
	}

	public String getName() {
		return this.itemName;
	}

	public int getValue() {
		return this.sellValue;
	}

	public String getDescription(){
		return this.description;
	}

	public int getLevel() {
		return this.level;
	}

	public void scaleByLevel(int level) {
		this.level = level;
		this.sellValue = this.sellValue * (1 + (level / 8));
	}
}