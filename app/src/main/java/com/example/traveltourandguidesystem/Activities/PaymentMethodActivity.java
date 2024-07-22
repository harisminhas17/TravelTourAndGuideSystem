package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class PaymentMethodActivity extends AppCompatActivity {

    ImageView tv_payment_back;

    EditText tv_payment_name, tv_payment_phone, tv_payment_address, tv_payment_easy;
    Button tv_payment_submit;
    private Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().hide();

        context = PaymentMethodActivity.this;

        tv_payment_name = findViewById(R.id.tv_payment_name);
        tv_payment_phone = findViewById(R.id.tv_payment_phone);
        tv_payment_address = findViewById(R.id.tv_payment_address);
        tv_payment_easy = findViewById(R.id.tv_payment_easy);

        tv_payment_back = findViewById(R.id.tv_payment_back);
        tv_payment_submit = findViewById(R.id.tv_payment_submit);

        tv_payment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_payment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ConfirmationTourActivity.class));
            }
        });
    }
}