package com.example.traveltourandguidesystem.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;
public class ProfileActivity extends AppCompatActivity {
    ImageView back_btn;
    TextView tv_name, tv_email, tv_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        tv_name=findViewById(R.id.tv_name);
        tv_email=findViewById(R.id.tv_email);
        tv_address=findViewById(R.id.tv_address);
        back_btn=findViewById(R.id.back_btn);
        String email, name;
        SharedPref sharedPref=new SharedPref();
        email=sharedPref.getEmailData(ProfileActivity.this);
        name=sharedPref.getNameData(ProfileActivity.this);
        tv_name.setText(name);
        tv_email.setText(email);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            onBackPressed();
        }
        });
    }
}