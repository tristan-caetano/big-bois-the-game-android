package com.example.bigboisthegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ddScreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd_screen);

        TextView ddText = (TextView) findViewById(R.id.ddText);
        Button stayB = (Button) findViewById(R.id.stayButton4);
        Button leaveB = (Button) findViewById(R.id.leaveButton2);

        ddText.setText("So, uh, you both died at the same time.\nThat's pretty rare so, good job?\nYou're still dead though.\n");

        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
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
