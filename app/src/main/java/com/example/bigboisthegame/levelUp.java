package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class levelUp extends AppCompatActivity {

    int health, stamina, level, levelTracker, numHealthPacks, pp, tier;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String HEALTH = "health";
    public static final String STAMINA = "stamina";
    public static final String LEVEL = "level";
    public static final String PACKS = "packs";
    public static final String PP = "pp";
    public static final String TRACKER = "tracker";
    public static final String TIER = "tier";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        levelUp();
    }

    public void levelUp() {

        load();

        TextView lText = (TextView) findViewById(R.id.lText);
        TextView levelT = (TextView) findViewById(R.id.currentLevel);

        Button stayB = (Button) findViewById(R.id.stayButton);

        if (level < 10) {
            levelTracker = 0;
            level++;
            lText.setText("Aye guy, you leveled up! \nHecken real nice!\nYou are now level " + Integer.toString(level) + ".");

        } else if (level > 9) {
            tier++;
            lText.setText("So, you think you beat the game, huh.\n So, for the sake of extending playtime,\n let's reset your level,\n but lets increase your tier!");
            level = 0;
            numHealthPacks += 5;
            pp += 30;
        }

        if (health < 1000) {
            health = 1000;
            stamina = 100;
        } else {
            stamina = 100;
        }

        save();

        stayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(startIntent);
            }
        });

    }

    public void save(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(HEALTH, health);
        editor.putInt(STAMINA, stamina);
        editor.putInt(PACKS, numHealthPacks);
        editor.putInt(PP, pp);
        editor.putInt(LEVEL, level);
        editor.putInt(TRACKER, levelTracker);
        editor.putInt(TIER, tier);
        editor.commit();

    }

    public void load(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        health = sharedPref.getInt(HEALTH, 1000);
        stamina = sharedPref.getInt(STAMINA, 100);
        level = sharedPref.getInt(LEVEL, 0);
        levelTracker = sharedPref.getInt(TRACKER, 0);
        numHealthPacks = sharedPref.getInt(PACKS, 2);
        pp = sharedPref.getInt(PP, 10);
        tier = sharedPref.getInt(TIER, 0);

    }

    @Override
    public void onBackPressed() {

        return;
    }

}
