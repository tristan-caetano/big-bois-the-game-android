package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class inventoryScreen extends AppCompatActivity {
    int pp, numStaminaChonk, numHealthPacks, coins;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHONKS = "chonks";
    public static final String PACKS = "packs";
    public static final String PP = "pp";
    public static final String COINS = "coins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_screen);

        load();

        TextView invT = (TextView) findViewById(R.id.invText);
        TextView cbT = (TextView) findViewById(R.id.coinView);
        Button backB = (Button) findViewById(R.id.backButton);

        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp));
        cbT.setText(Integer.toString(coins));

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), initiateGame.class);
                startActivity(startIntent);
            }
        });

    }

    public void load(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        numStaminaChonk = sharedPref.getInt(CHONKS, 0);
        numHealthPacks = sharedPref.getInt(PACKS, 2);
        pp = sharedPref.getInt(PP, 10);
        coins = sharedPref.getInt(COINS, 0);
        System.out.println("Coins INV: " + coins);

    }
}
