package com.example.bigboisthegame;

import android.content.SharedPreferences;

import java.util.Random;

public class swordClass {
    static String  getDesc(){
        return ("The sword boi gives you no tactical advantage whatsoever, but your minumum damage per attack is now 50.");
    }

    int getDamage(int tier, int level, int weaponType){

        switch (weaponType){
            case 1: return base(tier, level);
            default: return -1;
        }
    }

    int base(int tier, int level){
        Random rand = new Random();

        int doubleLevel = level;

        int levelMultiplier = ((doubleLevel / 10) + 1);
        double doubleDamageDealt = (((rand.nextInt(51) + 50) * levelMultiplier));
        int damageDealt = (int) doubleDamageDealt;
        System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
        System.out.println(level + "  " + levelMultiplier);

        return damageDealt;
    }

    int getType(SharedPreferences sp){
        int weaponType = 0;
        saveLoadClass slc = new saveLoadClass(sp);
        weaponType = slc.swordType(weaponType, false);

        return weaponType;
    }
}
