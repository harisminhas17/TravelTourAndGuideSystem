package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Models.ImageModel;
import com.example.traveltourandguidesystem.Models.TransporationModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

import java.util.ArrayList;

public class TransportationDetailActivity extends AppCompatActivity {

    CardSliderViewPager cardSliderViewPager;
    private Context context;
    TextView tv_t_name, tv_t_type, tv_t_color, tv_t_p_n, tv_t_model, tv_t_done;
    ImageView back_5, notify_btn;


    TransporationModel transporationModel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_detail);
        context = TransportationDetailActivity.this;
        getSupportActionBar().hide();
        transporationModel = getIntent().getParcelableExtra("transportationmodel");

        back_5 = findViewById(R.id.back_5);
        notify_btn = findViewById(R.id.notify_btn);
        tv_t_name = findViewById(R.id.tv_t_name);
        tv_t_type = findViewById(R.id.tv_t_type);
        tv_t_color = findViewById(R.id.tv_t_color);
        tv_t_p_n = findViewById(R.id.tv_t_p_n);
        tv_t_model = findViewById(R.id.tv_t_model);
        tv_t_done = findViewById(R.id.tv_t_done);


        cardSliderViewPager = findViewById(R.id.slider);

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

        tv_t_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleName = transporationModel.getVehicle_name();
                tv_t_name.setText(vehicleName);

                SharedPreferences tourGuidePrefs = getSharedPreferences("TourGuidePrefs", Context.MODE_PRIVATE);
                String tourGuiderPrice = tourGuidePrefs.getString("tourGuiderPrice", "default_price");

                // Get vehicle price
                SharedPreferences transportPrefs = getSharedPreferences("TransportPrefs", Context.MODE_PRIVATE);
                String vehiclePrice = transportPrefs.getString("vehiclePrice", "default_price");
                Intent intent = new Intent(context, HotelDetailActivity.class);
                intent.putExtra("city_id", transporationModel.getCity_id());
                intent.putExtra("tourGuiderPrice", tourGuiderPrice);
                intent.putExtra("vehiclePrice", vehiclePrice);
                startActivity(intent);
            }
        });

        //getting data from previous activity
        if (transporationModel != null) {
            if (transporationModel.getVehicle_image() != null) {
                ArrayList<ImageModel> images = new ArrayList<>();
                images.add(new ImageModel(transporationModel.getVehicle_image()));

                ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(images, TransportationDetailActivity.this);
                cardSliderViewPager.setAdapter(imagesSliderAdapter);

                tv_t_name.setText(transporationModel.getVehicle_name());
                tv_t_type.setText(transporationModel.getVehicle_type());
                tv_t_color.setText(transporationModel.getVehicle_color());
                tv_t_p_n.setText(transporationModel.getVehicle_plate_number());
                tv_t_model.setText(transporationModel.getVehicle_model());

            } else {
                // Handle case where vehicle_image is null
                // e.g., show a default image or a placeholder
            }
        } else {
            // Handle case where transporation Model is null
            Toast.makeText(this, "No transportation data available", Toast.LENGTH_SHORT).show();
            // Optionally, finish the activity or navigate back
            finish();
        }
    }

}