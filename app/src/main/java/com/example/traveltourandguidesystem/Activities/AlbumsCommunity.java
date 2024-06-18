package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsCommunity extends AppCompatActivity {
    ImageView tv_c_back, tv_c_notify;
    TextView tv_c_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_community);
        getSupportActionBar().hide();
        tv_c_back = findViewById(R.id.tv_c_back);
        tv_c_notify = findViewById(R.id.tv_c_notify);
        tv_c_start = findViewById(R.id.tv_c_start);
        tv_c_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsCommunity.this, AlbumsFragment.class));
            }
        });
        tv_c_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsCommunity.this, NotificationActivity.class));
            }
        });
        tv_c_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsCommunity.this, AlbumsTravelerFeedCommunityActivity.class));
            }
        });
    }
}