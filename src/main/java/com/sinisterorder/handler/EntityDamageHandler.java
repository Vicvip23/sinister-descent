package com.sinisterorder.handler;

// Probably a good idea to remove this
public interface EntityDamageHandler {

	public void removeHealth(int amnt);
	public void addHealth(int amnt);
	public void setHealth(int amnt);
	public int getHealth();

}