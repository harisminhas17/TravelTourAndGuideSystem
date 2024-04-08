package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView sendotp_btn;
    EditText edit_text_email_address2;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        edit_text_email_address2 = findViewById(R.id.editTextEmailAddress2);
        sendotp_btn = findViewById(R.id.sendotp_btn);
        progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
        progressDialog.setCancelable(true);
        sendotp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
                String email = edit_text_email_address2.getText().toString();
                if (email.length() < 5) {
                    progressDialog.dismiss();
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter Your Correct Email", Toast.LENGTH_SHORT).show();
                } else {

                    startActivity(new Intent(ForgetPasswordActivity.this, OTPActivity.class).putExtra("email", email).putExtra("next", "setPassword"));
                }
            }
        });
    }
}