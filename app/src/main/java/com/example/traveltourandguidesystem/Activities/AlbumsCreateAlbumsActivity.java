package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsCreateAlbumsActivity extends AppCompatActivity {

    ImageView tv_create_album_back, tv_create_album_notify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_create_albums);
        getSupportActionBar().hide();
        tv_create_album_back = findViewById(R.id.tv_create_album_back);
        tv_create_album_notify = findViewById(R.id.tv_create_album_notify);
        tv_create_album_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsCreateAlbumsActivity.this, AlbumsFragment.class));
            }
        });
        tv_create_album_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsCreateAlbumsActivity.this, NotificationActivity.class));
            }
        });
    }
}