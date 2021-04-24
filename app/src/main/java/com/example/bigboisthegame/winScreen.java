package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class winScreen extends AppCompatActivity {

    int health, stamina, counter, multiplier, attack, ehealthTemp, level, levelTracker, xpMilestone,
            numStaminaChonk, staminaChonkAmt, chance, numHealthPacks, healthPackAmt,
            healthPackDropChance, pp, coins, initHealth, tier, leftOver;
    double levelMultiplier;
    String enemy;

    Random rand = new Random();

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
    public static final String IHEALTH = "iHealth";
    public static final String TIER = "tier";
    public static final String LEFTOVER = "leftOver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
        win();
    }

    void win(){

        load();

        TextView winText = (TextView) findViewById(R.id.winText);

        if (stamina < 100) {
            stamina += 20;
            if (stamina > 100) {
                stamina = 100;
            }
        }
        counter++;
        ehealthTemp = initHealth/10;
        coins += Math.abs(ehealthTemp);
        System.out.println("Coins WIN: " + coins + "\nEHEALTHTMP: " + Math.abs(ehealthTemp) + "\nNORMAL EHELTH: " + ehealthTemp);
        levelTracker += (double)((Math.abs(ehealthTemp)) * ((double)((tier + 10)/10)));
        healthPackDropChance = 70;

        if (rand.nextInt(100) < healthPackDropChance) {
            numHealthPacks++;
            numStaminaChonk++;
            winText.setText("Thank god. " + enemy + " has passed the HECK away. \nHeck yeah! The " + enemy
                    + " dropped a\nhealth pack and stamina chonk. You now have " + Integer.toString(numHealthPacks)
                    + " health pack(s) \nand " + Integer.toString(numStaminaChonk) + " stamina chonk(s).\nYou got "
                    + Integer.toString(Math.abs(ehealthTemp)) + " CB and 5 more pp!\nYou have killed " + Integer.toString(counter) + " enemie(s) so far.");
            pp += 5;
        }
        else {
            winText.setText("Thank god. " + enemy + " has passed the HECK away.\nYou got " + Math.abs(ehealthTemp)
                    + " CB!\nYou have killed " + counter + " enemie(s) so far and got 5 more pp!.");
            System.out.println("");
            pp += 5;
        }

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sharedPref.edit().remove("enemy").commit();

        save();

        Button stayB = (Button) findViewById(R.id.stayButton2);

        stayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (levelTracker > ((level + 1) * 100)) {
                    leftOver = levelTracker - ((level + 1) * 100);
                    System.out.println("Level TEST: " + Integer.toString(level));
                    System.out.println("EXP TEST: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));
                    Intent startIntent = new Intent(getApplicationContext(), levelUp.class);
                    startActivity(startIntent);
                }else {
                    Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                    startActivity(startIntent);
                }
            }
        });


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
        editor.putInt(IHEALTH, initHealth);
        editor.putInt(LEFTOVER, leftOver);
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
        enemy = sharedPref.getString(ENEMY, " ");
        initHealth = sharedPref.getInt(IHEALTH, 0);
        tier = sharedPref.getInt(TIER, 0);
    }
    @Override
    public void onBackPressed() {

        return;
    }
}
