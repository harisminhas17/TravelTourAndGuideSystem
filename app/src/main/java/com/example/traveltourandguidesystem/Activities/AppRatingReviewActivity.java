package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class AppRatingReviewActivity extends AppCompatActivity {
    ImageView tv_reviews_btn;
    Button tv_rating_submit;
    View ratingBar_5;
    private Context context;

    EditText tv_review_message;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_rating_review);

        getSupportActionBar().hide();

        tv_reviews_btn = findViewById(R.id.tv_reviews_btn);
        tv_rating_submit = findViewById(R.id.tv_rating_submit);
        tv_review_message = findViewById(R.id.tv_review_message);
        ratingBar_5 = findViewById(R.id.ratingBar_5);


        context = AppRatingReviewActivity.this;

        tv_reviews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });


        tv_rating_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String name = tv_support_name.getText().toString();
//                String email = tv_support_email.getText().toString();
//                String message = tv_support_message.getText().toString();
//                if (name.length() < 2) {
//                    Toast.makeText(context.this, "Name Must Be Valid", Toast.LENGTH_SHORT).show();
//                } else if (message.length() < 4) {
//                    Toast.makeText(context.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    addSupportMessage(name, email, message);
//                }
//            }
            }
        });
    }
}