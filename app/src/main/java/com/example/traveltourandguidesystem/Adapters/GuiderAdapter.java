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
import com.example.traveltourandguidesystem.Activities.GuiderProfileActivity;
import com.example.traveltourandguidesystem.Helper.Constant;
import com.example.traveltourandguidesystem.Models.TourGuiderModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class GuiderAdapter extends RecyclerView.Adapter<GuiderAdapter.GuiderViewHolder> {

    ArrayList<TourGuiderModel> list = new ArrayList<>();

    Context context;
    boolean isHori;

    public GuiderAdapter(ArrayList<TourGuiderModel> list, Context context, boolean isHori) {
        this.list = list;
        this.context = context;
        this.isHori = isHori;
    }

    @Override
    public GuiderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new GuiderViewHolder(inflater.inflate(R.layout.hotel_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final GuiderViewHolder viewHolder, int position) {
        TourGuiderModel tourGuiderModel = list.get(position);
        viewHolder.tv_name.setText(tourGuiderModel.getName());

        viewHolder.tv_address.setText(tourGuiderModel.getAddress());

//        viewHolder.tv_rating.setText(hotelsModel.getRating());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GuiderProfileActivity.class).putExtra("tourguidermodel", tourGuiderModel));
            }
        });

        //ToDo link other objects here

        if (tourGuiderModel.getImage() != null && !tourGuiderModel.getImage().isEmpty()) {
            Glide.with(context)
                    .load(Constant.IMAGE_URL + tourGuiderModel.getImage())
                    .into(viewHolder.imageView);
        } else {
            // Handle the case where the checks do not pass
            // For example, you might want to load a default image or log an error
        }
    }

    public class GuiderViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView tv_name;
        TextView tv_address;
        TextView tv_rating;


        GuiderViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imageview);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_address = itemView.findViewById(R.id.tv_address_hotel);
            tv_rating = itemView.findViewById(R.id.tv_rating_hotel);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

} 
