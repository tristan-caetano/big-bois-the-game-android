package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loseScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String COUNTER = "counter";
    public static final String ENEMY = "enemy";
    String enemy;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_screen);

        TextView loseText = (TextView) findViewById(R.id.loseText);
        Button stayB = (Button) findViewById(R.id.stayButton3);
        Button leaveB = (Button) findViewById(R.id.leaveButton);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        enemy = sharedPref.getString(ENEMY, "NAH");
        counter = sharedPref.getInt(COUNTER, 0);

        loseText.setText("Looks like you hecken died bro. " + enemy + " defiled your corpse. You killed "
                + Integer.toString(counter) + " enemies.");

        sharedPref.edit().clear().commit();

        stayB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(startIntent);
            }
        });

        leaveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        return;
    }
}
