package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class EmergencyDetailActivity extends AppCompatActivity {

    ImageView back_btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_detail);
        getSupportActionBar().hide();
        back_btn3 = findViewById(R.id.back_btn3);
        back_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmergencyDetailActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}