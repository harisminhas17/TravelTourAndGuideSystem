package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {
    TextView done_btn;
    PinView otpView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        getSupportActionBar().hide();
        otpView = findViewById(R.id.firstPinView);
        done_btn = findViewById(R.id.done_btn);
        progressDialog = new ProgressDialog(OTPActivity.this);
        progressDialog.setCancelable(true);
        String email = getIntent().getStringExtra("email");
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otpView.getText().toString();
                if (otp.length() < 5) {
                    Toast.makeText(OTPActivity.this, "Enter Six Digits OTP Here", Toast.LENGTH_SHORT).show();
                } else {
                    verifyOTP(email, otp);
                }
            }
        });
        sendotp(email);
    }


    private void sendotp(String email) {
        progressDialog.setMessage("Sending OTP...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(OTPActivity.this).create(APIInterface.class).sendotp(email);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        Toast.makeText(OTPActivity.this, "Email not exists", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(OTPActivity.this, "OTP has been sent to your Email", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                progressDialog.dismiss();
                throwable.printStackTrace();
                finish();
            }
        });

    }


    private void verifyOTP(String email, String otp) {
        progressDialog.setMessage("Verify OTP...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(OTPActivity.this).create(APIInterface.class).verifyOTP(email, otp);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        Toast.makeText(OTPActivity.this, "OTP is invalid", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(OTPActivity.this, "OTP is correct", Toast.LENGTH_LONG).show();
                        if (getIntent().getStringExtra("next").contentEquals("setPassword")) {
                            startActivity(new Intent(OTPActivity.this, SetPasswordActivity.class).putExtra("email", email));
                        }
                        else {
                            startActivity(new Intent(OTPActivity.this, LoginActivity.class));
                        }
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                progressDialog.dismiss();
                throwable.printStackTrace();
            }
        });

    }
}