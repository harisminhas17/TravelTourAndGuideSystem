package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AlbumsWriteStoryActivity extends AppCompatActivity {

    ImageView tv_write_story_back, tv_write_story_notify, tv_write_story_upload;
    Button tv_image_select, tv_write_story_cancel;

    static RecyclerView recyclerView;

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

        recyclerView = findViewById(R.id.recyclerView_story);

        tv_write_story_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_write_story_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsWriteStoryActivity.this, NotificationActivity.class));
            }
        });
        tv_image_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AlbumsWriteStoryActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(100, 100)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(999);
            }
        });
        tv_write_story_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        tv_write_story_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = tv_support_name.getText().toString();
//                String email = tv_support_email.getText().toString();
//                String message = tv_support_message.getText().toString();
//                if (name.length() < 2) {
//                    Toast.makeText(AlbumsWriteStoryActivity.this, "Place Name Must Be Valid", Toast.LENGTH_SHORT).show();
//                } else if (message.length() < 4) {
//                    Toast.makeText(AlbumsWriteStoryActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    addSupportMessage(name, email, message);
//                }
//            }
//        });
    }
}