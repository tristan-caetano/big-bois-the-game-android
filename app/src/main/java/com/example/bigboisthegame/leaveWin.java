package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class leaveWin extends AppCompatActivity {

    public static final String ENEMY = "enemy";
    public static final String SHARED_PREFS = "sharedPrefs";
    String enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_win);

        TextView wText = (TextView) findViewById(R.id.winText2);
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        enemy = sharedPref.getString(ENEMY, "NAH");
        wText.setText("You were able to pursuade " + enemy + " to leave.");

        sharedPref.edit().remove("enemy").commit();

        Button returnB = (Button) findViewById(R.id.returnButton);
        returnB.setOnClickListener(new View.OnClickListener() {
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
