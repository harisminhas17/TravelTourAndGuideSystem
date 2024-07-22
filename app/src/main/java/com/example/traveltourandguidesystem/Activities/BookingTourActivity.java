package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class BookingTourActivity extends AppCompatActivity {
    private Context context;

    ImageView tv_booking_back;

    Button tv_payment_submit;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_tour);

        context = BookingTourActivity.this;
        getSupportActionBar().hide();

        tv_booking_back = findViewById(R.id.tv_booking_back);
        tv_payment_submit = findViewById(R.id.tv_payment_submit);


        tv_booking_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_payment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PaymentMethodActivity.class));
            }
        });
    }
}