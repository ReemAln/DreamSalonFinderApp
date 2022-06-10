package com.example.dreamsalonfinderapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.dreamsalonfinderapp.databinding.ActivityFavoritesMainBinding;
import com.example.dreamsalonfinderapp.databinding.ActivityFavoritesMainBinding;

import java.util.ArrayList;

public class FavoritesMain extends AppCompatActivity {

    ActivityFavoritesMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_favorites_main);


        //Hides action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

            int[] imageId = {R.drawable.blower_services, R.drawable.eyelashes_services, R.drawable.facial_services, R.drawable.makeup_services, R.drawable.men3_services,
                    R.drawable.manicure_services, R.drawable.shampoo_services, R.drawable.men1_services, R.drawable.men2_services};

            String[] services = {"Jose", "Eyelashes", "Facial", "Make up", "Men's Haircut", "Manicure", "Shampoo", "Haircut of the Month", "Family Haircut"};
            String[] name = {"Hair Blower", "Eyelashes", "Facial", "Make up", "Men's Haircut", "Manicure", "Shampoo", "Haircut of the Month", "Family Haircut"};
            String[] lastMessage = {"Great haircuts, fantastic services, love the atmosphere, nothing but a good afternoon.", "Easy appointments, welcoming staff, and talented employees! I was thrilled with my eyebrow wax, and bang trim. I will be back for more services!", "I have been getting facials here once a month since this salon opened. (BN) is wonderful! The whole experience is so relaxing…I have fallen 😴! Love coming here!",
                    "Absolutely cannot recommend this salon enough, every time I go I get consistent, quality service. Pricing is more than reasonable. I especially recommend (BN) and (BN)!", "No appointment necessary, they get you in right away, offer a wide variety of services. Friendly and reasonably priced.",
                    "Great service and employees. The hot stone massage is my favorite part of the spa pedicure.", "I had a signature facial with (BN) and loved it! Left with glowing skin and a pleasant experience! Highly recommend!", "Love this place! I always come here to get my nails and eyebrows done they do an awesome job and are very friendly. Im so picky about my eyebrows and i always leave satisfied! I recommend (BS) to my family and friends and we always go back!", "Loved it!! (BS) 1 did a great job on everything and we had great. Overall great choice and definitely a competitive price compared to other salons in the area. Super worth it"};
            String[] lastmsgTime = {"8:45 pm", "9:00 am", "7:34 pm", "6:32 am", "5:76 am",
                    "5:00 am", "7:34 pm", "2:32 am", "7:76 am"};
            String[] phoneNo = {"514 685-2458", "514 852-9635", "514 205-7894", "514 635-7841", "514 682-3654",
                    "514 698-8547", "438 698-2145", "514 685-2451", "438 698-9748"};
            String[] address = {"Montreal, QC", "Westmount, QC", "Lachine, QC", "Israel", "Saint-Laurent, QC", "Verdun, QC", "Le Plateau-Mont-Royal, QC", "Outremont, QC", "Saint-Léonard, QC"};

            ArrayList<UserFavorites> userArrayList = new ArrayList<>();

            for (int i = 0; i < imageId.length; i++) {
                UserFavorites userFavorites = new UserFavorites(name[i], lastMessage[i], lastmsgTime[i], phoneNo[i], address[i], imageId[i], services[i]);
                userArrayList.add(userFavorites);
            }

            FavAdapter listAdapter = new FavAdapter(FavoritesMain.this, userArrayList);


            binding.listview.setAdapter(listAdapter);
            binding.listview.setClickable(true);
            binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(getApplicationContext(), UsersActivity.class);
                    i.putExtra("services", services[position]);
                    i.putExtra("name", name[position]);
                    i.putExtra("phone", phoneNo[position]);
                    i.putExtra("address", address[position]);
                    i.putExtra("imageid", imageId[position]);
                    startActivity(i);
                }
            });
        }
    }

