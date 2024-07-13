package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsWriteStoryActivity extends AppCompatActivity {

    ImageView tv_write_story_back, tv_write_story_notify, tv_write_story_upload;
    Button tv_image_select, tv_write_story_cancel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_write_story);
        getSupportActionBar().hide();
        tv_write_story_back = findViewById(R.id.tv_write_story_back);
        tv_write_story_notify = findViewById(R.id.tv_write_story_notify);

        tv_image_select = findViewById(R.id.tv_image_select);
        tv_write_story_upload = findViewById(R.id.tv_write_story_upload);
        tv_write_story_cancel = findViewById(R.id.tv_write_story_cancel);

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