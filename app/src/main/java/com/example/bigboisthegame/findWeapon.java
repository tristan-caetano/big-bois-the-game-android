package com.example.bigboisthegame;

public class findWeapon{
    private int tier;
    private String weapon;
    private int level;

    findWeapon(int tier, String weapon, int level){
        this.tier = tier;
        this.weapon = weapon;
        this.level = level;
    }

    int findDmg(){
        int damage = 0;

        switch(weapon){
            case "fist":{
                fistClass fc = new fistClass();
                damage = fc.getDamage(tier, level);
                break;
            }
            case "sword":{
                swordClass sc = new swordClass();
                damage = sc.getDamage(tier, level);
                break;
            }
        }

        return damage;
    }
}
