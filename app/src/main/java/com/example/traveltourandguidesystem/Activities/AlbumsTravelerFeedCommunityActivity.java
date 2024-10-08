package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.R;

public class AlbumsTravelerFeedCommunityActivity extends AppCompatActivity {

    ImageView tv_traveler_c_back, tv_traveler_notify;
    RecyclerView recyclerView_top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_traveler_feed_community);
        getSupportActionBar().hide();
        tv_traveler_c_back = findViewById(R.id.tv_traveler_c_back);
        tv_traveler_notify = findViewById(R.id.tv_traveler_notify);
        recyclerView_top = findViewById(R.id.recyclerView_top);

        tv_traveler_c_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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