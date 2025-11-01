package com.sinisterorder.scene.scenes;

import java.util.ArrayList;
import java.util.Random;

import com.sinisterorder.attack.Attack;
import com.sinisterorder.attack.AttackFactory;
import com.sinisterorder.attack.AttacksetFactory;
import com.sinisterorder.entity.Entity;
import com.sinisterorder.entity.player.Player;
import com.sinisterorder.item.*;
import com.sinisterorder.scene.Scene;
import com.sinisterorder.ui.ChoiceMenu;
import com.sinisterorder.ui.EndMenu;
import com.sinisterorder.ui.MenuFactory;
import com.sinisterorder.ui.MenuUtils;

public class Battle extends Scene{
	private Player player;
	private Entity enemy;
	private ChoiceMenu battleMenu;
	private boolean run;

	public void run(Player player, Entity enemy) {
		this.player = player;
		this.enemy = enemy;
		run = true;

		while (run) {
			if(enemy.getHealth() > 0) {
				buildMenu(true);
				
				if(player.getHealth() <= 0) {
					battleMenu.display();
					MenuUtils.wait(2000);
					EndMenu.run();
				}
				battleMenu.run();
				
				// TODO: fix hacky solution
				if(enemy.getHealth() > 0) {
					turn();
					buildMenu(false);
					battleMenu.display();
					MenuUtils.wait(250);
				}

			} else {
				buildEndMenu();
				battleMenu.run();
			}
		}
	};

	private void buildMenu(boolean displayEnemyAction) {
		battleMenu = MenuFactory.create("battle", "Battle");
		battleMenu.addLabel("battle_enemy_header", "A " + enemy.getName() + " Approaches!\n");
		battleMenu.addLabel("battle_enemy_info", String.format("%s: %d\t %s: %d\n", "Health", enemy.getHealth(), "Armor", enemy.getArmor()));
		battleMenu.addLabel("battle_player_header", "\nYour Stats\n");
		battleMenu.addLabel("battle_player_info", String.format("%s: %d\t %s: %d\n", "Health", player.getHealth(), "Armor", player.getArmor()));

		if(enemy.getLastAction() != null && displayEnemyAction) {
			battleMenu.addLabel("enemy_action", enemy.getLastAction() + "\n");
		}

		battleMenu.createOption("attack", "Attack", () -> {
			player.attack(enemy);
		});

		battleMenu.createOption("inventory", "Open inventory", () -> {
			player.startInventoryManagerUi(false);
		});
	}

	private void buildEndMenu() {
		battleMenu = MenuFactory.create("battle", "Battle");
		battleMenu.addLabel("battle_flavor", enemy.getName() + " Has Been Defeated!\n");
		battleMenu.addLabel("battle_drops_header", "Obtained items:\n\n");

		GenericItem[] drops = ItemUtils.generateBattleDrops(enemy);

		if(drops != null) {
			for (GenericItem item : drops) {
				battleMenu.addLabel("drop_" + item.getId(), String.format("%s\n", item.getName()));
				switch (item.getItemType()) {
					case item:
						player.inventory.itemManager.add((Item) item);
						break;
					case weapon:
						player.inventory.weaponManager.add((Weapon) item);
						break;
					case consumable:
						player.inventory.consumableManager.add((Consumable) item);
					default:
						break;
				}
			}
		} else {
			battleMenu.addLabel("no_drops", "none");
		}

		battleMenu.createOption("next", "Next", () -> {
			run = false;
		});
	}

	private void turn() {
		enemy.attack(player);
	}
}
