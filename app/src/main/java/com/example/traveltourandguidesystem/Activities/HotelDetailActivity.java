package com.example.traveltourandguidesystem.Activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;
import com.github.islamkhsh.CardSliderViewPager;

public class HotelDetailActivity extends AppCompatActivity {

    private Context context;
    ImageView back_4, notify_btn;
    CardSliderViewPager cardSliderViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        context = HotelDetailActivity.this;
        getSupportActionBar().hide();

        cardSliderViewPager = findViewById(R.id.slider);
        back_4 = findViewById(R.id.back_4);
        notify_btn = findViewById(R.id.notify_btn);

    }
}