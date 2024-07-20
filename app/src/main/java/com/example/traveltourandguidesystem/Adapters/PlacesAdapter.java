package com.example.traveltourandguidesystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveltourandguidesystem.Activities.PlaceDetailActivity;
import com.example.traveltourandguidesystem.Helper.Constant;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> {

    ArrayList<PlacesModel> list = new ArrayList<>();

    Context context;
    boolean isHori;

    public PlacesAdapter(ArrayList<PlacesModel> list, Context context,boolean isHori) {
        this.list = list;
        this.context = context;
        this.isHori = isHori;
    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (isHori){
            return new PlacesViewHolder(inflater.inflate(R.layout.places_item_horizontal, parent,false));
        }
        else {
            return new PlacesViewHolder(inflater.inflate(R.layout.places_item, parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final PlacesViewHolder viewHolder, int position) {
        PlacesModel placesModel = list.get(position);
        viewHolder.tv_name.setText(placesModel.getName());
        //ToDo link other objects here
        viewHolder.tv_address.setText(placesModel.getAddress());
        if (placesModel.getImages() != null && !placesModel.getImages().isEmpty() && placesModel.getImages().get(0).getImage_name() != null) {
            Glide.with(context)
                    .load(Constant.IMAGE_URL + placesModel.getImages().get(0).getImage_name())
                    .into(viewHolder.imageView);
        } else {
            // Handle the case where the checks do not pass
            // For example, you might want to load a default image or log an error
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PlaceDetailActivity.class).putExtra("placemodel",placesModel));
            }
        });

    }


    public class PlacesViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView tv_name;
        TextView tv_address;
        TextView tv_rating;

        PlacesViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imageview);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_rating = itemView.findViewById(R.id.tv_rating);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


} 
