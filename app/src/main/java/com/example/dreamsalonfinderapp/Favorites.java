package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();







            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.favorites);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.addservices:
                            startActivity(new Intent(Favorites.this, AddServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            startActivity(new Intent(Favorites.this, UserProfile.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.update:
                            startActivity(new Intent(Favorites.this, UpdateUserInfo.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.favorites:
                            return true;
                    }
                    return false;
                }
            });

        }
    }
}