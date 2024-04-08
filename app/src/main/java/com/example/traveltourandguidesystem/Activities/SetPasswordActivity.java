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
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPasswordActivity extends AppCompatActivity {
    EditText et_set_forgot_password;
    TextView et_done_btn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        getSupportActionBar().hide();
        et_set_forgot_password=findViewById(R.id.et_set_forgot_password);
        et_done_btn=findViewById(R.id.et_done_btn);
        progressDialog = new ProgressDialog(SetPasswordActivity.this);
        progressDialog.setCancelable(true);
        et_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = et_set_forgot_password.getText().toString();
                if(password.length()<5){
                    Toast.makeText(SetPasswordActivity.this, "Password length must be greater then 5", Toast.LENGTH_SHORT).show();
                }else {
                    String email=getIntent().getStringExtra("email");
                    setNewPassword(email,password);

                }

            }
        });
    }
    private void setNewPassword(String email,String password) {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(SetPasswordActivity.this).create(APIInterface.class).setPassword(password,email);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    Toast.makeText(SetPasswordActivity.this, "Password Has Been Set", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SetPasswordActivity.this, LoginActivity.class));
                    finish();
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