package com.sinisterorder.item;

import com.sinisterorder.handler.LevelHandler;
import com.sinisterorder.item.ItemUtils.ItemType;

public class GenericItem implements LevelHandler{
	private String itemId;
	private String itemName;
	private int sellValue;
	private String description;
	private int level;
	private ItemType itemType;



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

	public ItemType getItemType() {
		return itemType;
	}

	protected void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public int getLevel() {
		return this.level;
	}

	public void scaleByLevel(int level) {
		this.level = level;
		this.sellValue = this.sellValue * (1 + (level / 8));
	}
}