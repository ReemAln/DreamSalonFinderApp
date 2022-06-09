package com.example.dreamsalonfinderapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity  {

    private Button registerUser, returnButton;
    private TextInputEditText editTextFullName, editTextEmail, editTextPhoneNumber, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;


    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef =  database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();


            // Button to save the user to database
            registerUser = (Button) findViewById(R.id.registerBtn);


            // Button to return to login page
            returnButton = (Button) findViewById(R.id.returnBtn);

            mAuth = FirebaseAuth.getInstance();

            // fields that the user enters information
            editTextFullName = findViewById(R.id.name);
            editTextPhoneNumber = findViewById(R.id.phone);
            editTextEmail = findViewById(R.id.email);
            editTextPassword = findViewById(R.id.password);
            editTextConfirmPassword = findViewById(R.id.confirmPassword);
            //   progressBar = (ProgressBar) findViewById(R.id.progressBar);


        }
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = editTextFullName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();


                if (fullName.isEmpty()) {
                    editTextFullName.setError("Full name is required.");
                    editTextFullName.requestFocus();

                }
                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required.");
                    editTextEmail.requestFocus();

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please provide a valid email.");
                    editTextEmail.requestFocus();

                }
                if (phoneNumber.isEmpty()) {
                    editTextPhoneNumber.setError("Phone number is required.");
                    editTextPhoneNumber.requestFocus();
                }

                if (password.isEmpty()) {
                    editTextPassword.setError("Password is required.");
                    editTextPassword.requestFocus();
                }

                if (password.length() < 6) {
                    editTextPassword.setError("Password should contain at least 6 characters.");
                    editTextPassword.requestFocus();

                }
                if (confirmPassword.isEmpty()) {
                    editTextPassword.setError("Please confirm your password.");
                    editTextPassword.requestFocus();

                }

                if (!confirmPassword.equals(password)) {
                    editTextPassword.setError("Your passwords must match, try again.");
                    editTextPassword.requestFocus();

                }

                createAccount(email, password);
            }

        });
    }
    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        User user = new User(editTextFullName, editTextEmail, editTextPhoneNumber, editTextPassword);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "Success!", Toast.LENGTH_SHORT).show();
                            userRef.setValue(user);
                            Intent intent = new Intent(RegisterUser.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterUser.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());

                        }

                    }
                });
    }


    private boolean validateForm() {
        boolean valid = true;

        String email = editTextEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Required.");
            valid = false;
        } else {
            editTextEmail.setError(null);
        }

        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Required.");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        return valid;
    }

}

    /*
                      //here i create a user object from the user java class i have created
                      User user = new User(fullName, password, email, phoneNumber);

                      //  progressBar.setVisibility(View.VISIBLE);

                      mAuth.createUserWithEmailAndPassword(email, password)
                              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                  @Override
                                  public void onComplete(@NonNull Task<AuthResult> task) {

                                      //here i create a user object from the user java class i have created
                                      User user = new User(fullName, password, email, phoneNumber);

                                      //if the user have been registered, we send the user info to database
                                      if (task.isSuccessful()) {

                                          //here i create a user object from the user java class i have created
                                          User user1 = new User(fullName, password, email, phoneNumber);

                                          //we call the firebase database object
                                          FirebaseDatabase.getInstance().getReference("Users")
                                                  .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                                  .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                      @Override
                                                      public void onComplete(@NonNull Task<Void> task) {
                                                          //if user have been registered successfully to the database
                                                          if (task.isSuccessful()) {
                                                              Toast.makeText(RegisterUser.this, "Registered! Welcome!", Toast.LENGTH_LONG).show();

                                                          } else {
                                                              Toast.makeText(RegisterUser.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                                          }
                                                      }
                                                  });
                                      }
                                  }
                              });
                  }
              });*/

