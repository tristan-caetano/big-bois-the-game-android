package com.example.bigboisthegame;

import android.content.SharedPreferences;

import java.util.Random;

public class fistClass {

    String getDesc(){
        return ("Dude why are you looking up a description about your fists? They are used for bonking bois, that's it.");
    }

    int getDamage(int tier, int level, int weaponType){

        switch (weaponType){
            case 0: return base(tier, level);
            default: return -1;
        }
    }

    int base(int tier, int level){
        Random rand = new Random();

        int doubleLevel = level;

        int levelMultiplier = ((doubleLevel / 10) + 1);
        double doubleDamageDealt = ((rand.nextInt(101) * levelMultiplier));
        int damageDealt = (int) doubleDamageDealt;
        System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
        System.out.println(level + "  " + levelMultiplier);

        return damageDealt;
    }

    int getType(SharedPreferences sp){
//        int weaponType = 0;
//        saveLoadClass slc = new saveLoadClass(sp);
//        //weaponType = slc.fistType(weaponType, false);

        return 1;
    }
}
