package com.example.dreamsalonfinderapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    //variables
    private EditText userNameEdit, userEmailEdit, userPhoneEdit, userEditPassword, userConfirmPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    UserHelperClass user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_sign_up);



            userNameEdit = findViewById(R.id.name);
            userEmailEdit = findViewById(R.id.email);
            userPhoneEdit = findViewById(R.id.phone);
            userEditPassword = findViewById(R.id.password);
            userConfirmPassword = findViewById(R.id.confirmPassword);

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("UserHelperClass");

            user = new UserHelperClass();
            regBtn = findViewById(R.id.reg_btn);


        /*  This is for the bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        */

            regBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SignUp.this, Login.class);
                    String name = userNameEdit.getText().toString();
                    String email = userEmailEdit.getText().toString();
                    String phone = userPhoneEdit.getText().toString();
                    String password = userEditPassword.getText().toString();
                    String confirmPassword = userConfirmPassword.getText().toString();

                    if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword)) {
                        Toast.makeText(SignUp.this, "Please add your information.", Toast.LENGTH_SHORT).show();

                    } else if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(phone)){
                        addDataToFirebase(name, email, phone, password, confirmPassword);
                        startActivity(intent);
                    }
                }
            });
        }

        public void addDataToFirebase (String name, String email, String phone, String password, String confirmPassword){
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            user.setConfirmPassword(confirmPassword);


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.setValue(user);
                    Toast.makeText(SignUp.this, "Account Created.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SignUp.this, "Account Creation Failed." + error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }





