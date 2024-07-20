package com.example.traveltourandguidesystem.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Helper.HelperMethods;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

public class PlaceDetailActivity extends AppCompatActivity {

    private Context context;
    PlacesModel placesModel;
    CardSliderViewPager cardSliderViewPager;
    Button tv_book_tour;
    TextView tv_detail_description, tv_name, tv_about, tv_dir, tv_visited, tv_mark;
    ImageView notify_btn;
    ImageView back_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        getSupportActionBar().hide();
        context = PlaceDetailActivity.this;

        cardSliderViewPager = findViewById(R.id.slider);
        tv_detail_description = findViewById(R.id.tv_detail_description);
        tv_name = findViewById(R.id.tv_name);
        tv_about = findViewById(R.id.tv_about);
        notify_btn = findViewById(R.id.notify_btn);
        back_4 = findViewById(R.id.back_4);
        tv_book_tour = findViewById(R.id.tv_book_tour);
        tv_dir = findViewById(R.id.tv_dir);
        tv_visited = findViewById(R.id.tv_visited);
        tv_mark = findViewById(R.id.tv_mark);


        tv_book_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HotelsListActivity.class).putExtra("city_id", placesModel.getCity_id()));
            }
        });
        back_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        tv_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethods.openGoogleMap(context, placesModel.getLatitude(), placesModel.getLongitude());
            }
        });

        tv_visited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            }
        });

        tv_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            }
        });


        //getting data from previous activity

        placesModel = getIntent().getParcelableExtra("placemodel");

        if (placesModel.getImages() != null) {
            ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(placesModel.getImages(), context);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);
            tv_detail_description.setText(placesModel.getDescription());
            tv_name.setText(placesModel.getName());
            tv_about.setText(placesModel.getAddress());
        }

    }
}