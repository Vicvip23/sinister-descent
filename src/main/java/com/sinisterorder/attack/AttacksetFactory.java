package com.sinisterorder.attack;

import java.io.FileReader;

import com.google.gson.Gson;

public class AttacksetFactory {
    static Gson gson = new Gson();

    public static Attackset fromJson(String attacksetId) {

        try {
            FileReader reader = new FileReader("src/main/resources/attack/attackset.json");
            Attackset[] attacksets = gson.fromJson(reader, Attackset[].class);

            // Search for attackset with matching ID
            for (Attackset attack : attacksets) {
                if(attacksetId.equals(attack.getAttacksetId())){
                    return attack;
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // No attackset found
        return null;
    }
}
