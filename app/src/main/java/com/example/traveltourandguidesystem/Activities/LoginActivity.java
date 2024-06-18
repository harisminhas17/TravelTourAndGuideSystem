package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView forgot_btn;
    TextView login_btn;
    EditText edit_text_email;
    EditText edit_text_password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        forgot_btn = findViewById(R.id.forgot_btn);
        login_btn = findViewById(R.id.login_btn);
        edit_text_email = findViewById(R.id.edit_text_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCancelable(true);
        String email = new SharedPref().getEmailData(LoginActivity.this);
        edit_text_email.setText(email);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = edit_text_email.getText().toString();
                password = edit_text_password.getText().toString();

                if (email.length() < 5) {
                    Toast.makeText(LoginActivity.this, "Enter Your Registered Email First ", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 3) {
                    Toast.makeText(LoginActivity.this, "Enter Your Correct Password", Toast.LENGTH_SHORT).show();
                } else {
                    login(email, password);
                }
            }
        });
        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }


    private void login(String email, String password) {
        progressDialog.setMessage("Waiting For Logging...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(LoginActivity.this).create(APIInterface.class).login(email, password);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                        if (msg.contains("verify")) {
                            startActivity(new Intent(LoginActivity.this, OTPActivity.class).putExtra("next", "login").putExtra("email", email));
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                        String name = jsonObject.getJSONObject("user").getString("name");
                        int id = jsonObject.getJSONObject("user").getInt("id");
                        new SharedPref().saveLoginData(LoginActivity.this, name, email, password, id);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
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