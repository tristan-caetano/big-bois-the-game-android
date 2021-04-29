package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class initiateGame extends AppCompatActivity {

    //Player Variables
    int health, stamina, counter, attack, ehealthTemp, level, levelTracker, xpMilestone,
            numStaminaChonk, staminaChonkAmt, chance, numHealthPacks, healthPackAmt, pp, coins,
            tier, weaponType;
    double levelMultiplier, doubleLevel;
    boolean bossLevel;
    String weapon;

    //Music Player
    MediaPlayer player;

    //Enemy Variables
    int enemyHealth, maxEnemyHealth, maxEnemyAtt, bossChance, initHealth;
    String enemy;

    //Initializing static strings for saved values
    public static final String SHARED_PREFS = "sharedPrefs";
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiate_game);

        startBattle();
    }

    //Sets Default Values for Player
    public void playerSetup() {
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

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

        //Setting on screen buttons
        Button fight = (Button) findViewById(R.id.fightButton);
        Button specialB = (Button) findViewById(R.id.specialButton);
        Button dronkB = (Button) findViewById(R.id.dronkButton);
        Button cronchB = (Button) findViewById(R.id.cronchButton);
        Button runB = (Button) findViewById(R.id.runButton);
        Button inventoryB = (Button) findViewById(R.id.inventoryButton);
        Button blockB = (Button) findViewById(R.id.blockButton);
        //Button suicideB = (Button) findViewById(R.id.suicideButton);

        //Setting value for amount of xp that needs to be reached before leveling up
        xpMilestone = (100 * (1 + level));

        //Calling method to setup default values
        playerSetup();

        enemy = slc.enemy(enemy, false);
        enemyHealth = slc.enemyHealth(enemyHealth, false);

        //If the game fails to properly name an enemy, the function to do so is called again
        if (enemy.equals("NAH")) {
            enemy = getEnemyName();
            System.out.println("Getting New Enemy Name");
        }

        System.out.println("ENEMY: " + enemy);
        //Displaying greeting text
        TextView fText = (TextView) findViewById(R.id.fightText);
        fText.setText("Boi, " + enemy + "'s got " + Integer.toString(enemyHealth) + " health left. \nWhat you doin now fam?");

        updateDisplay();

        //Calls functions depending on the button pressed
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

    //Function that updates all current values on screen
    public void updateDisplay(){

        //Setting the text boxes on screen
        TextView healthT = (TextView) findViewById(R.id.currentHealth2);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina2);
        TextView healthText = (TextView) findViewById(R.id.healthText2);
        TextView staminaText = (TextView) findViewById(R.id.staminaText2);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth);
        TextView enemyName = (TextView) findViewById(R.id.enemyName);
        TextView levelT = (TextView) findViewById(R.id.currentLevel);
        TextView expT = (TextView) findViewById(R.id.currentExp);
        TextView tierText = (TextView) findViewById(R.id.currentTier);

        //Displaying all relevant on screen value
        healthText.setText("Health Bois: ");
        staminaText.setText("Stamina Bois: ");
        healthT.setText(Integer.toString(health));
        staminaT.setText(Integer.toString(stamina));
        levelT.setText("Level: " + Integer.toString(level));
        tierText.setText("Tier: " + Integer.toString(tier));
        expT.setText("EXP: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));
        enemyHealthT.setText(Integer.toString(enemyHealth));
        enemyName.setText(enemy + "'s Health:");

    }

    //Function that gets an enemy name whether it be a boss or not
    //Returns the string of the enemy name
    String getEnemyName() {

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

        bossLevel = false;

        System.out.println("ENEMY: " + (counter % 5));
        if (counter % 5 == 0) {
            System.out.println("Check for boss: " + (bossChance * level));
            if (rand.nextInt(100) < (bossChance * level)) {
                bossLevel = true;
                System.out.println("IsBoss");
                int[] bossHealthChance = {2000, 3000, 4000};
                int bossHealth = bossHealthChance[rand.nextInt(bossHealthChance.length)];

                String[] enemies = {"The Big Boi", "Ultimate Salad", "PoopyJuice", "Mononucleosis",
                        "The Inhibition", "Big Sad", "Viscus Fart Chamber", "COVID-19", "Karen"};
                enemy = enemies[rand.nextInt(enemies.length)];

                enemyHealth = bossHealth;
            }

        }

        if (!bossLevel) {

            System.out.println("Assigning enemy name.");

            String[] enemies = {"Prime Time Bad Guy", "Normie", "Big Salad", "Lowly Salad", "Bad Crouton",
                    "Apple Fanboy", "Long Boi", "Sad Boi", "Orange Juice", "Bad Larry", "Big Kidney Stone",
                    "Fart Chamber"};
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

        slc.enemy(enemy, true);
        slc.enemyHealth(enemyHealth, true);
        initHealth = enemyHealth;
        slc.initHealth(initHealth, true);

        save();

        return enemy;
    }

    //**ALL FUNCTIONS THAT ARE ATTACK FUNCTIONS ALSO HAVE THE ENEMY ATTACK YOU BACK**

    //Function that does the standard attack for the player
    void attack() {
        String playerAtt = "";
        String enemyAtt = "";

        System.out.println("Weapon Type: " + weaponType);

        TextView fText = (TextView) findViewById(R.id.fightText);

        Button fight = (Button) findViewById(R.id.fightButton);
        Button specialB = (Button) findViewById(R.id.specialButton);
        Button dronkB = (Button) findViewById(R.id.dronkButton);
        Button cronchB = (Button) findViewById(R.id.cronchButton);
        Button runB = (Button) findViewById(R.id.runButton);
        Button inventoryB = (Button) findViewById(R.id.inventoryButton);
        Button blockB = (Button) findViewById(R.id.blockButton);
        //Button suicideB = (Button) findViewById(R.id.suicideButton);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

        if (stamina > 10) {
            doubleLevel = level;
            int damageDealt;

            //Player's Turn
            findWeapon fw = new findWeapon(tier, weapon, level, 0);
            findWeapon rfw = new findWeapon(tier, weapon, level, fw.findType(sharedPref));
            System.out.println("Sword Type Att: " + fw.findType(sharedPref) + "\nWeapon: " + weapon);
            damageDealt = rfw.findDmg();

            int damageTaken = rand.nextInt(maxEnemyAtt);

            //Enemy Turn
            if(weapon.equals("shield")){
                System.out.println("HAS SHIELD");
                if(rand.nextInt(10) == 0) {
                    System.out.println("BLOCKED");
                    damageTaken = 0;
                }
            }
            health -= damageTaken;
            enemyHealth -= damageDealt;

            System.out.println("Damage Dealt: " + damageDealt);
            System.out.println("Enemy Health: " + enemyHealth);


            if(enemyHealth < 0){
                enemyHealth = 0;
            }

            stamina -= (damageDealt / 10);
            save();

            if (stamina < 0) {
                stamina = 0;
            }

            if(damageDealt > 0){
                playerAtt = "Boi, you hit " + enemy + " for " + damageDealt + " damage, big yeet. ";
            } else if(damageDealt == 0){
                playerAtt = "Bruh, you missed, not epic. ";
            }

            if(damageTaken > 0){
                enemyAtt = enemy + " also hit you for " + damageTaken + ", oof. What's poppin next?";
            } else if(damageTaken == 0 && weapon.equals("shield")){
                enemyAtt = "You blocked " + enemy + "'s attack! Very Epic.";
            } else if(damageTaken == 0 && !(weapon.equals("shield"))){
                enemyAtt = enemy + "'s attack missed! Very cool.";
            }

            fText.setText(playerAtt + enemyAtt);

            updateDisplay();

        } else {

            fText.setText("You are too tired to fight.");

        }

        if (enemyHealth < 1 && health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), ddScreen.class);
            startActivity(startIntent);
        } else if (enemyHealth < 1) {
            slc.enemy(enemy,true);
            Intent startIntent = new Intent(getApplicationContext(), winScreen.class);
            startActivity(startIntent);

        } else if (health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), loseScreen.class);
            startActivity(startIntent);
        }
    }

    //Function that blocks a random amount of damage from the enemy, and restores a random amount of stamina
    public void block() {

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

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

        fText.setText("You blocked " + Math.round(bChance) + "% of " + enemy + "'s attack. \nYou took "
                + Math.round(blockedDamage) + " damage.");
        updateDisplay();

        System.out.println("NOT DEAD: " + health);

        if (enemyHealth < 1 && health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), ddScreen.class);
            startActivity(startIntent);
        } else if (enemyHealth < 1) {
            slc.enemy(enemy,true);
            Intent startIntent = new Intent(getApplicationContext(), winScreen.class);
            startActivity(startIntent);

        } else if (health < 1) {
            Intent startIntent = new Intent(getApplicationContext(), loseScreen.class);
            startActivity(startIntent);
        }
    }

    //Function for taking a health pack to restore health
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

    //Function for taking a stamina chonk to restore stamina
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

    //Saving all variables to Shared Prefs
    public void save() {

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

        slc.health(health,true);
        slc.stamina(stamina,true);
        slc.numStam(numStaminaChonk,true);
        slc.numHeal(numHealthPacks,true);
        slc.pp(pp, true);
        slc.counter(counter,true);
        slc.level(level,true);
        slc.coins(coins,true);
        slc.levelTracker(levelTracker,true);
        slc.enemy(enemy,true);
        slc.enemyHealth(enemyHealth,true);
        slc.initHealth(initHealth, true);
        slc.tier(tier,true);
        slc.weapon(weapon,true);

    }

    //Loading all values from shared prefs
    public void load() {

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saveLoadClass slc = new saveLoadClass(sharedPref);

        health = slc.health(health,false);
        stamina = slc.stamina(stamina,false);
        numStaminaChonk = slc.numStam(numStaminaChonk,false);
        numHealthPacks = slc.numHeal(numHealthPacks,false);
        pp = slc.pp(pp, false);
        counter = slc.counter(counter,false);
        level = slc.level(level,false);
        coins = slc.coins(coins,false);
        levelTracker = slc.levelTracker(levelTracker,false);
        enemy = slc.enemy(enemy,false);
        enemyHealth = slc.enemyHealth(enemyHealth,false);
        initHealth = slc.initHealth(initHealth, false);
        tier = slc.tier(tier,false);
        weapon = slc.weapon(weapon,false);

    }



    //Does not allow player to hit return on device to go to previous screen from here
    //IE: locked in battle

    @Override
    public void onBackPressed() {

        return;
    }

}
