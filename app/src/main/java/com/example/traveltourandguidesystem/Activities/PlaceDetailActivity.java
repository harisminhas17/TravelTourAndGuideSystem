package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

public class PlaceDetailActivity extends AppCompatActivity {
    PlacesModel placesModel;
    CardSliderViewPager cardSliderViewPager;

    TextView tv_detail_description, tv_name, tv_about;
    ImageView notify_btn;
    ImageView back_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        getSupportActionBar().hide();
        cardSliderViewPager = findViewById(R.id.slider);
        tv_detail_description = findViewById(R.id.tv_detail_description);
        tv_name = findViewById(R.id.tv_name);
        tv_about = findViewById(R.id.tv_about);
        notify_btn = findViewById(R.id.notify_btn);
        back_4 = findViewById(R.id.back_4);
        back_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceDetailActivity.this, MainActivity.class));
            }
        });
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceDetailActivity.this, NotificationActivity.class));
            }
        });

        //getting data from previous activity
        placesModel = getIntent().getParcelableExtra("placemodel");

        if (placesModel.getImages() != null) {
            ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(placesModel.getImages(), PlaceDetailActivity.this);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);
            tv_detail_description.setText(placesModel.getDescription());
            tv_name.setText(placesModel.getName());
            tv_about.setText(placesModel.getAddress());
        }

    }
}