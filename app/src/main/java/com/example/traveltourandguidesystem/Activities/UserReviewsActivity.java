package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.MenuFragment;
import com.example.traveltourandguidesystem.R;

public class UserReviewsActivity extends AppCompatActivity {
    ImageView tv_reviews_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reviews);
        getSupportActionBar().hide();
        tv_reviews_btn = findViewById(R.id.tv_reviews_btn);
        tv_reviews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserReviewsActivity.this, MenuFragment.class));
            }
        });
    }
}