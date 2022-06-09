package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


    public class AddServices extends AppCompatActivity implements ServicesListener {

        RecyclerView recyclerView;
        ArrayList<Services> serviceArrayList = new ArrayList<>();

        private Button buttonToAddServices;

        // Uri for emulator, can comment out the geo part for physical download
        Uri searchUrl = Uri.parse("geo:45.5017,-73.5673?q=");
        String searchParams = "";
        String key = "key";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_services);

            buttonToAddServices = findViewById(R.id.buttonToAddServices);

            //Hides action bar
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();


                searchParams = "";
                recyclerView = findViewById(R.id.servicesRecyclerView);


                serviceArrayList.add(new Services("Men's ", "Haircut", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Haircut", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Family Friendly ", "Haircut", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Men's ", "Shampoo", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Shampoo", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Family Friendly ", "Shampoo", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Men's ", "Style", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Style", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Family Friendly ", "Style", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Men's ", "Shave", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Hair Color", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Blowout", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Eyelashes", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Facial", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Make Up", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Blowout", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Men's ", "Hair Treatment","15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Women's ", "Manicure", "15 to 25 min", R.drawable.femme, 5f, 0));



                ServicesAdapter adapter = new ServicesAdapter(serviceArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

                buttonToAddServices.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        searchParams = String.join(" ", ServicesAdapter.chosenServices);
                        Uri searchUri = Uri.parse("geo:0,0?q=" + (searchParams));
                        Intent searchIntent = new Intent(Intent.ACTION_VIEW, searchUri);
                        Toast.makeText(view.getContext(), searchParams, Toast.LENGTH_LONG).show();
                        startActivity(searchIntent);


                      ServicesAdapter.chosenServices.clear();

     /*                   //searchParams = ServicesAdapter.chosenServices.toString() ;
                        //Uri searchUri = Uri.parse("geo:0,0?q=" + (searchParams));
                        Intent intent = new Intent(getApplicationContext() , MapsActivity.class);
                     //   intent.getBundleExtra("key");
                       // Toast.makeText(view.getContext(), (CharSequence) searchUri, Toast.LENGTH_LONG).show();
                        startActivity(intent);

                        ServicesAdapter.chosenServices.clear();*/
                        }
                });
            }
        }


        @Override
        public void onServicesShowAction(Boolean isSelected) {
        }


    }