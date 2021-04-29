package com.example.bigboisthegame;

import android.content.SharedPreferences;

public class saveLoadClass {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String HEALTH = "health";
    public static final String STAMINA = "stamina";
    public static final String COUNTER = "counter";
    public static final String LEVEL = "level";
    public static final String CHONKS = "chonks";
    public static final String PACKS = "packs";
    public static final String PP = "pp";
    public static final String COINS = "coins";
    public static final String TRACKER = "tracker";
    public static final String ENEMY = "enemy";
    public static final String EHEALTH = "eHealth";
    public static final String IHEALTH = "iHealth";
    public static final String WEAPON = "weapon";
    public static final String TIER = "tier";
    public static final String SWORDTYPE = "sword";
    public static final String AXETYPE = "axe";
    public static final String SHIELDTYPE = "shield";
    SharedPreferences sharedPref;

     saveLoadClass(SharedPreferences sharedPref){
         this.sharedPref = sharedPref;
     }

     int health(int health, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(HEALTH, health);
            editor.commit();
            return 0;
        }else{
            health = sharedPref.getInt(HEALTH, 1000);
            return health;
        }
    }

     int stamina(int stamina, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(STAMINA, stamina);
            editor.commit();
            return 0;
        }else{
            stamina = sharedPref.getInt(STAMINA, 100);
            return stamina;
        }
    }

     int numStam(int numStaminaChonk, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(CHONKS, numStaminaChonk);
            editor.commit();
            return 0;
        }else{
            numStaminaChonk = sharedPref.getInt(CHONKS, 0);
            return numStaminaChonk;
        }
    }

     int numHeal(int numHealthPacks, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(PACKS, numHealthPacks);
            editor.commit();
            return 0;
        }else{
            numHealthPacks = sharedPref.getInt(PACKS, 2);
            return numHealthPacks;
        }
    }

     int pp(int pp, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(PP, pp);
            editor.commit();
            return 0;
        }else{
            pp = sharedPref.getInt(PP, 10);
            return pp;
        }
    }

     int counter(int counter, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(COUNTER, counter);
            editor.commit();
            return 0;
        }else{
            counter = sharedPref.getInt(COUNTER, 0);
            return counter;
        }
    }

     int level(int level, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(LEVEL, level);
            editor.commit();
            return 0;
        }else{
            level = sharedPref.getInt(LEVEL, 0);
            return level;
        }
    }

     int coins(int coins, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(COINS, coins);
            editor.commit();
            return 0;
        }else{
            coins = sharedPref.getInt(COINS, 0);
            return coins;
        }
    }

     int levelTracker(int levelTracker, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(TRACKER, levelTracker);
            editor.commit();
            return 0;
        }else{
            levelTracker = sharedPref.getInt(TRACKER, 0);
            return levelTracker;
        }
    }

     String enemy(String enemy, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putString(ENEMY, enemy);
            editor.commit();
            return "";
        }else{
            enemy = sharedPref.getString(ENEMY, "NAH");
            return enemy;
        }
    }

     int enemyHealth(int enemyHealth, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(EHEALTH, enemyHealth);
            editor.commit();
            return 0;
        }else{
            enemyHealth = sharedPref.getInt(EHEALTH, 500);
            return enemyHealth;
        }
    }

     int initHealth(int initHealth, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(IHEALTH, initHealth);
            editor.commit();
            return 0;
        }else{
            initHealth = sharedPref.getInt(IHEALTH, 0);
            return initHealth;
        }
    }

//    int initHealth(boolean saveLoad){
//
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//
//        if(saveLoad){
//            editor.putInt(IHEALTH, initHealth);
//            editor.commit();
//            return 0;
//        }else{
//            initHealth = sharedPref.getInt(IHEALTH, 0);
//            return initHealth;
//        }
//    }

     int tier(int tier, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(TIER, tier);
            editor.commit();
            return 0;
        }else{
            tier = sharedPref.getInt(TIER, 0);
            return tier;
        }
    }

     String weapon(String weapon, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putString(WEAPON, weapon);
            editor.commit();
            return "";
        }else{
            weapon = sharedPref.getString(WEAPON, "fist");
            return weapon;
        }
    }

    int swordType(int weaponType, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(SWORDTYPE, weaponType);
            editor.commit();
            return 0;
        }else{
            weaponType = sharedPref.getInt(SWORDTYPE, 0);
            return weaponType;
        }
    }

    int shieldType(int weaponType, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(SHIELDTYPE, weaponType);
            editor.commit();
            return 0;
        }else{
            weaponType = sharedPref.getInt(SHIELDTYPE, 0);
            return weaponType;
        }
    }

    int axeType(int weaponType, boolean saveLoad){
        SharedPreferences.Editor editor = sharedPref.edit();

        if(saveLoad){
            editor.putInt(AXETYPE, weaponType);
            editor.commit();
            return 0;
        }else{
            weaponType = sharedPref.getInt(AXETYPE, 0);
            return weaponType;
        }
    }

}
