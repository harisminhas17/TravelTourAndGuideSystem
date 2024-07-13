package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class TransportationDetailActivity extends AppCompatActivity {

    private Context context;
    TextView tv_d_t_name;
    ImageView back_4, notify_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_detail);
        context = TransportationDetailActivity.this;
        getSupportActionBar().hide();
        back_4 = findViewById(R.id.back_4);
        notify_btn = findViewById(R.id.notify_btn);
        tv_d_t_name = findViewById(R.id.tv_d_t_name);
    }
}