package com.example.dreamsalonfinderapp;

import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.TABLENAME;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_EMAIL;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_FULLNAME;
import static com.example.dreamsalonfinderapp.TableSchema.UserEntry.USERS_COLUMN_ID;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;



public class UserProfile extends AppCompatActivity {


    private TextView userName, userEmail, greetingName;
    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        greetingName = findViewById(R.id.profileGreetingName);
        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();

            databaseHelper = new DBHelper(this);


//            readData();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.profile);
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {

                        case R.id.addservices:
                            startActivity(new Intent(getApplicationContext(), AddServices.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.favoritesMain:
                            startActivity(new Intent(getApplicationContext(), UsersActivity.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.update:
                            startActivity(new Intent(getApplicationContext(), UpdateUserInfo.class));
                            overridePendingTransition(0, 0);
                            return true;

                        case R.id.profile:
                            return true;
                    }
                    return false;
                }
            });
        }
    }

    private void readData() {

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(TABLENAME, new String[]{"fullName", "email"}, " id = ? ", new String[]{USERS_COLUMN_ID}, null, null, null );
        String greeting = "", name = "", email = "";
        while(cursor.moveToNext()) {
           greeting = cursor.getString(0);
           name = cursor.getString(0);
            email = cursor.getString(1);
        }
            userName.setText(name);
            userEmail.setText(email);
            greetingName.setText(greeting);
            dbHelper.close();
    }
}