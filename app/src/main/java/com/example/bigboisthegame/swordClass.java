package com.example.bigboisthegame;

import java.util.Random;

public class swordClass {
    void getDesc(){
        System.out.println("The sword boi gives you no tactical advantage whatsoever, but your minumum damage per attack is now 50.");
    }

    int getDamage(int tier, int level){
        Random rand = new Random();

        int doubleLevel = level;

        int levelMultiplier = ((doubleLevel / 10) + 1);
        double doubleDamageDealt = ((rand.nextInt(100) * levelMultiplier));
        int damageDealt = (int) doubleDamageDealt;
        System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
        System.out.println(level + "  " + levelMultiplier);

        if(damageDealt < 50){
            damageDealt = 50;
        }

        return damageDealt;
    }
}
