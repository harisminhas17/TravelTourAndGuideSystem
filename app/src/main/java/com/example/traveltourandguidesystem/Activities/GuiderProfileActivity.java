package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Adapters.ImagesSliderAdapter;
import com.example.traveltourandguidesystem.Models.ImageModel;
import com.example.traveltourandguidesystem.Models.TourGuiderModel;
import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

import java.util.ArrayList;

public class GuiderProfileActivity extends AppCompatActivity {

    TourGuiderModel tourGuiderModel;
    private Context context;
    CardSliderViewPager cardSliderViewPager;
    ImageView back_5, notify_btn;
    TextView tv_h_name, tv_h_d_rating, tv_g_exp, tv_g_ph, tv_g_add, tv_g_ab, tv_g_lan, tv_g_price;
    Button tv_g_done;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider_profile);
        getSupportActionBar().hide();
        context = GuiderProfileActivity.this;

        back_5 = findViewById(R.id.back_5);
        notify_btn = findViewById(R.id.notify_btn);
        tv_h_name = findViewById(R.id.tv_h_name);
        tv_h_d_rating = findViewById(R.id.tv_h_d_rating);
        tv_g_ph = findViewById(R.id.tv_g_ph);
        tv_g_exp = findViewById(R.id.tv_g_exp);
        tv_g_add = findViewById(R.id.tv_g_add);
        tv_g_ab = findViewById(R.id.tv_g_ab);
        tv_g_lan = findViewById(R.id.tv_g_lan);
        tv_g_price = findViewById(R.id.tv_g_price);
        tv_g_done = findViewById(R.id.tv_g_done);

        cardSliderViewPager = findViewById(R.id.slider);
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        back_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_g_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TransportationListActivity.class).putExtra("city_id", tourGuiderModel.getCity_id()));
            }
        });

        //getting data from previous activity

        tourGuiderModel = getIntent().getParcelableExtra("tourguidermodel");

        if (tourGuiderModel.getImage() != null) {
            ArrayList<ImageModel> images = new ArrayList<>();
            images.add(new ImageModel(tourGuiderModel.getImage()));
            ImagesSliderAdapter imagesSliderAdapter = new ImagesSliderAdapter(images, GuiderProfileActivity.this);
            cardSliderViewPager.setAdapter(imagesSliderAdapter);

            tv_h_name.setText(tourGuiderModel.getName());
            tv_g_add.setText(tourGuiderModel.getAddress());
            tv_g_exp.setText(tourGuiderModel.getExperience());
            tv_g_ph.setText(tourGuiderModel.getPhone_number());
            tv_g_ab.setText(tourGuiderModel.getAbout());
            tv_g_lan.setText(tourGuiderModel.getLanguages());
            tv_g_price.setText(tourGuiderModel.getPrice());
        }
    }
}