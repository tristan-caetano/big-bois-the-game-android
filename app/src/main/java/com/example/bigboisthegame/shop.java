package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class shop extends AppCompatActivity {
    int pp, numStaminaChonk, numHealthPacks, coins, tier, level, swordType, shieldType, axeType;
    String weapon;

    private long lastTouchTime = 0;
    private long currentTouchTime = 0;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CHONKS = "chonks";
    public static final String PACKS = "packs";
    public static final String PP = "pp";
    public static final String COINS = "coins";
    public static final String TIER = "tier";
    public static final String WEAPON = "weapon";
    public static final String LEVEL = "level";
    public static final String SWORDTYPE = "sword";
    public static final String AXETYPE = "axe";
    public static final String SHIELDTYPE = "shield";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final TextView invT = (TextView) findViewById(R.id.invText2);
        final TextView shopT = (TextView) findViewById(R.id.shopText);
        Button returnB = (Button) findViewById(R.id.returnButton);
        Button buyHealthB = (Button) findViewById(R.id.buyHealthPack);
        Button buyPPB = (Button) findViewById(R.id.buyPP);
        Button buyStaminaB = (Button) findViewById(R.id.buyStaminaChonk);
        Button buySword = (Button) findViewById(R.id.buySword);
        Button buyShield = (Button) findViewById(R.id.buyShield);
        Button buyAxe = (Button) findViewById(R.id.buyAxe);


        load();

        if(swordType > 0){

            buySword.setAlpha(.5f);

        }
        if(shieldType > 0){

            buyShield.setAlpha(.5f);

        }
        if(axeType > 0){

            buyAxe.setAlpha(.5f);

        }

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

        buySword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopT.setText(swordClass.getDesc() + "\nDouble tap to confirm purchase.");

                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    if(coins >= 500) {
                        coins -= 500;
                        shopT.setText("You purchased the sword!");
                        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                        weapon = "sword";
                        swordType = 0;
                        save();
                        lastTouchTime = 0;
                        currentTouchTime = 0;
                    } else{
                        shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                    }
                }
            }
        });

        buyShield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopT.setText(shieldClass.getDesc() + "\nDouble tap to confirm purchase.");

                lastTouchTime = currentTouchTime;
                currentTouchTime = System.currentTimeMillis();

                if (currentTouchTime - lastTouchTime < 250) {
                    if(coins >= 500) {
                        coins -= 500;
                        shopT.setText("You purchased the shield!");
                        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                        weapon = "shield";
                        shieldType = 0;
                        save();
                        lastTouchTime = 0;
                        currentTouchTime = 0;
                    } else{
                        shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                    }
                }
            }
        });

        buyAxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    shopT.setText(axeClass.getDesc() + "\nDouble tap to confirm purchase.");

                    lastTouchTime = currentTouchTime;
                    currentTouchTime = System.currentTimeMillis();

                    if (currentTouchTime - lastTouchTime < 250) {
                        if(coins >= 500) {
                            coins -= 500;
                            shopT.setText("You purchased the axe!");
                            invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nCoin Bois: " + Integer.toString(coins));
                            weapon = "axe";
                            axeType = 0;
                            save();
                            lastTouchTime = 0;
                            currentTouchTime = 0;
                        } else{
                            shopT.setText("You don't have enough coin bois to purchase that, idiot.");
                        }
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
        weapon = sharedPref.getString(WEAPON, "fist");
        tier = sharedPref.getInt(TIER, 0);
        level = sharedPref.getInt(LEVEL, 0);
        swordType = sharedPref.getInt(SWORDTYPE, 0);
        shieldType = sharedPref.getInt(SHIELDTYPE, 0);
        axeType = sharedPref.getInt(AXETYPE, 0);

    }

    public void save(){
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(CHONKS, numStaminaChonk);
        editor.putInt(PACKS, numHealthPacks);
        editor.putInt(PP, pp);
        editor.putInt(COINS, coins);
        editor.putString(WEAPON, weapon);
        editor.putInt(TIER, tier);
        editor.putInt(LEVEL, level);
        editor.putInt(SWORDTYPE, swordType);
        editor.putInt(SHIELDTYPE, shieldType);
        editor.putInt(AXETYPE, axeType);
        editor.commit();

    }
}
