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
import com.example.traveltourandguidesystem.Models.HotelsModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

public class HotelDetailActivity extends AppCompatActivity {

    HotelsModel hotelsModel;
    private Context context;
    ImageView back_5, notify_btn;
    CardSliderViewPager cardSliderViewPager;
    TextView tv_h_name, tv_h_add, tv_dir, tv_h_d_rating, tv_h_about, tv_h_wifi;
    TextView tv_h_single_room, tv_h_double_room, tv_h_single_bed, tv_h_double_bed;
    TextView tv_h_chi_food, tv_h_fast_food, tv_p_d_review;
    Button tv_book_hotel;

    static String tourGuiderPrice;
    static String vehiclePrice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        context = HotelDetailActivity.this;
        getSupportActionBar().hide();


        tv_p_d_review = findViewById(R.id.tv_p_d_review);
        cardSliderViewPager = findViewById(R.id.slider);
        back_5 = findViewById(R.id.back_5);
        notify_btn = findViewById(R.id.notify_btn);
        tv_h_name = findViewById(R.id.tv_h_name);
        tv_h_add = findViewById(R.id.tv_h_add);
        tv_dir = findViewById(R.id.tv_dir);
        tv_h_d_rating = findViewById(R.id.tv_h_d_rating);
        tv_book_hotel = findViewById(R.id.tv_book_hotel);
        tv_h_about = findViewById(R.id.tv_h_about);

        tv_h_wifi = findViewById(R.id.tv_h_wifi);

        tv_h_single_room = findViewById(R.id.tv_h_single_room);
        tv_h_double_room = findViewById(R.id.tv_h_double_room);

        tv_h_single_bed = findViewById(R.id.tv_h_single_bed);
        tv_h_double_bed = findViewById(R.id.tv_h_double_bed);

        tv_h_chi_food = findViewById(R.id.tv_h_chi_food);
        tv_h_fast_food = findViewById(R.id.tv_h_fast_food);

        Intent intent = getIntent();
        tourGuiderPrice = intent.getStringExtra("tourGuiderPrice");
        vehiclePrice = intent.getStringExtra("vehiclePrice");


        hotelsModel = getIntent().getParcelableExtra("hotelmodel");

        tv_p_d_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ReviewActivity.class).putExtra("hotelsModel", hotelsModel));
            }
        });


        back_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        tv_book_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hotelName = hotelsModel.getName();
                tv_h_name.setText(hotelName);

                // Get tour guider price from SharedPreferences
                SharedPreferences tourGuidePrefs = getSharedPreferences("TourGuidePrefs", Context.MODE_PRIVATE);
                String tourGuiderPrice = tourGuidePrefs.getString("tourGuiderPrice", "0");

                // Get vehicle price from SharedPreferences
                SharedPreferences transportPrefs = getSharedPreferences("TransportPrefs", Context.MODE_PRIVATE);
                String vehiclePrice = transportPrefs.getString("vehiclePrice", "0");

                // Determine hotel price based on hotel name
                String hotelPrice = "0";
                switch (hotelName) {
                    case "Capital House Islamabad":
                        hotelPrice = "15,000";
                        break;
                    case "Hillside Residence by Paramount Hospitality":
                        hotelPrice = "20,000";
                        break;
                    case "Rhodium Boutique Hotel Islamabad":
                        hotelPrice = "30,000";
                        break;
                    case "Ramada by Wyndham Islamabad":
                        hotelPrice = "40,000";
                        break;
                    case "Grand Regency Hotel":
                        hotelPrice = "25,000";
                        break;
                    case "IFQ Hotel & Resort Islamabad":
                        hotelPrice = "60,000";
                        break;
                    default:
                        hotelPrice = "0";
                        break;
                }

                // Create an intent to start BookingTourActivity
                Intent intent = new Intent(context, BookingTourActivity.class);
                intent.putExtra("city_id", hotelsModel.getCity_id());
                intent.putExtra("tourGuiderPrice", tourGuiderPrice);
                intent.putExtra("vehiclePrice", vehiclePrice);
                intent.putExtra("hotelPrice", hotelPrice);
                startActivity(intent);
            }
        });


        tv_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelperMethods.openGoogleMap(context, hotelsModel.getLatitude(), hotelsModel.getLongitude());
            }
        });

        //getting data from previous activity


        if (hotelsModel.getImages() != null) {
            ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(hotelsModel.getImages(), HotelDetailActivity.this);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);
            tv_h_name.setText(hotelsModel.getName());
            tv_h_add.setText(hotelsModel.getAddress());
            tv_h_about.setText(hotelsModel.getAbout());
            tv_h_wifi.setText(hotelsModel.getWifi());
            tv_h_chi_food.setText(hotelsModel.getChinese_food());
            tv_h_fast_food.setText(hotelsModel.getFast_food());
            tv_h_single_room.setText(hotelsModel.getSingle_room());
            tv_h_double_room.setText(hotelsModel.getDouble_room());
        }
    }

}