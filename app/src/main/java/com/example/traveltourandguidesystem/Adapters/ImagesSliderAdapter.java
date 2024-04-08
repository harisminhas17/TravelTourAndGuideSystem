package com.example.traveltourandguidesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveltourandguidesystem.Helper.Constant;
import com.example.traveltourandguidesystem.Models.ImageModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderAdapter;

import java.util.ArrayList;

public class ImagesSliderAdapter extends CardSliderAdapter<ImagesSliderAdapter.MovieViewHolder> {
    
    private ArrayList<ImageModel> imagesList;
    Context context;
    
    public ImagesSliderAdapter(ArrayList<ImageModel> imagesList, Context context)
    {
        this.imagesList = imagesList;
        this.context=context;
    }
    
    @Override
    public int getItemCount(){
    	return imagesList.size();
    }
    
     @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
	return new MovieViewHolder(view);
    }

    @Override
    public void bindVH(@NonNull MovieViewHolder movieViewHolder, int i) {
        ImageModel imageModel= imagesList.get(i);
        Glide.with(context)
                .load(Constant.IMAGE_URL + imageModel.getImage_name())
                .into(movieViewHolder.imageView);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
    	ImageView imageView;
	public MovieViewHolder(View view){
	      super(view);
          imageView=view.findViewById(R.id.imageviewslider);

	}
    }
}