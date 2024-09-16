package com.example.dreamsalonfinderapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
    static ArrayList<String> chosenServices = new ArrayList<>();

    private String example = "";
    static String searchUrl = "";
    private String searchName, searchType;
    Context context;
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

                    // This is to hold toast string to announce something has changed
                    example = holder.name.getText().toString() + "" + holder.type.getText().toString() + " is added";


                    // Men's Services
                    if (holder.name.getText().toString().equalsIgnoreCase("men's ") && holder.type.getText().toString().equalsIgnoreCase("haircut")) {
                        searchUrl = "mens haircut";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                        holder.checkmark.setVisibility(View.VISIBLE);
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("men's ") && holder.type.getText().toString().equalsIgnoreCase("shampoo")) {
                        searchUrl = "mens shampoo";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                        holder.checkmark.setVisibility(View.VISIBLE);
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("men's ") && holder.type.getText().toString().equalsIgnoreCase("style")) {
                            searchUrl = "mens style";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                            holder.checkmark.setVisibility(View.VISIBLE);
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("men's ") && holder.type.getText().toString().equalsIgnoreCase("shave")) {
                            searchUrl = "mens shave";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                            holder.checkmark.setVisibility(View.VISIBLE);
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("men's ") && holder.type.getText().toString().equalsIgnoreCase("hair treatment")) {
                            searchUrl = "mens treatment";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                    }

                    // Women's Services
                     else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("haircut")) {
                        searchUrl = "womens haircut";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                     else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("shampoo")) {
                        searchUrl = "womens shampoo";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("style")) {
                        searchUrl = "womens style";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("blowout")) {
                        searchUrl = "womens blowout";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("hair color")) {
                        searchUrl = "womens hair color";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("manicure")) {
                        searchUrl = "manicure";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("facial")) {
                        searchUrl = "facial";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("make up")) {
                        searchUrl = "make up";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }
                    else if (holder.name.getText().toString().equalsIgnoreCase("women's ") && holder.type.getText().toString().equalsIgnoreCase("eyelashes")) {
                        searchUrl = "eyelashes";
                        chosenServices.add(searchUrl);
                        searchUrl = "";
                    }


                    // Family friendly Services

                     else if (holder.name.getText().toString().equalsIgnoreCase("family friendly ") && holder.type.getText().toString().equalsIgnoreCase("haircut")) {
                            searchUrl = "child haircut";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                        }
                        else if (holder.name.getText().toString().equalsIgnoreCase("family friendly ") && holder.type.getText().toString().equalsIgnoreCase("shampoo")) {
                            searchUrl = "child shampoo";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                        }
                        else if (holder.name.getText().toString().equalsIgnoreCase("family friendly  ") && holder.type.getText().toString().equalsIgnoreCase("style")) {
                            searchUrl = "child style";
                            chosenServices.add(searchUrl);
                            searchUrl = "";
                        }


                    holder.isSelected.setText("1");

                    chosenServices.add(searchUrl);
                    Toast.makeText(view.getContext(),  example, Toast.LENGTH_SHORT).show();

            }
                // This needs to be worked on a bit. It sees homme and erases it from the search url before
            // getting passed as the list. starts out, if the flag is 1, then check the name and type and erase
            // both from the servicesChosen list
            else if (holder.isSelected.getText().equals("1")) {

                    example = holder.name.getText().toString() + " " + holder.type.getText().toString() + " has been deleted";
                    searchName = holder.name.getText().toString() + " " + holder.type.getText().toString();

                    int index, temp = 0;
                    for (index = 0; index < chosenServices.size(); index++) {

                        // need to erase the particular item from the string without deleting the others so maybe a list of selected
                        // services, check flag isSelected if 1 then erase that entry in the list(?)

                        if (searchName.equalsIgnoreCase(chosenServices.get(index)))
                            temp = index;
                            holder.checkmark.setVisibility(View.GONE);
                            break;
                          // take remove out of the loop, store in temp variable and remove after loop has finished iterating
                    }
                            chosenServices.remove(temp);
                            Toast.makeText(view.getContext(), example, Toast.LENGTH_SHORT).show();
                            holder.isSelected.setText("0");
                    }

                    // trying to get the list to go over and use the activity but layout inflater is not working. Check with teacher today
                 /*   Bundle bundle = new Bundle();
                    bundle.putString("services", chosenServices.toString());
                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("key", chosenServices);*/
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
        ImageView checkmark;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            name = itemView.findViewById(R.id.service_name);
            type = itemView.findViewById(R.id.service_style);
            time = itemView.findViewById(R.id.service_time);

            checkmark =(ImageView) itemView.findViewById(R.id.service_selected);
           // rating = itemView.findViewById(R.id.service_rating);

            isSelected = itemView.findViewById(R.id.isSelected);
        }

    }

}



