package com.example.bigboisthegame;

public class findWeapon{
    private int tier;
    private String weapon;
    private int level;

    //Constructor to setup object
    findWeapon(int tier, String weapon, int level){
        this.tier = tier;
        this.weapon = weapon;
        this.level = level;
    }

    //Returns the damage for the weapon
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

            case "shield":{
                shieldClass sc = new shieldClass();
                damage = sc.getDamage(tier, level);
                break;
            }

            case "axe":{
                axeClass ac = new axeClass();
                damage = ac.getDamage(tier, level);
                break;
            }
        }

        return damage;
    }

    //Returns the description of the weapon
    String findDesc(){
        String desc = "ERROR: No description found;";

        switch(weapon){
            case "fist":{
                fistClass fc = new fistClass();
                desc = fc.getDesc();
                break;
            }

            case "sword":{
                swordClass sc = new swordClass();
                desc = sc.getDesc();
                break;
            }

            case "shield":{
                shieldClass sc = new shieldClass();
                desc = sc.getDesc();
                break;
            }

            case "axe":{
                axeClass ac = new axeClass();
                desc = ac.getDesc();
                break;
            }
        }

        return desc;
    }
}
