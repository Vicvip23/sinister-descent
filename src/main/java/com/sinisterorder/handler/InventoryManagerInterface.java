package com.sinisterorder.handler;

import com.sinisterorder.item.GenericItem;
import com.sinisterorder.item.Item;

import java.util.ArrayList;
import java.util.Objects;

public interface InventoryManagerInterface {

	public ArrayList list();
	public void add(String itemId);
	public GenericItem get(String itemId);
	public GenericItem get(int index);
	public void remove(String itemId);
	public void remove(String itemId,int amount);
	public void remove(int index);

}
