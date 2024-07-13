package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.ImagesAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.Models.ImageUriModel;
import com.example.traveltourandguidesystem.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {

    EditText tv_support_name, tv_support_email, tv_support_message;
    ImageView back_btn3;
    ProgressDialog progressDialog;
    Button tv_support_done, tv_image_upload;
    static RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(SupportActivity.this);
        progressDialog.setCancelable(true);

        back_btn3 = findViewById(R.id.back_btn3);
        tv_support_done = findViewById(R.id.tv_support_done);
        tv_support_name = findViewById(R.id.tv_support_name);
        tv_support_email = findViewById(R.id.tv_support_email);
        tv_support_message = findViewById(R.id.tv_support_message);
        recyclerView = findViewById(R.id.recyclerView_Support);
        tv_image_upload = findViewById(R.id.tv_image_upload);

        String email = new SharedPref().getEmailData(SupportActivity.this);
        String name = new SharedPref().getNameData(SupportActivity.this);

        if (name.length() > 0 && email.length() > 0) {
            tv_support_name.setText(name);
            tv_support_email.setText(email);
        }
        back_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SupportActivity.this, MainActivity.class));
                finish();
            }
        });

        tv_image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(SupportActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(100, 100)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(999);
            }
        });

        tv_support_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tv_support_name.getText().toString();
                String email = tv_support_email.getText().toString();
                String message = tv_support_message.getText().toString();
                if (name.length() < 2) {
                    Toast.makeText(SupportActivity.this, "Name Must Be Valid", Toast.LENGTH_SHORT).show();
                } else if (message.length() < 4) {
                    Toast.makeText(SupportActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    addSupportMessage(name, email, message);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(new ImagesAdapter(arrayList, SupportActivity.this));
    }

    private void addSupportMessage(String name, String email, String message) {
        progressDialog.setMessage("Message is Sending");
        progressDialog.show();
        int id = new SharedPref().getid(SupportActivity.this);
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(SupportActivity.this).create(APIInterface.class).addSupportMessage(name, email, message, id + "");
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("Message Does Not Send");
                        Toast.makeText(SupportActivity.this, msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SupportActivity.this, "Message Send Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SupportActivity.this, MainActivity.class));
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

    static ArrayList<ImageUriModel> arrayList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 999) {

            //Image Uri will not be null for RESULT_OK

            Uri uri = data.getData();
            ImageUriModel imageUriModel = new ImageUriModel();
            imageUriModel.setUri(uri);
            arrayList.add(imageUriModel);
            recyclerView.getAdapter().notifyDataSetChanged();

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Photos Not Uploaded", Toast.LENGTH_SHORT).show();
        }

    }

}