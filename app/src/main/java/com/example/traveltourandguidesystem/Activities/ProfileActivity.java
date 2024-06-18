package com.example.traveltourandguidesystem.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView back_btn;
    EditText tv_name;
    EditText tv_email;
    EditText tv_location, tv_password;
    ImageView tv_profile_support_btn;
    Button btn_done_profile;
    CircleImageView tv_pic;
    FloatingActionButton floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);


        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_location = findViewById(R.id.tv_location);
        tv_password = findViewById(R.id.tv_password);
        tv_profile_support_btn = findViewById(R.id.tv_profile_support_btn);
        back_btn = findViewById(R.id.back_btn);
        btn_done_profile = findViewById(R.id.btn_done_profile);
        tv_pic = findViewById(R.id.tv_pic);
        floatingActionButton3 = findViewById(R.id.floatingActionButton3);


        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setCancelable(true);
        String email, name, password;
        SharedPref sharedPref = new SharedPref();
        email = sharedPref.getEmailData(ProfileActivity.this);
        name = sharedPref.getNameData(ProfileActivity.this);
        password = sharedPref.getpassword(ProfileActivity.this);
        tv_name.setText(name);
        tv_email.setText(email);
        tv_password.setText(password);
        tv_profile_support_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SupportActivity.class));
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(999);

            }
        });
        btn_done_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tv_name.getText().toString().trim();
                String email = tv_email.getText().toString().trim();
                String password = tv_password.getText().toString().trim();
                if (name.length() < 2) {
                    Toast.makeText(ProfileActivity.this, "Name Must Be Valid", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 3) {
                    Toast.makeText(ProfileActivity.this, "Password Must Be Greater Then 3 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    updateProfile(name, email, password);
                }
            }
        });
    }

    private void updateProfile(String name, String email, String password) {
        progressDialog.setMessage("Updating Your Profile...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(ProfileActivity.this).create(APIInterface.class).updateProfile(name, email, password);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        Toast.makeText(ProfileActivity.this, "Name Must Be Valid", Toast.LENGTH_SHORT).show();
                    } else if (email.length() < 5) {
                        Toast.makeText(ProfileActivity.this, "Email Does Not Change", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        String name = jsonObject.getJSONObject("user").getString("name");
                        int id = jsonObject.getJSONObject("user").getInt("id");
                        new SharedPref().saveLoginData(ProfileActivity.this, name, email, password, id);
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int i = 0;

        if (resultCode == Activity.RESULT_OK && requestCode == 999) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            tv_pic.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Profile Not Uploaded", Toast.LENGTH_SHORT).show();
        }
    }

}