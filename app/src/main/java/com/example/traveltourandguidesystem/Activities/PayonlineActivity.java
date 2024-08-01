package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class PayonlineActivity extends AppCompatActivity {

    ImageView open_whatsapp;
    Button tv_payment_online_done;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_payonline);

        open_whatsapp = findViewById(R.id.open_whatsapp);
        tv_payment_online_done = findViewById(R.id.tv_payment_online_done);

        tv_payment_online_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayonlineActivity.this, ConfirmationTourActivity.class));
            }
        });

        open_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp();
            }
        });
    }

    private void openWhatsApp() {
        String contact = "+923165461537";
        String url = "https://api.whatsapp.com/send?phone=" + contact;

        try {
            PackageManager pm = getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp is not installed on your device", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}