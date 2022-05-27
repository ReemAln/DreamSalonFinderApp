package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UpdateUserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.update);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.addservices:
                            startActivity(new Intent(UpdateUserInfo.this, AddServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.favorites:
                            startActivity(new Intent(UpdateUserInfo.this, AllServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            startActivity(new Intent(UpdateUserInfo.this, UserProfile.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.update:
                            return true;
                    }
                    return false;
                }
            });
        }
    }
}