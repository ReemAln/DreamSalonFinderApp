package com.example.dreamsalonfinderapp;

import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_EMAIL;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_FULLNAME;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_PASSWORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dreamsalonfinderapp.helpers.InputValidation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateUserInfo extends AppCompatActivity {


    private Button updateBtn, deleteBtn;

    // Layouts
    private TextInputLayout updateNameLayout;
    private TextInputLayout updateEmailLayout;
    private TextInputLayout updatePasswordLayout;
    private TextInputLayout updateConfirmPasswordLayout;

    // Text Inputs
    private TextInputEditText updateName;
    private TextInputEditText updateEmail;
    private TextInputEditText updatePassword;
    private TextInputEditText updateConfirmPassword;

    private InputValidation inputValidation;
    DBHelper dbHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

            // Buttons
            updateBtn = findViewById(R.id.updateButton);
            deleteBtn = findViewById(R.id.deleteButton);

            dbHelper = new DBHelper(this);
            inputValidation = new InputValidation(this);
            user = new User();


            updateNameLayout = findViewById(R.id.updateNameLayout);
            updateEmailLayout = findViewById(R.id.updateEmailLayout);
            updatePasswordLayout = findViewById(R.id.updatePasswordLayout);
            updateConfirmPasswordLayout = findViewById(R.id.updateConfirmPasswordLayout);


            updateName = findViewById(R.id.updateName);
            updateEmail = findViewById(R.id.updateEmail);
            updatePassword = findViewById(R.id.updatePassword);
            updateConfirmPassword = findViewById(R.id.updateConfirmPassword);

            readData();

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateUser();
                }
            });


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

                        case R.id.favoritesMain:
                            startActivity(new Intent(UpdateUserInfo.this, FavoritesMain.class));
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

    private void updateUser() {

        String name = updateName.getText().toString();
        String email = updateEmail.getText().toString();
        String password = updatePassword.getText().toString();
        String confirmPassword = updateConfirmPassword.getText().toString();

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        dbHelper.updateUserData(name, email, password, confirmPassword, database);
        Toast.makeText(UpdateUserInfo.this, "Updated your profile!", Toast.LENGTH_LONG).show();
    }

    private void readData() {

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = dbHelper.readData(database);
        String info1 ="", info2 ="", info3 ="", info4 ="";
        while(cursor.moveToNext()) {

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(USERS_COLUMN_FULLNAME));
            info1 = name;
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(USERS_COLUMN_EMAIL));
            info2 = email;
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD));
            info3 = password;
            @SuppressLint("Range") String confirmPassword = cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD));
            info4 = confirmPassword;
        }
        updateName.setText(info1);
        updateEmail.setText(info2);
        updatePassword.setText(info3);
        updatePassword.setText(info4);

        dbHelper.close();
    }
}