package com.example.dreamsalonfinderapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Button callRegisterScreen, login_btn;
    ImageView image;
    TextView logoText;
    TextInputLayout email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

            //Hooks
            image = findViewById(R.id.logo_image);
            logoText = findViewById(R.id.logo_name);
            email = findViewById(R.id.emailLogin);
            password = findViewById(R.id.passwordLogin);
            callRegisterScreen = findViewById(R.id.registerUserBtn);
            login_btn = findViewById(R.id.loginBtn);


            // this needs to be implemented with the database
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAuth.signInWithEmailAndPassword(String.valueOf(email.getEditText().getText()), String.valueOf(password.getEditText().getText()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "signInWithEmail:success");
                                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                                        Log.w("TAG", String.valueOf(email.getEditText().getText()));
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            });
        }
        callRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(intent);

             /*       try {
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
                    }*/
            }
        });
    }

}



/*
    public void loginUser (View view){
        //validate login screen
        if (!validateUserName() || !validatePassword()) {
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
                    email.setError("No such User exist");
                    email.requestFocus();
                }
            }
*/
/*

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}*/
