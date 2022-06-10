package com.example.dreamsalonfinderapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.example.dreamsalonfinderapp.helpers.InputValidation;
import com.example.dreamsalonfinderapp.DBHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {

    private Button callRegisterScreen, loginBtn;
    private ImageView image;
    private TextView logoText;
    private TextInputLayout emailLayout, passwordLayout;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

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

            callRegisterScreen = findViewById(R.id.registerUserBtn);
            loginBtn = findViewById(R.id.loginBtn);

            emailLayout = (TextInputLayout) findViewById(R.id.emailLogin);
            passwordLayout = (TextInputLayout) findViewById(R.id.passwordLogin);


            textInputEditTextEmail = (TextInputEditText) findViewById(R.id.inputEmail);
            textInputEditTextPassword = (TextInputEditText) findViewById(R.id.inputPassword);


            dbHelper = new DBHelper(this);
            inputValidation = new InputValidation(this);


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("Notification", " Notification", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        verifyFromSQLite();

                     // notification manager is here
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this, "Notification");
                    builder.setContentTitle("Logged In!");
                    builder.setContentText("Time to live the DReaM!!");
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Login.this);
                    managerCompat.notify(1, builder.build());
                }

            });

            callRegisterScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), RegisterUser.class);
                    startActivity(intent);

                }
            });
        }
    }
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, emailLayout, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, emailLayout, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, passwordLayout, getString(R.string.error_message_email))) {
            return;
        }
        if (dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            Intent accountsIntent = new Intent(getApplicationContext(), UserProfile.class);
            emptyInputEditText();
            startActivity(accountsIntent);
        } else {

            Toast.makeText(Login.this, getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
        }
    }
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    public TextInputLayout getEmailLayout() {
        return emailLayout;
    }

    public void setEmailLayout(TextInputLayout emailLayout) {
        this.emailLayout = emailLayout;
    }

    public TextInputLayout getPasswordLayout() {
        return passwordLayout;
    }

    public void setPasswordLayout(TextInputLayout passwordLayout) {
        this.passwordLayout = passwordLayout;
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
