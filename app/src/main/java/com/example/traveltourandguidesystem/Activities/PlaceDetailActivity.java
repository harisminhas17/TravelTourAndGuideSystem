package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Helper.HelperMethods;
import com.example.traveltourandguidesystem.Helper.SharedPref;
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

    TextView tv_p_d_tour_guide, tv_p_d_transport, tv_p_d_review;


    @SuppressLint("MissingInflatedId")
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

        tv_p_d_transport = findViewById(R.id.tv_p_d_transport);
        tv_p_d_tour_guide = findViewById(R.id.tv_p_d_tour_guide);
        tv_p_d_review = findViewById(R.id.tv_p_d_review);

        placesModel = getIntent().getParcelableExtra("placesModel");
        tv_p_d_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceDetailActivity.this, ReviewActivity.class).putExtra("placesModel", placesModel));
            }
        });

        tv_book_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get tour guider price
                SharedPreferences tourGuidePrefs = getSharedPreferences("TourGuidePrefs", Context.MODE_PRIVATE);
                String tourGuiderPrice = tourGuidePrefs.getString("tourGuiderPrice", "default_price");

                // Get vehicle price
                SharedPreferences transportPrefs = getSharedPreferences("TransportPrefs", Context.MODE_PRIVATE);
                String vehiclePrice = transportPrefs.getString("vehiclePrice", "default_price");

                // Use the retrieved prices as needed
                // For example, you can pass them to the next activity using Intent extras

                Intent intent = new Intent(context, HotelsListActivity.class);
                intent.putExtra("city_id", placesModel.getCity_id());
                intent.putExtra("tourGuiderPrice", tourGuiderPrice);
                intent.putExtra("vehiclePrice", vehiclePrice);
                startActivity(intent);
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

        tv_p_d_tour_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TourGuideListActivity.class).putExtra("city_id", placesModel.getCity_id()));
            }
        });

        tv_p_d_transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TransportationListActivity.class).putExtra("city_id", placesModel.getCity_id()));
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


        if (placesModel == null) {
            placesModel = new SharedPref().getPlaceDetails(context);
        } else {
            new SharedPref().savePlaceDetail(context, placesModel);
        }
        if (placesModel.getImages() != null) {
            ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(placesModel.getImages(), context);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);
            tv_detail_description.setText(placesModel.getDescription());
            tv_name.setText(placesModel.getName());
            tv_about.setText(placesModel.getAddress());
        }

    }
}