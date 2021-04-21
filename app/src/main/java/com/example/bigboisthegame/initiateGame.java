package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class initiateGame extends AppCompatActivity {

    //Player Variables
    int health, stamina, counter, attack, ehealthTemp, level, levelTracker, xpMilestone, numStaminaChonk, staminaChonkAmt, chance, numHealthPacks, healthPackAmt, healthPackDropChance, pp, coins, tier;
    double levelMultiplier;
    boolean bossLevel;
    String weapon;

    //Enemy Variables
    int enemyHealth, maxEnemyHealth, maxEnemyAtt, bossChance, initHealth;
    String enemy;

    //Game Variables
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
    Random rand = new Random();
    double doubleLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_game);
        startBattle();
    }

    public void playerSetup(){
        attack = 100;
        maxEnemyAtt = 100;
        maxEnemyHealth = 1000;
        bossChance = 10;
        ehealthTemp = 0;
        chance = 100;
        staminaChonkAmt = 30;
        healthPackAmt = 300;

        load();
    }

    public void startBattle() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth2);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina2);
        TextView healthText = (TextView) findViewById(R.id.healthText2);
        TextView staminaText = (TextView) findViewById(R.id.staminaText2);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth);
        TextView enemyName = (TextView) findViewById(R.id.enemyName);
        TextView fText = (TextView) findViewById(R.id.fightText);
        TextView levelT = (TextView) findViewById(R.id.currentLevel);
        TextView expT = (TextView) findViewById(R.id.currentExp);
        TextView tierText = (TextView) findViewById(R.id.currentTier);

        Button fight = (Button) findViewById(R.id.fightButton);
        Button specialB = (Button) findViewById(R.id.specialButton);
        Button dronkB = (Button) findViewById(R.id.dronkButton);
        Button cronchB = (Button) findViewById(R.id.cronchButton);
        Button runB = (Button) findViewById(R.id.runButton);
        Button inventoryB = (Button) findViewById(R.id.inventoryButton);
        Button blockB = (Button) findViewById(R.id.blockButton);
        //Button suicideB = (Button) findViewById(R.id.suicideButton);

        xpMilestone = (100 * (1 + level));

            playerSetup();

            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            System.out.println(sharedPref.getString(ENEMY, "NAH"));
            System.out.println(sharedPref.getInt(EHEALTH, 69420));
            enemy = sharedPref.getString(ENEMY, "NAH");
            enemyHealth = sharedPref.getInt(EHEALTH, 500);

            if(enemy.equals("NAH")){
                enemy = getEnemyName();
                System.out.println("Getting New Enemy Name");
            }

            fText.setText("Boi, " + enemy + "'s got " + Integer.toString(enemyHealth) + " health left. \nWhat you doin now fam?");

            enemyName.setText(enemy + "'s Health: ");
            healthText.setText("Health Bois: ");
            staminaText.setText("Stamina Bois: ");
            healthT.setText(Integer.toString(health));
            staminaT.setText(Integer.toString(stamina));
            levelT.setText("Level: " + Integer.toString(level));
            tierText.setText("Tier: " + Integer.toString(tier));
            expT.setText("EXP: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));
            enemyHealthT.setText(Integer.toString(enemyHealth));

            fight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attack();
                    save();

                }
            });

            blockB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    block();
                    save();
                }
            });

            inventoryB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save();
                    Intent startIntent = new Intent(getApplicationContext(), inventoryScreen.class);
                    startActivity(startIntent);
                }
            });

             dronkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healthPackInstance();
                save();
                 }
             });

             cronchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staminaChonkInstance();
                save();
            }
        });

        specialB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), specialAttack.class);
                startActivity(startIntent);

            }
        });

        specialB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), specialAttack.class);
                startActivity(startIntent);

            }
        });
        specialB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), specialAttack.class);
                startActivity(startIntent);

            }
        });

        runB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), run.class);
                startActivity(startIntent);

            }
        });

        }

    String getEnemyName(){

        bossLevel = false;

        System.out.println("ENEMY: " + (counter % 5));
        if (counter % 5 == 0) {
            System.out.println("Check for boss: " + (bossChance * level));
            if (rand.nextInt(100) < (bossChance * level)) {
                bossLevel = true;
                System.out.println("IsBoss");
                int[] bossHealthChance = { 2000, 3000, 4000 };
                int bossHealth = bossHealthChance[rand.nextInt(bossHealthChance.length)];

                String[] enemies = { "The Big Boi", "Ultimate Salad", "PoopyJuice", "Mononucleosis",
                        "The Inhibition", "Big Sad", "Viscus Fart Chamber", "COVID-19", "Karen" };
                enemy = enemies[rand.nextInt(enemies.length)];

                enemyHealth = bossHealth;
            }

        }

        if(!bossLevel){

            System.out.println("Assigning enemy name.");

            String[] enemies = { "Prime Time Bad Guy", "Normie", "Big Salad", "Lowly Salad", "Bad Crouton",
                    "Apple Fanboy", "Long Boi", "Sad Boi", "Orange Juice", "Bad Larry", "Big Kidney Stone",
                    "Fart Chamber" };
            enemy = (enemies[rand.nextInt(enemies.length)]);
            enemyHealth = rand.nextInt(maxEnemyHealth);

            System.out.println("New Name: " + enemy);

            if (enemyHealth < 0) {
                enemyHealth *= -1;
            }

        }

        ehealthTemp = enemyHealth / 10;

        System.out.println("Enemy: " + enemy);
        System.out.println("EnemyXP: " + ehealthTemp);
        System.out.println("EnemyH: " + enemyHealth);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ENEMY, enemy);
        editor.putInt(EHEALTH, enemyHealth);
        initHealth = enemyHealth;
        editor.putInt(IHEALTH, initHealth);
        editor.commit();

        System.out.println(sharedPref.getString(ENEMY, "NA"));

        return enemy;
    }


    void attack(){

        TextView healthT = (TextView) findViewById(R.id.currentHealth2);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina2);
        TextView healthText = (TextView) findViewById(R.id.healthText2);
        TextView staminaText = (TextView) findViewById(R.id.staminaText2);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth);
        TextView enemyName = (TextView) findViewById(R.id.enemyName);
        TextView fText = (TextView) findViewById(R.id.fightText);

        Button fight = (Button) findViewById(R.id.fightButton);
        Button specialB = (Button) findViewById(R.id.specialButton);
        Button dronkB = (Button) findViewById(R.id.dronkButton);
        Button cronchB = (Button) findViewById(R.id.cronchButton);
        Button runB = (Button) findViewById(R.id.runButton);
        Button inventoryB = (Button) findViewById(R.id.inventoryButton);
        Button blockB = (Button) findViewById(R.id.blockButton);
        //Button suicideB = (Button) findViewById(R.id.suicideButton);

        if (stamina > 10) {
            doubleLevel = level;

            //findWeapon fw = new findWeapon(tier, weapon, level);

            //Player's Turn
            levelMultiplier = ((doubleLevel / 10) + 1);
            double doubleDamageDealt = ((rand.nextInt(attack) * levelMultiplier));
            int damageDealt = (int) doubleDamageDealt;
            System.out.println(damageDealt + " / " + levelMultiplier + "/" + doubleLevel);
            System.out.println(level + "  " + levelMultiplier);

            //Enemy Turn
            int damageTaken = rand.nextInt(maxEnemyAtt);
            health -= damageTaken;
            enemyHealth -= damageDealt;
            stamina -= (damageDealt / 10);
            save();

            if (stamina < 0) {

                stamina = 0;

            }

            staminaT.setText(Integer.toString(stamina));

            fText.setText("Boi, you hit " + enemy + " for " + damageDealt + " damage,\nbig yeet. " + enemy
                    + " also hit you for " + damageTaken + ", oof. \nWhat's poppin next?");

            enemyHealthT.setText(Integer.toString(enemyHealth));
            healthT.setText(Integer.toString(health));

        } else {

            fText.setText("You are too tired to fight.");

        }

        if (enemyHealth < 1 && health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), ddScreen.class);
            startActivity(startIntent);
        } else if (enemyHealth < 1) {
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(ENEMY, enemy);
            editor.commit();
            Intent startIntent = new Intent(getApplicationContext(), winScreen.class);
            startActivity(startIntent);

        } else if (health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), loseScreen.class);
            startActivity(startIntent);
        }
    }

    public void block() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth2);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina2);
        TextView healthText = (TextView) findViewById(R.id.healthText2);
        TextView staminaText = (TextView) findViewById(R.id.staminaText2);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth);
        TextView enemyName = (TextView) findViewById(R.id.enemyName);
        TextView fText = (TextView) findViewById(R.id.fightText);

        Button fight = (Button) findViewById(R.id.fightButton);
        Button specialB = (Button) findViewById(R.id.specialButton);
        Button dronkB = (Button) findViewById(R.id.dronkButton);
        Button cronchB = (Button) findViewById(R.id.cronchButton);
        Button runB = (Button) findViewById(R.id.runButton);
        Button inventoryB = (Button) findViewById(R.id.inventoryButton);
        Button blockB = (Button) findViewById(R.id.blockButton);
        //Button suicideB = (Button) findViewById(R.id.suicideButton);

        System.out.println("\tYou did your best to block " + enemy + "'s attack.");
        double bChance = rand.nextInt(chance);
        double block = (bChance / 100);
        int damageTaken = rand.nextInt(maxEnemyAtt);
        double blockedDamage = damageTaken - (block * damageTaken);
        health -= Math.round(blockedDamage);

        stamina += (block * 15);

        if (stamina > 100) {
            stamina = 100;
        }

        healthT.setText(Integer.toString(health));
        staminaT.setText(Integer.toString(stamina));

        fText.setText("You blocked " + Math.round(bChance) + "% of " + enemy + "'s attack. \nYou took "
                + Math.round(blockedDamage) + " damage.");

        System.out.println("NOT DEAD: " + health);

        if (enemyHealth < 1 && health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), ddScreen.class);
            startActivity(startIntent);
        } else if (enemyHealth < 1) {
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(ENEMY, enemy);
            editor.commit();
            Intent startIntent = new Intent(getApplicationContext(), winScreen.class);
            startActivity(startIntent);

        } else if (health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), loseScreen.class);
            startActivity(startIntent);
        }
    }

    public void save(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(HEALTH, health);
        editor.putInt(STAMINA, stamina);
        editor.putInt(CHONKS, numStaminaChonk);
        editor.putInt(PACKS, numHealthPacks);
        editor.putInt(PP, pp);
        editor.putInt(COUNTER, counter);
        editor.putInt(LEVEL, level);
        editor.putInt(COINS, coins);

        editor.putInt(TRACKER, levelTracker);
        editor.putString(ENEMY, enemy);
        editor.putInt(EHEALTH, enemyHealth);
        editor.putInt(IHEALTH, initHealth);
        editor.putInt(TIER, tier);
        editor.putString(WEAPON, weapon);
        editor.commit();

    }

    public void load(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        health = sharedPref.getInt(HEALTH, 1000);
        stamina = sharedPref.getInt(STAMINA, 100);
        numStaminaChonk = sharedPref.getInt(CHONKS, 0);
        numHealthPacks = sharedPref.getInt(PACKS, 2);
        pp = sharedPref.getInt(PP, 10);
        counter = sharedPref.getInt(COUNTER, 0);
        level = sharedPref.getInt(LEVEL, 0);
        coins = sharedPref.getInt(COINS, 0);
        levelTracker = sharedPref.getInt(TRACKER, 0);
        initHealth = sharedPref.getInt(IHEALTH, 0);
        weapon = sharedPref.getString(WEAPON, "fist");
        tier = sharedPref.getInt(TIER, 0);

    }

    public void healthPackInstance() {

        TextView healthText = (TextView) findViewById(R.id.currentHealth2);
        TextView fText = (TextView) findViewById(R.id.fightText);

        if (health >= 1500) {
            fText.setText("Your health is hecking maxed!");
        } else if (numHealthPacks > 0) {
            health += healthPackAmt;
            numHealthPacks--;
            fText.setText("You dronked the health pack for " + Integer.toString(healthPackAmt) + " health back. \n You have "
                    + Integer.toString(numHealthPacks) + " left.");
            if (health >= 1500) {
                health = 1500;
            }
        } else if (numHealthPacks <= 0) {
            fText.setText("You dronked the all the health packs!");
        }

        healthText.setText(Integer.toString(health));
        save();

    }

    public void staminaChonkInstance() {

        TextView staminaText = (TextView) findViewById(R.id.currentStamina2);
        TextView fText = (TextView) findViewById(R.id.fightText);

        if (stamina >= 100) {
            fText.setText("Your stamina is hecking maxed!");
        } else if (numStaminaChonk > 0) {
            stamina += staminaChonkAmt;
            numStaminaChonk--;
            fText.setText("You cronched the stamina chonk for " + Integer.toString(staminaChonkAmt) + " stamina back. \n You have "
                    + Integer.toString(numStaminaChonk) + " left.");
            if (stamina >= 100) {
                stamina = 100;
            }
        } else if (numStaminaChonk <= 0) {
            fText.setText("You cronched the all the stamina chonks!");
        }

        staminaText.setText(Integer.toString(stamina));
        save();

    }
    @Override
    public void onBackPressed() {

        return;
    }

}
