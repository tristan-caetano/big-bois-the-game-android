package com.example.bigboisthegame;

import java.util.Random;

public class axeClass {

    static String  getDesc(){
        return ("The axe boi used to be used for chopping trees, now its used for chopping bois.\nAlso, axe has more damage output, but less accurate.");
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
        int missPerc = rand.nextInt(10);
        int levelMultiplier = ((doubleLevel / 10) + 1);
        double doubleDamageDealt = (((rand.nextInt(51) + 70) * levelMultiplier));
        int damageDealt = (int) doubleDamageDealt;
        System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
        System.out.println(level + "  " + levelMultiplier);

        //10 percent Miss
        if(missPerc == 0){
            damageDealt = 0;
        }

        return damageDealt;

    }

}
