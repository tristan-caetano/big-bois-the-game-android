package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class specialAttack extends AppCompatActivity {

    Random rand = new Random();

    int health, stamina, multiplier, ehealthTemp, level, levelTracker, xpMilestone, numStaminaChonk, staminaChonkAmt, numHealthPacks, healthPackAmt, healthPackDropChance, pp, coins;
    double levelMultiplier;

    int attack = 100;
    int chance = 100;


    int enemyHealth, maxEnemyHealth, bossChance, initHealth;
    String enemy;

    int maxEnemyAtt = 100;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_attack);

        load();

        Button slapB = (Button) findViewById(R.id.slapButton);
        Button pp4SB = (Button) findViewById(R.id.pp4StaminaButton);
        Button cronchB = (Button) findViewById(R.id.cronchyButton);
        Button leaveB = (Button) findViewById(R.id.leaveButton);
        Button returnB = (Button) findViewById(R.id.returnButton);

        TextView healthT = (TextView) findViewById(R.id.currentHealth3);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina3);
        TextView healthText = (TextView) findViewById(R.id.healthText3);
        TextView staminaText = (TextView) findViewById(R.id.staminaText3);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth2);
        TextView enemyName = (TextView) findViewById(R.id.enemyName2);
        TextView fText = (TextView) findViewById(R.id.fightText2);
        TextView levelT = (TextView) findViewById(R.id.currentLevel2);
        TextView expT = (TextView) findViewById(R.id.currentExp2);

        fText.setText("Boi, " + enemy + "'s got " + Integer.toString(enemyHealth) + " health left. \nWhat you doin now fam?");
        enemyName.setText(enemy + "'s Health: ");
        healthText.setText("Health Bois: ");
        staminaText.setText("Stamina Bois: ");
        healthT.setText(Integer.toString(health));
        staminaT.setText(Integer.toString(stamina));
        levelT.setText("Level: " + Integer.toString(level));
        expT.setText("EXP: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));
        enemyHealthT.setText(Integer.toString(enemyHealth));

        slapB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slap();
                save();

            }
        });

        cronchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronchy();
                save();
            }
        });

        pp4SB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppToStamina();
                save();
            }
        });

        leaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leave();
                save();
            }
        });

        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), initiateGame.class);
                startActivity(startIntent);
            }
        });

    }

    public void slap() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth3);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina3);
        TextView healthText = (TextView) findViewById(R.id.healthText3);
        TextView staminaText = (TextView) findViewById(R.id.staminaText3);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth2);
        TextView enemyName = (TextView) findViewById(R.id.enemyName2);
        TextView fText = (TextView) findViewById(R.id.fightText2);

        if (pp > 1) {

            if (stamina > 9) {

                int sDamageDealt = rand.nextInt(attack) * 2;
                int sDamageTaken = rand.nextInt(maxEnemyAtt);
                health -= sDamageTaken;
                enemyHealth -= sDamageDealt;
                stamina -= (sDamageDealt / 20);
                staminaT.setText(Integer.toString(stamina));
                fText.setText("You gave " + enemy + " a phat slap for double damage!\nBtw that's " + Integer.toString(sDamageDealt)
                        + " damage.\nAlso you took like " + Integer.toString(sDamageTaken) + ". Lol heckin loser.");
                pp -= 2;

                healthT.setText(Integer.toString(health));
                enemyHealthT.setText(Integer.toString(enemyHealth));
            } else {
                fText.setText("You are too tired to do this move, tired boi");
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

        } else {
            fText.setText("You don't have enough PP for this move, dummy.");
        }

    }

    public void cronchy() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth3);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina3);
        TextView healthText = (TextView) findViewById(R.id.healthText3);
        TextView staminaText = (TextView) findViewById(R.id.staminaText3);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth2);
        TextView enemyName = (TextView) findViewById(R.id.enemyName2);
        TextView fText = (TextView) findViewById(R.id.fightText2);

        if (stamina > 9) {
            if (pp > 2) {
                int sDamageDealt = rand.nextInt(attack) * 3;
                int sDamageTaken = rand.nextInt(maxEnemyAtt);
                health -= sDamageTaken;
                enemyHealth -= sDamageDealt;
                stamina -= (sDamageDealt / 30);
                staminaT.setText(Integer.toString(stamina));
                fText.setText("You ate the cronchy malk and gave \n" + enemy
                        + " a PHAT slap for THRICE damage.\nThat's like " + Integer.toString(sDamageDealt)
                        + " damage! Give or take.\nAlso you took like " + Integer.toString(sDamageTaken) + ". Lol heckin loser.");
                pp -= 3;
                healthT.setText(Integer.toString(health));
                enemyHealthT.setText(Integer.toString(enemyHealth));

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

            } else {
                fText.setText("You don't have enough PP for this move, dummy.");
            }
        } else {
            fText.setText("You are too tired to do this move, tired boi");

        }
    }

    public void leave() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth3);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina3);
        TextView healthText = (TextView) findViewById(R.id.healthText3);
        TextView staminaText = (TextView) findViewById(R.id.staminaText3);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth2);
        TextView enemyName = (TextView) findViewById(R.id.enemyName2);
        TextView fText = (TextView) findViewById(R.id.fightText2);

        if (initHealth > 1000) {
            fText.setText("You can't ask " + enemy + " to leave, it's a boss!");
        } else {

            if (pp > 4) {
                int sChance = rand.nextInt(chance);
                if (sChance > 50) {
                    fText.setText("You were able to pursuade " + enemy + " to leave.");
                    pp -= 5;
                    Intent startIntent = new Intent(getApplicationContext(), leaveWin.class);
                    startActivity(startIntent);

                } else {
                    fText.setText("Well, " + enemy + " told you to stop being a weenie and fight.");
                    pp -= 5;
                }
            }
            // If the player doesn't have enough pp for the move
            else {

                fText.setText("You don't have enough PP for this move, dummy.");
            }
        }
    }

    public void ppToStamina() {

        TextView healthT = (TextView) findViewById(R.id.currentHealth3);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina3);
        TextView healthText = (TextView) findViewById(R.id.healthText3);
        TextView staminaText = (TextView) findViewById(R.id.staminaText3);
        TextView enemyHealthT = (TextView) findViewById(R.id.enemyHealth2);
        TextView enemyName = (TextView) findViewById(R.id.enemyName2);
        TextView fText = (TextView) findViewById(R.id.fightText2);

        if (stamina < 100) {
            if (pp > 9) {

                stamina += 15;
                staminaT.setText(Integer.toString(stamina));
                fText.setText("You got 15 more stamina!");
                pp -= 10;
                if (stamina > 100) {
                    stamina = 100;
                    staminaT.setText(Integer.toString(stamina));
                }

            } else {
                fText.setText("You don't have enough PP for this move, dummy.");
            }
        } else {

            fText.setText("Your stamina is already maxed boi!");

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
        editor.putInt(LEVEL, level);
        editor.putInt(COINS, coins);
        editor.putInt(TRACKER, levelTracker);
        editor.putString(ENEMY, enemy);
        editor.putInt(EHEALTH, enemyHealth);
        editor.putInt(IHEALTH, initHealth);
        editor.commit();

    }

    public void load(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        health = sharedPref.getInt(HEALTH, 1000);
        stamina = sharedPref.getInt(STAMINA, 100);
        numStaminaChonk = sharedPref.getInt(CHONKS, 0);
        numHealthPacks = sharedPref.getInt(PACKS, 2);
        pp = sharedPref.getInt(PP, 10);
        level = sharedPref.getInt(LEVEL, 0);
        coins = sharedPref.getInt(COINS, 0);
        levelTracker = sharedPref.getInt(TRACKER, 0);
        initHealth = sharedPref.getInt(IHEALTH, 0);
        enemy = sharedPref.getString(ENEMY, "NAH");
        enemyHealth = sharedPref.getInt(EHEALTH, 500);
    }
}
