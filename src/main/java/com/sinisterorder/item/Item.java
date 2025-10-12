package com.sinisterorder.item;

import com.google.gson.Gson;

public class Item {

    public String itemID;
    public String itemName;
    public int sellValue;
    public String description;

    public Item(String itemID,String itemName,int sellValue,String description){

        this.itemID=itemID;
        this.itemName=itemName;
        this.sellValue=sellValue;
        this.description=description;

    }

    public Item(){

        //constructor

    }

     @Override
    public String toString() {
        return "Item{itemID='" + itemID + "', itemName='" + itemName + "', sellValue=" + sellValue + ", description='" + description + "'}";
    }
}