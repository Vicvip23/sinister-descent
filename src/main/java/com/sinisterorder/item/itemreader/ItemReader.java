package com.sinisterorder.item.itemreader;

import com.google.gson.Gson;
import com.sinisterorder.item.Item;
import java.io.FileReader;


public class ItemReader {

    public Item itemFactory(String itemID){
        Gson gson = new Gson();

         try {
            FileReader reader = new FileReader("src/main/resources/item/item.json");

            Item[] items = gson.fromJson(reader, Item[].class);

            for (Item item : items) {
                if(itemID.equals(item.itemID)){
                    return item;
                }
            }

            reader.close();
        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

}
