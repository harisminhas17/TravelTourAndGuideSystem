package com.example.traveltourandguidesystem.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class ReviewActivity extends AppCompatActivity {

    private Context context;
    ImageView tv_reviews_btn;
    Button tv_rating_submit;
    RatingBar ratingBar_item;

    EditText tv_option, tv_option_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().hide();
        context = ReviewActivity.this;

        tv_rating_submit = findViewById(R.id.tv_rating_submit);
        tv_reviews_btn = findViewById(R.id.tv_reviews_btn);
        ratingBar_item = findViewById(R.id.ratingBar_item);
        tv_option = findViewById(R.id.tv_option);
        tv_option_item = findViewById(R.id.tv_option_item);

        tv_reviews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}