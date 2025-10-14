package com.sinisterorder.attack;

public class Attack{
      private  String attackId;
      private  String attackName;
      private  String attackDesscripotion;
      private  float attackMultiplayer;

    public String getAttackId() {
        return this.attackId;
    }

    public String getAttackName() {
        return this.attackName;
    }

    public String getAttackDesscripotion() {
        return this.attackDesscripotion;
    }

    public float getAttackMultiplayer() {
        return this.attackMultiplayer;
    }

    public void setAttackId(String attackId) {
        this.attackId = attackId;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public void setAttackDesscripotion(String attackDesscripotion) {
        this.attackDesscripotion = attackDesscripotion;
    }

    public void setAttackMultiplayer(float attackMultiplayer) {
        this.attackMultiplayer = attackMultiplayer;
    }
}
