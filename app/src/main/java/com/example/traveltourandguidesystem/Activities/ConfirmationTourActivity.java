package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class ConfirmationTourActivity extends AppCompatActivity {

    private Context context;
    TextView confirmation;

    Button tv_explore_more_btn, tv_user_review_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_confirmation_tour);
        confirmation = findViewById(R.id.confirmation);
        tv_explore_more_btn = findViewById(R.id.tv_explore_more_btn);
        tv_user_review_btn = findViewById(R.id.tv_user_review_btn);

        context = ConfirmationTourActivity.this;

        confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AppRatingReviewActivity.class));
            }
        });
        tv_explore_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });
        tv_user_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AppRatingReviewActivity.class));
            }
        });
    }
}