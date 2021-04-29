package com.example.bigboisthegame;

import android.content.SharedPreferences;

public class findWeapon{
    private int tier;
    private String weapon;
    private int level;
    private int weaponType;

    //Constructor to setup object
    findWeapon(int tier, String weapon, int level, int weaponType){
        this.tier = tier;
        this.weapon = weapon;
        this.level = level;
        this.weaponType = weaponType;
    }

    //Returns the damage for the weapon
    int findDmg(){
        int damage = 0;

        switch(weapon){
            case "fist":{
                fistClass fc = new fistClass();
                damage = fc.getDamage(tier, level, weaponType);
                break;
            }

            case "sword":{
                swordClass sc = new swordClass();
                damage = sc.getDamage(tier, level, weaponType);
                break;
            }

            case "shield":{
                shieldClass sc = new shieldClass();
                damage = sc.getDamage(tier, level, weaponType);
                break;
            }

            case "axe":{
                axeClass ac = new axeClass();
                damage = ac.getDamage(tier, level, weaponType);
                break;
            }
        }

        return damage;
    }

    //Returns the damage for the weapon
    int findType(SharedPreferences sp){
        int weaponType = 0;

        switch(weapon){
            case "fist":{
                fistClass fc = new fistClass();
                weaponType = fc.getType(sp);
                break;
            }

            case "sword":{
                swordClass sc = new swordClass();
                weaponType = sc.getType(sp);
                break;
            }

            case "shield":{
                shieldClass sc = new shieldClass();
                weaponType = sc.getType(sp);
                break;
            }

            case "axe":{
                axeClass ac = new axeClass();
                weaponType = ac.getType(sp);
                break;
            }
        }

        return weaponType;
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
