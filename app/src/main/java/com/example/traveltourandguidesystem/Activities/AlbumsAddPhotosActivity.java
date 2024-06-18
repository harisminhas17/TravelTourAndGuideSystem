package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsAddPhotosActivity extends AppCompatActivity {

    ImageView tv_add_photos_back_btn;
    ImageView tv_add_photos_notify_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_add_photos);
        getSupportActionBar().hide();
        tv_add_photos_back_btn = findViewById(R.id.tv_add_photos_back_btn);
        tv_add_photos_notify_btn = findViewById(R.id.tv_add_photos_notify_btn);
        tv_add_photos_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAddPhotosActivity.this, AlbumsFragment.class));
            }
        });
        tv_add_photos_notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAddPhotosActivity.this, NotificationActivity.class));
            }
        });
    }
}