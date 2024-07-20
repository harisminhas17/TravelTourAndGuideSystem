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
import com.example.traveltourandguidesystem.Activities.TransportationDetailActivity;
import com.example.traveltourandguidesystem.Helper.Constant;
import com.example.traveltourandguidesystem.Models.TransporationModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class TransportationAdapter extends RecyclerView.Adapter<TransportationAdapter.TransportationViewHolder> {

    ArrayList<TransporationModel> list = new ArrayList<>();

    Context context;
    boolean isHori;

    public TransportationAdapter(ArrayList<TransporationModel> list, Context context, boolean isHori) {
        this.list = list;
        this.context = context;
        this.isHori = isHori;
    }

    @Override
    public TransportationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new TransportationViewHolder(inflater.inflate(R.layout.transportation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final TransportationViewHolder viewHolder, int position) {

        TransporationModel transporationModel = list.get(position);

        viewHolder.tv_v_name.setText(transporationModel.getVehicle_name());

        viewHolder.tv_v_color.setText(transporationModel.getVehicle_color());

        viewHolder.tv_v_type.setText(transporationModel.getVehicle_type());

        viewHolder.tv_v_plate.setText(transporationModel.getVehicle_plate_number());

        viewHolder.tv_v_model.setText(transporationModel.getVehicle_model());

        //ToDo link other objects here

        if (transporationModel.getVehicle_image() != null && !transporationModel.getVehicle_image().isEmpty()) {
            Glide.with(context)
                    .load(Constant.IMAGE_URL + transporationModel.getVehicle_image())
                    .into(viewHolder.imageView);
        } else {
            // Handle the case where the checks do not pass
            // For example, you might want to load a default image or log an error
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TransportationDetailActivity.class).putExtra("transportationmodel", transporationModel));
            }
        });
    }

    public class TransportationViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView tv_v_name;
        TextView tv_v_color;
        TextView tv_v_type;
        TextView tv_v_plate;
        TextView tv_v_model;


        TransportationViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = itemView.findViewById(R.id.imageview);
            tv_v_name = itemView.findViewById(R.id.tv_v_name);
            tv_v_color = itemView.findViewById(R.id.tv_v_color);
            tv_v_type = itemView.findViewById(R.id.tv_v_type);
            tv_v_plate = itemView.findViewById(R.id.tv_v_plate);
            tv_v_model = itemView.findViewById(R.id.tv_v_model);

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
