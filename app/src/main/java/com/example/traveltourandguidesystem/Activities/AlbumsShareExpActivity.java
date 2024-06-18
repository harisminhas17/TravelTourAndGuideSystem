package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsShareExpActivity extends AppCompatActivity {

    ImageView tv_share_exp_back, tv_share_exp_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_share_exp);
        getSupportActionBar().hide();
        tv_share_exp_back = findViewById(R.id.tv_share_exp_back);
        tv_share_exp_notify = findViewById(R.id.tv_share_exp_notify);
        tv_share_exp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsShareExpActivity.this, AlbumsFragment.class));
            }
        });
        tv_share_exp_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsShareExpActivity.this, NotificationActivity.class));
            }
        });
    }
}