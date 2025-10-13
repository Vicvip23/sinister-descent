package com.sinisterorder.item;

import com.sinisterorder.handler.LevelHandler;

public class Item implements LevelHandler{
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

    public String itemId;
    public String itemName;
    public int sellValue;
    public String description;

    public Item(String itemId,String itemName,int sellValue,String description){

        this.itemId=itemId;
        this.itemName=itemName;
        this.sellValue=sellValue;
        this.description=description;

    }

    public Item(){

        //constructor

    }

     @Override
    public String toString() {
        return "Item{itemID='" + itemId + "', itemName='" + itemName + "', sellValue=" + sellValue + ", description='" + description + "'}";
    }
}