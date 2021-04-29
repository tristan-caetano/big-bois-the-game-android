package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

    public class mmInventory extends AppCompatActivity {

        // Global Variables
        int pp, numStaminaChonk, numHealthPacks, coins, swordType, shieldType, axeType;
        String weapon;
        public static final String SHARED_PREFS = "sharedPrefs";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mm_inventory);

            // Loading saved values into global variables
            load();

            // Initializing text boxes and buttons
            final TextView invT = (TextView) findViewById(R.id.invTextMM);
            TextView cbT = (TextView) findViewById(R.id.coinViewMM);
            Button backB = (Button) findViewById(R.id.returnButtonMM);
            final Button equipAxe = (Button) findViewById(R.id.equipAxe);
            final Button equipSword = (Button) findViewById(R.id.equipSword);
            final Button equipShield = (Button) findViewById(R.id.equipShield);

            // Showing current inventory
            invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nWeapon: " + weapon);
            cbT.setText(Integer.toString(coins));

            // Determining what buttons should be greyed out; whether it is equipped or hasn't been purchased yet
            if(weapon.equals("sword") || swordType == 0) {equipSword.setAlpha(.5f);} else {equipSword.setAlpha(1f);};
            if(weapon.equals("shield") || shieldType == 0) {equipShield.setAlpha(.5f);} else {equipShield.setAlpha(1f);};
            if(weapon.equals("axe") || axeType == 0) {equipAxe.setAlpha(.5f);} else {equipAxe.setAlpha(1f);};

            // Return button
            backB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                    startActivity(startIntent);
                }
            });

            // Equip axe button if the user bought the axe
            equipAxe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!weapon.equals("axe") && axeType >= 1) {
                        weapon = "axe";

                        if(weapon.equals("sword") || swordType == 0) {equipSword.setAlpha(.5f);} else {equipSword.setAlpha(1f);};
                        if(weapon.equals("shield") || shieldType == 0) {equipShield.setAlpha(.5f);} else {equipShield.setAlpha(1f);};
                        if(weapon.equals("axe") || axeType == 0) {equipAxe.setAlpha(.5f);} else {equipAxe.setAlpha(1f);};
                        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nWeapon: " + weapon);

                        save();
                    }
                }
            });

            // Equip shield button if the user bought the shield
            equipShield.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!weapon.equals("shield") && shieldType >= 1) {
                        weapon = "shield";

                        if(weapon.equals("sword") || swordType == 0) {equipSword.setAlpha(.5f);} else {equipSword.setAlpha(1f);};
                        if(weapon.equals("shield") || shieldType == 0) {equipShield.setAlpha(.5f);} else {equipShield.setAlpha(1f);};
                        if(weapon.equals("axe") || axeType == 0) {equipAxe.setAlpha(.5f);} else {equipAxe.setAlpha(1f);};
                        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nWeapon: " + weapon);

                        save();
                    }
                }
            });

            // Equip sword button if the user bought the sword
            equipSword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!weapon.equals("sword") && swordType >= 1) {
                        weapon = "sword";

                        if(weapon.equals("sword") || swordType == 0) {equipSword.setAlpha(.5f);} else {equipSword.setAlpha(1f);};
                        if(weapon.equals("shield") || shieldType == 0) {equipShield.setAlpha(.5f);} else {equipShield.setAlpha(1f);};
                        if(weapon.equals("axe") || axeType == 0) {equipAxe.setAlpha(.5f);} else {equipAxe.setAlpha(1f);};
                        invT.setText("Health Packs: " + Integer.toString(numHealthPacks) + "\nStamina Chonks: " + Integer.toString(numStaminaChonk) + "\nPP: " + Integer.toString(pp) + "\nWeapon: " + weapon);

                        save();
                    }
                }
            });
        }

        // Function that loads values into variables
        public void load(){
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            saveLoadClass slc = new saveLoadClass(sharedPref);
            numStaminaChonk = slc.numStam(numStaminaChonk, false);
            numHealthPacks = slc.numHeal(numHealthPacks, false);
            pp = slc.pp(pp, false);
            coins = slc.numStam(coins, false);
            weapon = slc.weapon(weapon, false);
            swordType = slc.swordType(swordType, false);
            shieldType = slc.shieldType(shieldType, false);
            axeType = slc.axeType(axeType, false);
        }

        // Function that saves values
        public void save(){
            SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            saveLoadClass slc = new saveLoadClass(sharedPref);
            slc.weapon(weapon, true);
        }
    }

