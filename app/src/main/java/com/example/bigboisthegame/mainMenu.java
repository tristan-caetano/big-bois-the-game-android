package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mainMenu extends AppCompatActivity {
    int health, stamina, level, levelTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        load();

        TextView healthT = (TextView) findViewById(R.id.currentHealth);
        TextView staminaT = (TextView) findViewById(R.id.currentStamina);
        TextView mText = (TextView) findViewById(R.id.menuText);
        final TextView levelT = (TextView) findViewById(R.id.currentLevel);
        final TextView expT = (TextView) findViewById(R.id.currentExp);

        String menuText = "Wus poppin my guy?\nOh balls! You're here to heck stuff up yo!";

        healthT.setText(Integer.toString(health));
        staminaT.setText(Integer.toString(stamina));
        mText.setText(menuText);
        levelT.setText("Level: " + Integer.toString(level));
        expT.setText("EXP: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));

        /*
        Button settings = (Button) findViewById(R.id.settingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String SHARED_PREFS = "sharedPrefs";
                SharedPreferences settings = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                settings.edit().clear().commit();
                load();
                TextView healthT = (TextView) findViewById(R.id.currentHealth);
                TextView staminaT = (TextView) findViewById(R.id.currentStamina);
                healthT.setText(Integer.toString(health));
                staminaT.setText(Integer.toString(stamina));
                levelT.setText("Level: " + Integer.toString(level));
                expT.setText("EXP: " + Integer.toString(levelTracker) + " / " + Integer.toString(((level + 1) * 100)));
            }
        });
        */


        Button battle = (Button) findViewById(R.id.battleButton);
        battle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), initiateGame.class);
                startActivity(startIntent);
            }
        });

        Button shopB = (Button) findViewById(R.id.shopButton);
        shopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), shop.class);
                startActivity(startIntent);
            }
        });

    }

    public void load(){
        final String SHARED_PREFS = "sharedPrefs";
        final String HEALTH = "health";
        final String STAMINA = "stamina";
        final String LEVEL = "level";
        final String TRACKER = "tracker";

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        health = sharedPref.getInt(HEALTH, 1000);
        stamina = sharedPref.getInt(STAMINA, 100);
        level = sharedPref.getInt(LEVEL, 0);
        levelTracker = sharedPref.getInt(TRACKER, 0);

    }

    @Override
    public void onBackPressed() {

        return;
    }



}
