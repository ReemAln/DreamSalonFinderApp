package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements  BottomNavigationView.OnNavigationItemSelectedListener{

    private EditText userNameEdit, userEmailEdit, userPhoneEdit;
    private Button sendData;

    BottomNavigationView bottomNavigationView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEdit = findViewById(R.id.userNameEdit);
        userEmailEdit = findViewById(R.id.userPhoneEdit);
        userPhoneEdit = findViewById(R.id.userEmailEdit);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        user = new User();
        sendData = findViewById(R.id.buttonSubmit);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);



        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameEdit.getText().toString();
                String email = userEmailEdit.getText().toString();
                String phone = userPhoneEdit.getText().toString();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please add your information.", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToFirebase(name, email, phone);
                }
            }
        });
    }

            ProfileFragment profileFragment = new ProfileFragment();
            MapsFragment mapsFragment = new MapsFragment();
            AllServicesFragment allServicesFragment = new AllServicesFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                return true;

            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mapsFragment).commit();
                return true;

            case R.id.logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, allServicesFragment).commit();
                return true;
        }
        return false;

    }

        public void addDataToFirebase(String name, String email, String phone) {
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.setValue(user);

                        Toast.makeText(MainActivity.this, "Account Created.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Account Creation Failed." + error, Toast.LENGTH_SHORT).show();
                    }
                });


        }












        /* This is used for navigating through until all code is in the same folder
        Button btn = findViewById(R.id.buttonSubmit);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AllServices.class);
            startActivity(intent);
        });
                            */



}