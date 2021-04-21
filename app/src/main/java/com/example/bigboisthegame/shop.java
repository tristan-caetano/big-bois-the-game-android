package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class shop extends AppCompatActivity {
    int pp, numStaminaChonk, numHealthPacks, coins;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHONKS = "chonks";
    public static final String PACKS = "packs";
    public static final String PP = "pp";
    public static final String COINS = "coins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        load();

        final TextView invT = (TextView) findViewById(R.id.invText2);
        final TextView shopT = (TextView) findViewById(R.id.shopText);
        Button returnB = (Button) findViewById(R.id.returnButton);
        Button buyHealthB = (Button) findViewById(R.id.buyHealth);
        Button buyPPB = (Button) findViewById(R.id.buyPP);
        Button buyStaminaB = (Button) findViewById(R.id.buyStamina);

        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
        shopT.setText("Welcome to the shop, you can buy things here.");

        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(startIntent);
            }
        });
        buyHealthB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins >= 50) {
                    numHealthPacks++;
                    shopT.setText(
                            "You got another health pack!\nYou now have " + Integer.toString(numHealthPacks) + " health packs!");
                    coins -= 50;
                    invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                } else {
                    shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                }
            }
        });
        buyPPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coins >= 20) {
                    pp += 5;
                    shopT.setText("You got more pp!\nYou now have " + Integer.toString(pp) + " pp!");
                    coins -= 20;
                    invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                } else {
                    shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                }

            }
        });
        buyStaminaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (coins >= 50) {
                    numStaminaChonk++;
                    shopT.setText(
                            "You got another stamina chonk!\nYou now have " + Integer.toString(numStaminaChonk) + " stamina chonks!");
                    coins -= 50;
                    invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                } else {
                    shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                }

            }
        });

    }

    public void load(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        numStaminaChonk = sharedPref.getInt(CHONKS, 0);
        numHealthPacks = sharedPref.getInt(PACKS, 2);
        pp = sharedPref.getInt(PP, 10);
        coins = sharedPref.getInt(COINS, 0);
    }

    public void save(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(CHONKS, numStaminaChonk);
        editor.putInt(PACKS, numHealthPacks);
        editor.putInt(PP, pp);
        editor.putInt(COINS, coins);
        editor.commit();

    }
}
