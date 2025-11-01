package com.sinisterorder.item;

import com.sinisterorder.handler.LevelHandler;
import com.sinisterorder.handler.RandomChoiceByWeightInterface;
import com.sinisterorder.item.ItemUtils.ItemType;

public class GenericItem implements LevelHandler, RandomChoiceByWeightInterface {
	private String itemId;
	private String itemName;
	private int value;
	private String description;
	private int level;
	private ItemType itemType;
	private int weight;

	public String getId() {
		return this.itemId;
	}

	public String getName() {
		return this.itemName;
	}

	public int getValue() {
		return this.value;
	}

	public String getDescription() {
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

	public int getWeight() {
		return weight;
	}

	public void scaleByLevel(int level) {
		this.level = level;
		this.value = this.value * (1 + (level / 8));
	}
}