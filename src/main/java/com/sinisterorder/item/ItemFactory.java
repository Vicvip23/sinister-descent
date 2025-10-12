package com.sinisterorder.item;

public class ItemFactory {
	public Item creat(Item item){
		if(item != null){
			return item;
		}else{
			return null;
		}
	}
}
