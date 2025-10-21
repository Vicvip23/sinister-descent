package com.sinisterorder.handler;

import com.sinisterorder.item.GenericItem;

import java.util.ArrayList;

public interface InventoryManagerInterface {

	public ArrayList<GenericItem> list();
	public void add(String itemId);
	public ArrayList<Integer> get(String itemId);
	public GenericItem get(int index);
	public void remove(String itemId);
	public void remove(String itemId,int amount);
	public void remove(int index);

}
