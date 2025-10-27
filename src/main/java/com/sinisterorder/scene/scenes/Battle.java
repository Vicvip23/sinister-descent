package com.sinisterorder.scene.scenes;

import java.util.ArrayList;
import java.util.Random;

import com.sinisterorder.attack.Attack;
import com.sinisterorder.attack.AttackFactory;
import com.sinisterorder.attack.AttacksetFactory;
import com.sinisterorder.entity.Entity;
import com.sinisterorder.entity.player.Player;
import com.sinisterorder.scene.Scene;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.MenuFactory;

public class Battle extends Scene{
	private Player player;
	private Entity enemy;
	private ChoiceMenu battleMenu;
	private boolean run;
	private static Random random = new Random();

	public void run(Player player, Entity enemy) {
		this.player = player;
		this.enemy = enemy;
		run = true;

		while (run) {
			buildMenu();
			battleMenu.run();
			turn();
		}
	};

	private void buildMenu() {
		battleMenu = MenuFactory.create("battle", "Battle");
		battleMenu.addLabel("battle_flavor", "A " + enemy.getName() + " Approaches!\n");
		battleMenu.addLabel("battle_info", String.format("%s: %d\t %s: %d\n", "Health", enemy.getHealth(), "Armor", enemy.getArmor()));

		battleMenu.createOption("attack", "Attack", () -> {
			player.attack(enemy);
		});

		battleMenu.createOption("inventory", "Open inventory", () -> {
			player.startInventoryManagerUi(false);
		});
	}

	private void turn() {
		ArrayList<Attack> availableAttacks = new ArrayList<>();

		for (String attackId : AttacksetFactory.fromJson(enemy.inventory.weaponManager.getEquippedWeapon().getAttackset()).getAttackset()) {
			availableAttacks.add(AttackFactory.fromJson(attackId));
		}
		for (String attackId : enemy.inventory.weaponManager.getEquippedWeapon().getUniqueAttacks()) {
			availableAttacks.add(AttackFactory.fromJson(attackId));	
		}

		enemy.attack(player);
	}
}
