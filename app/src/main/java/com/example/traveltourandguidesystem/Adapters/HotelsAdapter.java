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
import com.example.traveltourandguidesystem.Activities.HotelDetailActivity;
import com.example.traveltourandguidesystem.Helper.Constant;
import com.example.traveltourandguidesystem.Models.HotelsModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder> {

    ArrayList<HotelsModel> list = new ArrayList<>();

    Context context;
    boolean isHori;

    public HotelsAdapter(ArrayList<HotelsModel> list, Context context, boolean isHori) {
        this.list = list;
        this.context = context;
        this.isHori = isHori;
    }

    @Override
    public HotelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new HotelsViewHolder(inflater.inflate(R.layout.hotel_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final HotelsViewHolder viewHolder, int position) {
        HotelsModel hotelsModel = list.get(position);
        viewHolder.tv_name.setText(hotelsModel.getName());

        viewHolder.tv_address.setText(hotelsModel.getAddress());

//        viewHolder.tv_rating.setText(hotelsModel.getRating());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, HotelDetailActivity.class).putExtra("hotelmodels", hotelsModel));
            }
        });

        //ToDo link other objects here

        if (hotelsModel.getImages() != null && !hotelsModel.getImages().isEmpty() && hotelsModel.getImages().get(0).getImage_name() != null) {
            Glide.with(context)
                    .load(Constant.IMAGE_URL + hotelsModel.getImages().get(0).getImage_name())
                    .into(viewHolder.imageView);
        } else {
            // Handle the case where the checks do not pass
            // For example, you might want to load a default image or log an error
        }
    }

    public class HotelsViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView tv_name;
        TextView tv_address;
        TextView tv_rating;


        HotelsViewHolder(View itemView) {
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
