package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView=findViewById(R.id.next_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this,OnboardingActivity.class));
                finish();
            }
        });
        if (new SharedPref().getEmailData(SplashActivity.this).length() >0 ){
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }

    }

}