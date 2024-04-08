package com.example.traveltourandguidesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ImageView; 

import androidx.annotation.NonNull; 
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.R;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

	// Array of images 
	// Adding images from drawable folder 
	private int[] images = {R.drawable.ic_globe, R.drawable.ic_tour, R.drawable.ic_man_hike};
	private Context ctx; 

	// Constructor of our ViewPager2Adapter class 
	public ViewPager2Adapter(Context ctx) {
		this.ctx = ctx; 
	} 

	// This method returns our layout 
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { 
		View view = LayoutInflater.from(ctx).inflate(R.layout.images_holder, parent, false); 
		return new ViewHolder(view); 
	} 

	// This method binds the screen with the view 
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) { 
		// This will set the images in imageview 
		holder.images.setImageResource(images[position]); 
	} 

	// This Method returns the size of the Array 
	@Override
	public int getItemCount() { 
		return images.length; 
	} 

	// The ViewHolder class holds the view 
	public static class ViewHolder extends RecyclerView.ViewHolder { 
		ImageView images; 

		public ViewHolder(@NonNull View itemView) { 
			super(itemView); 
			images = itemView.findViewById(R.id.images); 
		} 
	} 
}
