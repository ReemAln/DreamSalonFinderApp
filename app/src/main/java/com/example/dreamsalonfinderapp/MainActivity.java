package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        EditText editName = findViewById(R.id.edit_name);
        EditText editPosition = findViewById(R.id.edit_position);

        Button btn = findViewById(R.id.buttonSubmit);

        DAOEMployee dao = new DAOEMployee();

        btn.setOnClickListener(view -> {
            Employee employee = new Employee(editName.getText().toString(), editPosition.getText().toString());

            dao.add(employee).addOnSuccessListener(suc -> {
                Toast.makeText(this, "This record is inserted", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_LONG).show();
            });
        });

        btn.setOnClickListener(view -> {
            // This is for update, need to add button as there is only one in the design now
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", editName.getText().toString());
            hashMap.put("position", editPosition.getText().toString());

            // key comes from the database
         /*   dao.update(key, hashMap).addOnSuccessListener(suc -> {
                Toast.makeText(this, "This record is updated", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_LONG).show();
            });
        });*/
        Button btn = findViewById(R.id.buttonSubmit);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AllServices.class);
            startActivity(intent);
        });


    }


}