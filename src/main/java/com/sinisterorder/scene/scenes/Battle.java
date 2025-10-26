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

	public void run(Player player, Entity enemy, Scene nextScene) {
		run = true;

		while (run) {
			buildMenu();
			battleMenu.run();
			turn();
		}

		nextScene.run();
	};

	private void buildMenu() {
		battleMenu = MenuFactory.create("battle", "Battle");
		battleMenu.addLabel("battle_flavor", "A " + enemy.getName() + " Approaches!\n");
		battleMenu.addLabel("battle_info", String.format("%s: %d\t %s: %d\n", "Health", enemy.getHealth(), "Armor", enemy.getArmor()));

		battleMenu.createOption("attack", "Attack", () -> {
			ArrayList<Attack> availableAttacks = new ArrayList<>();

			for (String attackId : AttacksetFactory.fromJson(player.inventory.weaponManager.getEquippedWeapon().getAttackset()).getAttackset()) {
				availableAttacks.add(AttackFactory.fromJson(attackId));
			}
			for (String attackId : player.inventory.weaponManager.getEquippedWeapon().getUniqueAttacks()) {
				availableAttacks.add(AttackFactory.fromJson(attackId));	
			}

			battleMenu.createQuery("attack_selector", "Pick attack to use: ", "free", 0, availableAttacks.size() - 1);

			battleMenu.fullWipe();
			battleMenu.setTitle("Attacks");

			for (int i = 1; i <= availableAttacks.size(); ++i) {
				if(i % 3 == 0 && i != availableAttacks.size()) {
					battleMenu.addLabel("" + i, String.format("%d. %s\n", i, availableAttacks.get(i - 1).getAttackName()));
				} else {
					battleMenu.addLabel("" + i, String.format("%d. %s\t", i, availableAttacks.get(i - 1).getAttackName()));
				}
			}

			battleMenu.createOption("pick_attack", "Choose attack", () -> {
				player.attack(enemy, availableAttacks.get(battleMenu.query.run()));
			});
			battleMenu.run();
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

		enemy.attack(player, availableAttacks.get(random.nextInt(availableAttacks.size())));
	}
}
