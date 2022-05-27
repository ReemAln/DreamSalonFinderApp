package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AllServices extends AppCompatActivity {
    Intent searchMapIntent;
    String baseUrl ="https://www.google.com/maps/search/", searchUrl = "", key = "key";
    Button searchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_services);

        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


            searchBtn = findViewById(R.id.searchBtn);
            searchBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                   Intent intent = new Intent(AllServices.this, AddServices.class);
                   startActivity(intent);
                }
            });


            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

            bottomNavigationView.setSelectedItemId(R.id.addservices);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.favorites:
                            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            startActivity(new Intent(getApplicationContext(), UserProfile.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.addservices:
                            return true;
                    }
                    return false;
                }
            });

        }

    }
