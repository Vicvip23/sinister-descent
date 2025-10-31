package com.sinisterorder.attack;

import com.sinisterorder.handler.RandomChoiceByWeightInterface;

public class Attack implements RandomChoiceByWeightInterface{
      private String attackId;
      private String attackName;
      private String attackDescription;
      private float attackMultiplier;
      private int weight;

    public String getAttackId() {
        return this.attackId;
    }

    public String getAttackName() {
        return this.attackName;
    }

    public String getAttackDescription() {
        return this.attackDescription;
    }

    public float getAttackMultiplier() {
        return this.attackMultiplier;
    }

    public void setAttackId(String attackId) {
        this.attackId = attackId;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public void setAttackDescription(String attackDescription) {
        this.attackDescription = attackDescription;
    }

    public void setAttackMultiplayer(float attackMultiplier) {
        this.attackMultiplier = attackMultiplier;
    }

    public int getWeight() {
        return weight;
    }
}
