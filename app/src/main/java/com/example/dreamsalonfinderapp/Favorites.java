package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

        }
    }
}