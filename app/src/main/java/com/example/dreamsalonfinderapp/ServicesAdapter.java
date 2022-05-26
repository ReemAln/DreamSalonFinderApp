package com.example.dreamsalonfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private List<Services> services;
    private ServicesListener servicesListener;

    public ServicesAdapter(List<Services> services, ServicesListener servicesListener) {
        this.services = services;
        this.servicesListener = servicesListener;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServicesViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_services,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {
        holder.bindServices(services.get(position));
    }

    @Override
    public int getItemCount() {

        return services.size();
    }

    public List<Services> getServices() {
        List<Services> selectedServices = new ArrayList<>();
        for (Services service : services) {
            if (service.isSelected) {
                selectedServices.add(service);
            }
        }
        return selectedServices;
    }

    class ServicesViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutServices;
        View viewServices;
        View viewBackground;
        RoundedImageView imageServices;
        TextView textName, textServices, textTime;
        RatingBar ratingBar;
        ImageView imageServicesSelected;

        ServicesViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutServices = itemView.findViewById(R.id.layoutServices);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            viewServices= itemView.findViewById(R.id.viewServices);
            imageServices = itemView.findViewById(R.id.imageServices);
            textName = itemView.findViewById(R.id.textName);
            textServices = itemView.findViewById(R.id.textServices);
            textTime = itemView.findViewById(R.id.textTime);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageServicesSelected = itemView.findViewById(R.id.imageServicesSelected);

        }
        void bindServices (final Services services) {
            imageServices.setImageResource(services.image);
            textName.setText(services.name);
            textServices.setText(services.typeServices);
            textTime.setText(services.time);
            ratingBar.setRating(services.rating);
            if (services.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.services_background);
                imageServicesSelected.setVisibility(View.VISIBLE);
            }
            else {
                viewBackground.setBackgroundResource(R.drawable.services_selected_background);
                imageServicesSelected.setVisibility(View.GONE);
            }

            layoutServices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(services.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.services_background);
                        imageServicesSelected.setVisibility(View.GONE);
                        services.isSelected = false;
                        if (getServices().size() == 0) {
                            servicesListener.onServicesShowAction(false);
                        } else {
                            viewBackground.setBackgroundResource(R.drawable.services_background);
                            imageServicesSelected.setVisibility(View.VISIBLE);
                            services.isSelected = true;
                            servicesListener.onServicesShowAction(true);
                        }
                    }
                }
            });
        }
    }
}


