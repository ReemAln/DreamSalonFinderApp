package com.example.dreamsalonfinderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    ArrayList<Services> servicesList = new ArrayList<>();

    private String example = "";
    static String searchUrl = "";
    static ArrayList<String> chosenServices = new ArrayList<>();

    public ServicesAdapter(ArrayList<Services> data) {

        this.servicesList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(servicesList.get(position).name);
        holder.type.setText(servicesList.get(position).typeServices);
        holder.time.setText(servicesList.get(position).time);


        // These crash the app
        // holder.image.setText(servicesList.get(position).image);
        // holder.rating.setText(servicesList.get(position).rating);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.isSelected.getText().equals("0")) {
                    holder.isSelected.setText("1");
                    // compare the names and use switch case for the three types(men, women and children).
                    // find a way to ignore multiples?
                    // set the first part of the string then check the type and another switch(?) maybe
                    // to continue setting the search params

                    example = holder.name.getText().toString() + "" + holder.type.getText().toString() + " is added";
                    if (holder.name.getText().toString().equalsIgnoreCase("homme")) {
                        searchUrl += "mens";
                    } else if (holder.name.getText().toString().equalsIgnoreCase("femme")) {
                        searchUrl += "womens";
                    } else if (holder.name.getText().toString().equalsIgnoreCase("femme")) {
                        searchUrl += "kid+friendly";
                }




                    Toast.makeText(view.getContext(),example, Toast.LENGTH_SHORT).show() ;
                    chosenServices.add(searchUrl);
                }
                else if (holder.isSelected.getText().equals("1")) {
                    holder.isSelected.setText("0");
                    example = holder.name.getText().toString() + " " + holder.type.getText().toString() + " has been deleted";
                    //need to erase the particular item from the string with deleting the others so maybe a list of selected
                    //services, check flag isSelected if 1 then erase that entry in the list(?)
                    Toast.makeText(view.getContext(), searchUrl + "\n" + example, Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return servicesList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        TextView name, type, time, isSelected;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            name = itemView.findViewById(R.id.service_name);
            type = itemView.findViewById(R.id.service_style);
            time = itemView.findViewById(R.id.service_time);

            image =(ImageView) itemView.findViewById(R.id.service_image);
           // rating = itemView.findViewById(R.id.service_rating);

            isSelected = itemView.findViewById(R.id.isSelected);
        }

    }

}



