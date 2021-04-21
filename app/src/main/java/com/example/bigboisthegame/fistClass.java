package com.example.bigboisthegame;

import java.util.Random;

public class fistClass {

    void getDesc(){
        System.out.println("Dude why are you looking up a description about your fists? They are used for bonking bois, that's it.");
    }

    int getDamage(int tier, int level){
        Random rand = new Random();

        int doubleLevel = level;

        int levelMultiplier = ((doubleLevel / 10) + 1);
        double doubleDamageDealt = ((rand.nextInt(100) * levelMultiplier));
        int damageDealt = (int) doubleDamageDealt;
        System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
        System.out.println(level + "  " + levelMultiplier);

        return damageDealt;
    }
}
