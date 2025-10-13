package com.sinisterorder.handler;

public interface InventoryHandler {

	public void inventoryAdd(String itemId);
	public void inventoryRemove(int index);
	public void inventoryRemove(String itemId);
	public void inventoryGet(int index);
	public void inventoryGet(String itemId);
}