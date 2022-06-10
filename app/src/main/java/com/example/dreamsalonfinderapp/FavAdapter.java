package com.example.dreamsalonfinderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class FavAdapter extends ArrayAdapter<UserFavorites> {


    public FavAdapter(Context context, ArrayList<UserFavorites> userArrayList) {
        super(context, R.layout.list_item, userArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        UserFavorites userFavorites = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }


        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName = convertView.findViewById(R.id.personName);
        TextView lastMsg = convertView.findViewById(R.id.lastMessage);
        TextView time = convertView.findViewById(R.id.msgtime);

        imageView.setImageResource(userFavorites.imageId);
        userName.setText(userFavorites.name);
        lastMsg.setText(userFavorites.lastMessage);
        time.setText(userFavorites.lastMsgTime);


        return convertView;
    }

}
