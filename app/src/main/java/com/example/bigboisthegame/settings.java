package com.example.bigboisthegame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    String black = "#000000";
    String white = "#FFFFFF";
    String darkGrey = "#1C1B1B";
    String lightGrey = "#353535";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button returnB = (Button) findViewById(R.id.returnButtonS);
        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(startIntent);
            }
        });

        Button bwB = (Button) findViewById(R.id.bwTheme);
        bwB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(black, white);
                save();
            }
        });

        Button piB = (Button) findViewById(R.id.piTheme);
        piB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pi();
                save();
            }
        });

        Button gB = (Button) findViewById(R.id.greyTheme);
        gB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(darkGrey, lightGrey);
                save();
            }
        });




    }

    public void changeTheme(String color1, String color2){

        Button piB=(Button)findViewById(R.id.piTheme);
        piB.setTextColor(Color.parseColor(black)); // custom color

    }

    public void pi(){

        Button piB=(Button)findViewById(R.id.piTheme);
        piB.setTextColor(Color.parseColor(white));
        piB.setTextColor(Color.parseColor(white));

    }

    public void grey(){


    }

    public void save(){

    }

}