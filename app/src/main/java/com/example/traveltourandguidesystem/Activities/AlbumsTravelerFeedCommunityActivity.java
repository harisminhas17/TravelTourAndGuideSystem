package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsTravelerFeedCommunityActivity extends AppCompatActivity {

    ImageView tv_traveler_c_back, tv_traveler_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_traveler_feed_community);
        getSupportActionBar().hide();
        tv_traveler_c_back = findViewById(R.id.tv_traveler_c_back);
        tv_traveler_notify = findViewById(R.id.tv_traveler_notify);
        tv_traveler_c_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsTravelerFeedCommunityActivity.this, AlbumsFragment.class));
            }
        });
        tv_traveler_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsTravelerFeedCommunityActivity.this, NotificationActivity.class));
            }
        });
    }
}