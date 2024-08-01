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

public class SignupActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView submit_btn;
    EditText edit_text_name;
    EditText edit_text_email_address2;
    EditText edit_text_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();

        edit_text_name = findViewById(R.id.edit_text_name);
        edit_text_email_address2 = findViewById(R.id.edit_text_email_address2);
        edit_text_password2 = findViewById(R.id.edit_text_password2);
        submit_btn = findViewById(R.id.submit_btn);

        progressDialog=new ProgressDialog(SignupActivity.this);
        progressDialog.setCancelable(true);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, password;
                name = edit_text_name.getText().toString();
                email = edit_text_email_address2.getText().toString();
                password = edit_text_password2.getText().toString();

                if (name.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Enter Your Name First ", Toast.LENGTH_SHORT).show();
                } else if (email.length() < 6) {
                    Toast.makeText(SignupActivity.this, "Enter Your Email ", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 2) {
                    Toast.makeText(SignupActivity.this, "Enter Your Password ", Toast.LENGTH_SHORT).show();
                } else {
                    signup(name, email, password);
                }
            }

        });
    }

    private void signup(String name, String email, String password) {
        progressDialog.setMessage("Creating New Account...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(SignupActivity.this).create(APIInterface.class).register(name, email, password);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        Toast.makeText(SignupActivity.this, "This email already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "Verify Your Email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, OTPActivity.class).putExtra("email", email).putExtra("next", "main"));
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
