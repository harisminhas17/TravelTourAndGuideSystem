package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsWriteStoryActivity extends AppCompatActivity {

    ImageView tv_write_story_back, tv_write_story_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_write_story);
        getSupportActionBar().hide();
        tv_write_story_back = findViewById(R.id.tv_write_story_back);
        tv_write_story_notify = findViewById(R.id.tv_write_story_notify);
        tv_write_story_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsWriteStoryActivity.this, AlbumsFragment.class));
            }
        });
        tv_write_story_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsWriteStoryActivity.this, NotificationActivity.class));
            }
        });
    }
}