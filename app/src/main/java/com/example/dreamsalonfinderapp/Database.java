package com.example.dreamsalonfinderapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database extends AppCompatActivity {

    private EditText userNameEdit, userEmailEdit, userPhoneEdit;
    private Button sendData;

    BottomNavigationView bottomNavigationView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    UserHelperClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEdit = findViewById(R.id.userNameEdit);
        userEmailEdit = findViewById(R.id.userPhoneEdit);
        userPhoneEdit = findViewById(R.id.userEmailEdit);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserHelperClass");

        user = new UserHelperClass();
        sendData = findViewById(R.id.buttonSubmit);


        /*  This is for the bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        */

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameEdit.getText().toString();
                String email = userEmailEdit.getText().toString();
                String phone = userPhoneEdit.getText().toString();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(email)) {
                    Toast.makeText(Database.this, "Please add your information.", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToFirebase(name, email, phone);
                }
            }
        });
    }

    public void addDataToFirebase(String name, String email, String phone) {
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(user);

                Toast.makeText(Database.this, "Account Created.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Database.this, "Account Creation Failed." + error, Toast.LENGTH_SHORT).show();
            }
        });

    }
    }













