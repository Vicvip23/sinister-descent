package com.sinisterorder.app;

import java.util.Random;

import com.sinisterorder.entity.npc.*;
import com.sinisterorder.entity.player.Player;
import com.sinisterorder.item.ItemUtils;
import com.sinisterorder.scene.scenes.Battle;
import com.sinisterorder.scene.scenes.Shop;
import com.sinisterorder.ui.MainMenu;

public abstract class Game {
	private static Random random = new Random();

	public static void run() {
		Shop shop = new Shop();
		Player player = new Player();
		Battle battle = new Battle();
		shop.setClient(player);

		MainMenu.run();
		
		while (true) {
			shop.run();
			battle.run(player, generateEnemy());
		}
	}

	public static Npc generateEnemy() {
		Npc enemy = NpcFactory.fromJson(NpcUtils.EnemyList[random.nextInt(NpcUtils.EnemyList.length)]);
		enemy.inventory.weaponManager.add(ItemUtils.weaponIdList[random.nextInt(ItemUtils.weaponIdList.length)]);
		return enemy;
	}
}
