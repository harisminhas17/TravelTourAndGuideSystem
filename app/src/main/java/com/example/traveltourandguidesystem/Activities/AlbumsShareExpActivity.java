package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class AlbumsShareExpActivity extends AppCompatActivity {

    ImageView tv_share_exp_back, tv_share_exp_notify;
    Button tv_share_exp_cancel, tv_share_exp_upload;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_share_exp);
        getSupportActionBar().hide();

        tv_share_exp_back = findViewById(R.id.tv_share_exp_back);
        tv_share_exp_notify = findViewById(R.id.tv_share_exp_notify);
        tv_share_exp_cancel = findViewById(R.id.tv_share_exp_cancel);
        tv_share_exp_upload = findViewById(R.id.tv_share_exp_upload);

        tv_share_exp_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_share_exp_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsShareExpActivity.this, NotificationActivity.class));
            }
        });
        tv_share_exp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        tv_share_exp_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = tv_support_name.getText().toString();
//                String email = tv_support_email.getText().toString();
//                String message = tv_support_message.getText().toString();
//                if (name.length() < 2) {
//                    Toast.makeText(AlbumsShareExpActivity.this, "Place Name Must Be Valid", Toast.LENGTH_SHORT).show();
//                } else if (message.length() < 4) {
//                    Toast.makeText(AlbumsShareExpActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    addSupportMessage(name, email, message);
//                }
//            }
//        });


    }
}