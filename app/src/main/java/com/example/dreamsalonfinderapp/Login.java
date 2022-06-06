package com.example.dreamsalonfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.spec.ECField;

public class Login extends AppCompatActivity {

    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText;
    TextInputLayout email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

            //Hooks
            image = findViewById(R.id.logo_image);
            logoText = findViewById(R.id.logo_name);
            email = findViewById(R.id.emailLogin);
            password = findViewById(R.id.passwordLogin);
            callSignUp = findViewById(R.id.signup_screen);
            login_btn = findViewById(R.id.loginBtn);
            //image=findViewById(R.id.imageView);


            // this needs to be implemented with the database
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login.this, UserProfile.class);
                    startActivity(intent);
                }
            });

            callSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Login.this, SignUp.class);
                   // startActivity(intent);
                    try {
                        Pair[] pairs = new Pair[6];
                        pairs[0] = new Pair<View, String>(image, "logo_image");
                        pairs[1] = new Pair<View, String>(logoText, "logo_text");
                        pairs[2] = new Pair<View, String>(email, "username_transition");
                        pairs[3] = new Pair<View, String>(password, "password_transition");
                        pairs[4] = new Pair<View, String>(login_btn, "btn_Go_transition");
                        pairs[5] = new Pair<View, String>(callSignUp, "btn_login_signup_transition");
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                        startActivity(intent, options.toBundle());
                    } catch (Exception e) {
                        Toast.makeText(Login.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


        private Boolean validateUserName () {
            String val = email.getEditText().getText().toString();

            if (val.isEmpty()) {
                email.setError("Field cannot be empty");
                return false;
            } else {
                email.setError(null);
                email.setErrorEnabled(false);
                return true;
            }
        }

        private Boolean validatePassword () {
            String val = password.getEditText().getText().toString();


            if (val.isEmpty()) {
                password.setError("Field cannot be empty");
                return false;
            } else {
                password.setError(null);
                password.setErrorEnabled(false);
                return true;
            }

        }




        public void loginUser (View view){
            //validate login screen
            if (!validateUserName() | !validatePassword()) {
                return;
            } else {
                isUser();
            }
        }

        private void isUser () {
            final String userEnteredUsername = email.getEditText().getText().toString().trim();
            final String userEnteredPassword = password.getEditText().getText().toString().trim();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUser = reference.orderByChild("email").equalTo(userEnteredUsername);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        email.setError(null);
                        email.setErrorEnabled(false);
                        String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                        if (passwordFromDB.equals(userEnteredPassword)) {
                            email.setError(null);
                            email.setErrorEnabled(false);
                            String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                            String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                            String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
                            intent.putExtra("password", passwordFromDB);
                            startActivity(intent);
                        } else {
                            //progressBar.setVisibility(View.GONE);
                            password.setError("Wrong Password");
                            password.requestFocus();
                        }
                    } else {
                        //progressBar.setVisibility(View.GONE);
                        email.setError("No such UserHelperClass exist");
                        email.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
}