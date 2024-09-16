package com.example.dreamsalonfinderapp;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamsalonfinderapp.helpers.InputValidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterUser extends AppCompatActivity {

    private Button registerUser, returnButton;

    // Layouts
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    // Inputs
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;


    private User user;
    DBHelper dbHelper;
    private InputValidation inputValidation;



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

            dbHelper = new DBHelper(this);

            textInputLayoutName = (TextInputLayout) findViewById(R.id.nameRegister);
            textInputLayoutEmail = (TextInputLayout) findViewById(R.id.emailRegister);
            textInputLayoutPassword = (TextInputLayout) findViewById(R.id.passwordRegister);
            textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.confirmPasswordRegister);

            textInputEditTextName = (TextInputEditText) findViewById(R.id.inputNameRegister);
            textInputEditTextEmail = (TextInputEditText) findViewById(R.id.inputEmailRegister);
            textInputEditTextPassword = (TextInputEditText) findViewById(R.id.inputPasswordRegister);
            textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.inputConfirmPasswordRegister);

            inputValidation = new InputValidation(this);
            dbHelper = new DBHelper(this);
            user = new User();

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
                    postDataToSQL();
            }

        });
    }

    private void postDataToSQL() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }
        if (!dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            String name =  textInputEditTextName.getEditableText().toString();
            String email = textInputEditTextEmail.getEditableText().toString();
            String password =  textInputEditTextPassword.getEditableText().toString();

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            dbHelper.addUser(name, email, password, database);
            database.close();
            Toast.makeText(RegisterUser.this, getString(R.string.success_message), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterUser.this, Login.class);
            startActivity(intent);
            emptyInputEditText();
        } else {
            Toast.makeText(RegisterUser.this, getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}

