package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AllServices extends AppCompatActivity {
    Intent searchMapIntent, returnIntent;
    String haircut, shampoo, styling, baseUrl, searchUrl = "", key = "key";
    Boolean isHairCutChecked = false;
    ImageButton haircutbtn;
    Button searchBtn, returnBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);
        haircutbtn= findViewById(R.id.basic_service1_img);
        searchBtn= findViewById(R.id.searchBtn);
        returnBtn = findViewById(R.id.goBackButton);


        baseUrl = "https://www.google.com/maps/search/";


        haircutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHairCutChecked = true;
                haircut = "haircut";
            }/* Once clicked the buttons set a string to the proper search param.
               Then once the final button is pushed it appends the string to the base url
               into the search url */

        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchUrl = baseUrl + haircut;
                searchMapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                searchMapIntent.putExtra(key, searchUrl);
                startActivity(searchMapIntent);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnIntent);
            }
        });

    }
}