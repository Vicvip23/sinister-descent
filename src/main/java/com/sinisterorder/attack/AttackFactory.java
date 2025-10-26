package com.sinisterorder.attack;

import java.io.FileReader;

import com.google.gson.Gson;

public class AttackFactory {
    static Gson gson = new Gson();

    public static Attack fromJson(String attackId){


        try {
            FileReader reader = new FileReader("src/main/resources/attack/attack.json");
            Attack[] attacks = gson.fromJson(reader, Attack[].class);

            for (Attack attack : attacks) {
                if(attackId.equals(attack.getAttackId())){
                    return attack;
                }
            }
            reader.close();
        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }
}
