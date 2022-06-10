package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dreamsalonfinderapp.databinding.ActivityUsersBinding;
import com.example.dreamsalonfinderapp.databinding.ActivityFavoritesMainBinding;

public class UsersActivity extends AppCompatActivity {

    ActivityUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){

            String services = intent.getStringExtra("services");
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            String address = intent.getStringExtra("address");
            int imageid = intent.getIntExtra("imageid",R.drawable.blower_services);


            binding.servicesProfile.setText(services);
            binding.nameProfile.setText(name);
            binding.phoneProfile.setText(phone);
            binding.addressProfile.setText(address);
            binding.profileImage.setImageResource(imageid);


        }

    }
}