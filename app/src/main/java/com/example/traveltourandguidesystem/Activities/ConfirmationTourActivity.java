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

import com.example.traveltourandguidesystem.R;

public class ConfirmationTourActivity extends AppCompatActivity {

    private Context context;
    ImageView tv_confirm_back_btn;
    TextView confirmation;

    Button tv_explore_more_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_tour);
        tv_confirm_back_btn = findViewById(R.id.tv_confirm_back_btn);
        confirmation = findViewById(R.id.confirmation);
        tv_explore_more_btn = findViewById(R.id.tv_explore_more_btn);

        context = ConfirmationTourActivity.this;

        tv_confirm_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PaymentMethodActivity.class));
            }
        });
        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, UserReviewsActivity.class));
            }
        });
        tv_explore_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });
    }
}