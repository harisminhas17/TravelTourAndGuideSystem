package com.example.traveltourandguidesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Models.ImageUriModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesUriViewHolder> {

    ArrayList<ImageUriModel> list = new ArrayList<>();

    Context context;

    public ImagesAdapter(ArrayList<ImageUriModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ImagesAdapter.ImagesUriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ImagesAdapter.ImagesUriViewHolder(inflater.inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImagesAdapter.ImagesUriViewHolder viewHolder, int position) {
        ImageUriModel imageUriModel = list.get(position);
        if (imageUriModel != null) {
            viewHolder.tv_upload_pic.setImageURI(imageUriModel.getUri());
        }
    }

    public class ImagesUriViewHolder extends RecyclerView.ViewHolder {
        View view;

        ImageView tv_upload_pic;

        ImagesUriViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv_upload_pic = itemView.findViewById(R.id.tv_upload_pic);

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

