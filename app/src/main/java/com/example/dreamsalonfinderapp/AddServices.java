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
        String searchText = "";
        String key = "key";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_services);

            buttonToAddServices = findViewById(R.id.buttonToAddServices);

            //Hides action bar
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();



                recyclerView = findViewById(R.id.servicesRecyclerView);


                serviceArrayList.add(new Services("Men's ", "Haircut", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Haircut", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Enfant", "Haircut", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Men's", "Shampoo", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Shampoo", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Enfants", "Shampoo", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Homme", "Style", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Style", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Enfants", "Style", "15 to 25 min", R.drawable.enfant, 5f, 0));
                serviceArrayList.add(new Services("Homme", "Shave", "15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Hair Color", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Blowout", "15 to 25 min", R.drawable.femme, 5f, 0));
                serviceArrayList.add(new Services("Homme", "Hair Treatment","15 to 25 min", R.drawable.homme, 5f, 0));
                serviceArrayList.add(new Services("Femme", "Manicure", "15 to 25 min", R.drawable.femme, 5f, 0));



                ServicesAdapter adapter = new ServicesAdapter(serviceArrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


                // As of 2020, so try this next
               /*     String searchParam = String.valueOf(ServicesAdapter.chosenServices);
                    String url = "https://www.google.com/maps/search/?api=1&query="+searchParam;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(url));
                    Toast.makeText(view.getContext(), searchUri.toString(), Toast.LENGTH_LONG).show();
                    startActivity(intent);   */


                buttonToAddServices.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      /*  Uri searchUri = Uri.parse("geo:0,0?=q=" + Uri.encode(String.valueOf(ServicesAdapter.chosenServices)));
                        Intent searchIntent = new Intent(Intent.ACTION_VIEW, searchUri);
                        Toast.makeText(view.getContext(), searchUri.toString(), Toast.LENGTH_LONG).show();
                        startActivity(searchIntent);
*/
                  /*      String searchParam = String.valueOf(ServicesAdapter.chosenServices);
                        String url = "https://www.google.com/maps/search/?api=1&query="+searchParam;
                        Intent intent = new Intent(AddServices.this , MapsActivity.class);
                        Toast.makeText(view.getContext(), url, Toast.LENGTH_LONG).show();
                        startActivity(intent);  */

                        Intent intent = new Intent();
                        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                            String query = intent.getStringExtra(SearchManager.QUERY);
                            doMySearch(query);
                        }

                    }
                });
            }
        }


        @Override
        public void onServicesShowAction(Boolean isSelected) {
        }
    }