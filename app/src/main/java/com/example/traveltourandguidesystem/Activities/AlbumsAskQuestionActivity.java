package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class AlbumsAskQuestionActivity extends AppCompatActivity {

    ImageView tv_ask_q_back, tv_ask_q_notify, tv_ask_q_upload;
    Button tv_ask_q_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_ask_question);
        getSupportActionBar().hide();

        tv_ask_q_back = findViewById(R.id.tv_ask_q_back);
        tv_ask_q_notify = findViewById(R.id.tv_ask_q_notify);
        tv_ask_q_cancel = findViewById(R.id.tv_ask_q_cancel);
        tv_ask_q_upload = findViewById(R.id.tv_ask_q_upload);

        tv_ask_q_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_ask_q_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAskQuestionActivity.this, NotificationActivity.class));
            }
        });
        tv_ask_q_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        tv_ask_q_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = tv_support_name.getText().toString();
//                String email = tv_support_email.getText().toString();
//                String message = tv_support_message.getText().toString();
//                if (name.length() < 2) {
//                    Toast.makeText(AlbumsAskQuestionActivity.this, "Place Name Must Be Valid", Toast.LENGTH_SHORT).show();
//                } else if (message.length() < 4) {
//                    Toast.makeText(AlbumsAskQuestionActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    addSupportMessage(name, email, message);
//                }
//            }
//        });


    }
}