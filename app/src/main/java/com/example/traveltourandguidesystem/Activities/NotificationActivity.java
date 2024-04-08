package com.example.traveltourandguidesystem.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class NotificationActivity extends AppCompatActivity {
    TextView back_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notification);
        back_btn2=findViewById(R.id.profile);
        back_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){ onBackPressed();}
        });
    }
}