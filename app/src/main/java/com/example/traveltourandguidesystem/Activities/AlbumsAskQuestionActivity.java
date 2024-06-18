package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.R;

public class AlbumsAskQuestionActivity extends AppCompatActivity {

    ImageView tv_ask_q_back, tv_ask_q_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_ask_question);
        getSupportActionBar().hide();
        tv_ask_q_back = findViewById(R.id.tv_ask_q_back);
        tv_ask_q_notify = findViewById(R.id.tv_ask_q_notify);
        tv_ask_q_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAskQuestionActivity.this, AlbumsFragment.class));
            }
        });
        tv_ask_q_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAskQuestionActivity.this, NotificationActivity.class));
            }
        });
    }
}