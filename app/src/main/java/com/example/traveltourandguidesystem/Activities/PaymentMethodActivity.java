package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class PaymentMethodActivity extends AppCompatActivity {

    ImageView tv_payment_back;
    Button tv_payment_submit;
    private Context context;

    CheckBox checkBox_checkin_yes, checkBox_checkin_no;
    CheckBox checkBox_payonline_yes, checkBox_payonline_no;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().hide();
        context = PaymentMethodActivity.this;

        checkBox_checkin_yes = findViewById(R.id.checkBox_checkin_yes);
        checkBox_checkin_no = findViewById(R.id.checkBox_checkin_no);

        checkBox_payonline_yes = findViewById(R.id.checkBox_payonline_yes);
        checkBox_payonline_no = findViewById(R.id.checkBox_payonline_no);

        tv_payment_back = findViewById(R.id.tv_payment_back);
        tv_payment_submit = findViewById(R.id.tv_payment_submit);

        checkBox_checkin_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_checkin_yes.isChecked()) {
                    Toast.makeText(PaymentMethodActivity.this, "Check-in Yes", Toast.LENGTH_SHORT).show();
                    checkBox_checkin_no.setEnabled(false);
                    checkBox_payonline_yes.setEnabled(false);
                    checkBox_payonline_no.setEnabled(false);
                } else {
                    checkBox_checkin_no.setEnabled(true);
                    checkBox_payonline_yes.setEnabled(true);
                    checkBox_payonline_no.setEnabled(true);
                }
            }
        });

        // Set OnClickListener for No CheckBox
        checkBox_checkin_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_checkin_no.isChecked()) {
                    Toast.makeText(PaymentMethodActivity.this, "Check-in No", Toast.LENGTH_SHORT).show();
                    checkBox_checkin_yes.setEnabled(false);
                    checkBox_payonline_yes.setEnabled(true);
                    checkBox_payonline_no.setEnabled(false);
                } else {
                    checkBox_checkin_yes.setEnabled(true);
                    checkBox_payonline_yes.setEnabled(true);
                    checkBox_payonline_no.setEnabled(true);
                }
            }
        });

        checkBox_payonline_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_payonline_yes.isChecked()) {
                    Toast.makeText(PaymentMethodActivity.this, "Pay Online Yes", Toast.LENGTH_SHORT).show();
                    checkBox_payonline_no.setEnabled(false);
                    checkBox_checkin_yes.setEnabled(false);
                    checkBox_checkin_no.setEnabled(false);
                    Intent intent = new Intent(PaymentMethodActivity.this, PayonlineActivity.class);
                    startActivity(intent);
                } else {
                    checkBox_checkin_yes.setEnabled(true);
                    checkBox_checkin_no.setEnabled(true);
                    checkBox_payonline_no.setEnabled(true);
                }
            }
        });

        // Set OnClickListener for No CheckBox
        checkBox_payonline_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox_payonline_no.isChecked()) {
                    Toast.makeText(PaymentMethodActivity.this, "Pay Online No", Toast.LENGTH_SHORT).show();
                    checkBox_payonline_yes.setEnabled(false);
                    checkBox_checkin_yes.setEnabled(true);
                    checkBox_checkin_no.setEnabled(false);
                } else {
                    checkBox_checkin_yes.setEnabled(true);
                    checkBox_checkin_no.setEnabled(true);
                    checkBox_payonline_yes.setEnabled(true);
                }
            }
        });

        tv_payment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_payment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentMethodActivity.this, "Congratulations Your Booking is Confirmed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, ConfirmationTourActivity.class));
            }
        });
    }
}