package com.sinisterorder.entity.player;

import java.util.ArrayList;

import com.sinisterorder.entity.Entity;
import com.sinisterorder.item.GenericItem;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.MenuFactory;

public class Player extends Entity{
	private ChoiceMenu mainPlayerMenu;
	private ChoiceMenu subMenu;

	Player() {
		mainPlayerMenu = MenuFactory.create("playermenu", "");
		subMenu = MenuFactory.create("submenu", "");
	}

	public void displayItems() {
		mainPlayerMenu.setTitle("Misc items in inventory");
		ArrayList<GenericItem> items = this.inventory.itemManager.list();

		for (int i = 1; i <= items.size(); ++i) {
			if(i % 3 == 0) {
				mainPlayerMenu.addLabel("" + i, String.format("%d) %s\n", i, items.get(i - 1).getName()));
			} else {
				mainPlayerMenu.addLabel("" + i, String.format("%d) %s\t", i, items.get(i - 1).getName()));
			}
		}

		mainPlayerMenu.createOption("more", "Display more information about an item", () -> {
			subMenu.wipe();
			subMenu.setTitle(getEntityId());
			subMenu.createOption(getName(), getEntityId(), null);
		});
	}
}
