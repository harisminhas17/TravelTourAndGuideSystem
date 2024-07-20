package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
                startActivity(new Intent(context, BookingTourActivity.class).putExtra("city_id", transporationModel.getCity_id()));
            }
        });

        //getting data from previous activity

        transporationModel = getIntent().getParcelableExtra("transportationmodel");

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
        }
    }
}