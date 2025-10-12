package com.sinisterorder.item;

public class Item {

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