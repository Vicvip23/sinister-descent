package com.sinisterorder.entity.npc;

import java.util.ArrayList;

import com.sinisterorder.attack.Attack;
import com.sinisterorder.attack.AttackFactory;
import com.sinisterorder.attack.AttacksetFactory;
import com.sinisterorder.entity.Entity;
import com.sinisterorder.inventory.Inventory;
import com.sinisterorder.otherutils.RandomUtils;

public class Npc extends Entity {
	private String lastAction;
	private String[] uniqueAttackIds;

	public Npc() {
		createInventory();
	}

	public void createInventory() {
		this.inventory = new Inventory();
	}

	public String getLastAction() {
		return lastAction;
	}

	// Battle action algorithm
	@Override
	public void attack(Entity target) {

		// 1 in 10 chance to heal for 10% of max health if health is below 20%
		// Otherwise pick attack at random based on their weights
		if(this.health < this.maxHealth * 0.2 && RandomUtils.chance(10)) {
			this.health += (this.maxHealth * 0.2);
			lastAction = this.name + " has healed its wounds! (recovered " + (this.maxHealth * 0.2) + " health.)";
		} else {
			ArrayList<Attack> availableAttacks = new ArrayList<>();

			// Check for any abilities unique to given entity and add them
			if(uniqueAttackIds != null) {
				for (String attackId : uniqueAttackIds) {
					availableAttacks.add(AttackFactory.fromJson(attackId));
				}
			}

			if(inventory.weaponManager.getEquippedWeapon() != null) {

				// Iterate over attacks contained in equipped weapon's attackset as well as the weapon's unique attacks
				for (String attackId : AttacksetFactory.fromJson(inventory.weaponManager.getEquippedWeapon().getAttackset()).getAttackset()) {
					availableAttacks.add(AttackFactory.fromJson(attackId));
				}

				for (String attackId : inventory.weaponManager.getEquippedWeapon().getUniqueAttacks()) {
					availableAttacks.add(AttackFactory.fromJson(attackId));	
				}
			} else {
				// Fallback weapon and attack
				inventory.weaponManager.add("fist");
				inventory.weaponManager.equip(0);
				availableAttacks.add(AttackFactory.fromJson("punch"));
			}

			Attack attack = (Attack) RandomUtils.rollByWeight(availableAttacks.toArray(new Attack[availableAttacks.size()]));

			// Unique text for the "nothing" ability.
			if(attack.getAttackId().equals("nothing")) {
				lastAction = name + " is moping around.";
			} else {
				int damage = (int) (this.inventory.weaponManager.getEquippedWeapon().getDamage() * attack.getAttackMultiplier()) - (target.getArmor() / 2);
				target.removeHealth(damage);
				lastAction = name + " has attacked you for " + damage + " damage!";
			}
		}
	}
}