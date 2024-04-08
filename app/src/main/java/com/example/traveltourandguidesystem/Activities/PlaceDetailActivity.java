package com.example.traveltourandguidesystem.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

public class PlaceDetailActivity extends AppCompatActivity {
    PlacesModel placesModel;
    CardSliderViewPager cardSliderViewPager;

    TextView tv_detail_description;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        getSupportActionBar().hide();
        cardSliderViewPager=findViewById(R.id.slider);
        tv_detail_description=findViewById(R.id.tv_detail_description);
        placesModel=getIntent().getParcelableExtra("placemodel");
        if (placesModel.getImages()!=null){
            ImagesSliderAdapter imagesSliderAdapter=new ImagesSliderAdapter(placesModel.getImages(),PlaceDetailActivity.this);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);
            tv_detail_description.setText(placesModel.getDescription());
        }

    }
}