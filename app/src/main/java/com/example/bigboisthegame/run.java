package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class run extends AppCompatActivity {

    public static final String COINS = "coins";
    public static final String SHARED_PREFS = "sharedPrefs";

    int coins;
    int droppedCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        TextView runT = (TextView) findViewById(R.id.runText);
        Button runB = (Button) findViewById(R.id.wussButton);

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        coins = sharedPref.getInt(COINS, 0);

        System.out.println("Coins: " + coins);

        if(coins > 49){
            coins -= 50;
            runT.setText("You ran like a pansy, and you dropped 50 CB.\nGood job." );
        } else if(coins < 50 && coins > 0){
            runT.setText("You ran like a pansy, and you dropped " + Integer.toString(coins) + " CB.\nGood job." );
            coins = 0;
        }else{
            runT.setText("You ran like a pansy. \nGood job." );
        }

        if(coins < 0){
            coins = 0;
        }

        editor.putInt(COINS, coins);
        editor.commit();

        sharedPref.edit().remove("enemy").commit();

         runB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(startIntent);

            }
        });
    }

    @Override
    public void onBackPressed() {

        return;
    }
}
