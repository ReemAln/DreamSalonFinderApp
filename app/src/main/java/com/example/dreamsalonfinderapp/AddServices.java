package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddServices extends AppCompatActivity implements ServicesListener{
    private Button buttonToAddServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);


        RecyclerView servicesRecyclerView = findViewById(R.id.servicesRecyclerView);
        buttonToAddServices = findViewById(R.id.buttonToAddServices);
        List<Services> services = new ArrayList<>();

        Services homme = new Services();
        homme.image = R.drawable.homme;
        homme.name = "Men's Haircut";
        homme.rating = 5f;
        homme.typeServices = "Homme";
        homme.time = "30min to 60min";
        services.add(homme);

        Services femme = new Services();
        femme.image = R.drawable.femme;
        femme.name = "Women's Haircut";
        femme.rating = 5f;
        femme.typeServices = "Femme";
        femme.time = "40min to 60min";
        services.add(femme);

        Services enfant = new Services();
        enfant.image = R.drawable.enfant;
        enfant.name = "Super Special Haircut";
        enfant.rating = 5f;
        enfant.typeServices = "Enfant";
        enfant.time = "30min to 60min";
        services.add(enfant);

        Services homme1 = new Services();
        homme1.image = R.drawable.homme;
        homme1.name = "Men's Shampoo";
        homme1.rating = 5f;
        homme1.typeServices = "Homme";
        homme1.time = "30min to 60min";
        services.add(homme1);

        Services femme1 = new Services();
        femme1.image = R.drawable.femme;
        femme1.name = "Shampoo";
        femme1.rating = 5f;
        femme1.typeServices = "Femme";
        femme1.time = "30min to 40min";
        services.add(femme1);

        Services enfant1 = new Services();
        enfant1.image = R.drawable.enfant;
        enfant1.name = "Super Special Shampoo";
        enfant1.rating = 5f;
        enfant1.typeServices = "Enfant";
        enfant1.time = "25min to 40min";
        services.add(enfant1);

        Services homme2 = new Services();
        homme2.image = R.drawable.homme;
        homme2.name = "Shaving";
        homme2.rating = 5f;
        homme2.typeServices = "Homme";
        homme2.time = "30min to 50min";
        services.add(homme2);

        Services femme2 = new Services();
        femme2.image = R.drawable.femme;
        femme2.name = "Coloring";
        femme2.rating = 5f;
        femme2.typeServices = "Femme";
        femme2.time = "60min to 120min";
        services.add(femme2);

        Services enfant2 = new Services();
        enfant2.image = R.drawable.enfant;
        enfant2.name = "Kid's Haircut";
        enfant2.rating = 5f;
        enfant2.typeServices = "Enfant";
        enfant2.time = "30min to 60min";
        services.add(enfant2);

        Services homme3 = new Services();
        homme3.image = R.drawable.homme;
        homme3.name = "Hair Treatment";
        homme3.rating = 5f;
        homme3.typeServices = "Homme";
        homme3.time = "45min to 60min";
        services.add(homme3);

        Services femme3 = new Services();
        femme3.image = R.drawable.femme;
        femme3.name = "Pedicure";
        femme3.rating = 5f;
        femme3.typeServices = "Femme";
        femme3.time = "60min to 80min";
        services.add(femme3);

        Services enfant3 = new Services();
        enfant3.image = R.drawable.enfant;
        enfant3.name = "Blow dry";
        enfant3.rating = 5f;
        enfant3.typeServices = "Enfant";
        enfant3.time = "20min to 30min";
        services.add(enfant3);

        final ServicesAdapter servicesAdapter = new ServicesAdapter(services, this);
        servicesRecyclerView.setAdapter(servicesAdapter);

        buttonToAddServices.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //following is the list of selected items in recycler view
                List<Services> selectedServices = servicesAdapter.getServices();

                StringBuilder servicesName = new StringBuilder();
                for (int i = 0; i < selectedServices.size(); i++) {
                    if (i == 0) {
                        servicesName.append(selectedServices.get(i).name);

                    } else {
                        servicesName.append("\n").append(selectedServices.get(i).name);
                    }
                }
                Toast.makeText(AddServices.this,servicesName.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onServicesShowAction(Boolean isSelected) {
        if(isSelected){
            buttonToAddServices.setVisibility(View.VISIBLE);
        } else {
            buttonToAddServices.setVisibility(View.GONE);
        }
    }
}
