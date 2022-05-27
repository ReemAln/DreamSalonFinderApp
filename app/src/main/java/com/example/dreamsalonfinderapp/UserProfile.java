package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserProfile extends AppCompatActivity {

    Button searchStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();


            searchStart = findViewById(R.id.startButton);
            searchStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddServices.class);
                    startActivity(intent);
                }
            });


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.profile);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.addservices:
                            startActivity(new Intent(UserProfile.this, AddServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.services:
                            startActivity(new Intent(UserProfile.this, AllServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.update:
                            startActivity(new Intent(UserProfile.this, UpdateUserInfo.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            return true;
                    }
                    return false;
                }
            });
        }
    }
}