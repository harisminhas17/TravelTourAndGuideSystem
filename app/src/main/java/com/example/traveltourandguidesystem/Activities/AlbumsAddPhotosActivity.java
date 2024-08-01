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

public class AlbumsAddPhotosActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageView tv_add_photos_back_btn;
    ImageView tv_add_photos_notify_btn, tv_albums_add_photos_upload;
    Button tv_albums_add_photos_cancel, tv_image_select;

    EditText tv_select_photos, tv_captions;

    static RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_add_photos);
        progressDialog = new ProgressDialog(AlbumsAddPhotosActivity.this);
        progressDialog.setCancelable(true);
        getSupportActionBar().hide();

        tv_select_photos = findViewById(R.id.tv_select_photos);
        tv_captions = findViewById(R.id.tv_captions);

        tv_add_photos_back_btn = findViewById(R.id.tv_add_photos_back_btn);
        tv_add_photos_notify_btn = findViewById(R.id.tv_add_photos_notify_btn);

        tv_albums_add_photos_cancel = findViewById(R.id.tv_albums_add_photos_cancel);
        tv_albums_add_photos_upload = findViewById(R.id.tv_albums_add_photos_upload);
        tv_image_select = findViewById(R.id.tv_image_select);

        recyclerView = findViewById(R.id.recyclerView_albums_add);

        tv_add_photos_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tv_add_photos_notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AlbumsAddPhotosActivity.this, NotificationActivity.class));
            }
        });
        tv_albums_add_photos_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tv_albums_add_photos_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String place_name = tv_select_photos.getText().toString();
                String caption = tv_captions.getText().toString();
                if (place_name.length() < 2) {
                    Toast.makeText(AlbumsAddPhotosActivity.this, "Place Name Must Be Valid", Toast.LENGTH_SHORT).show();
                } else if (caption.length() < 4) {
                    Toast.makeText(AlbumsAddPhotosActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    uploadPhotos(place_name, caption);
                }
            }
        });
        tv_image_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AlbumsAddPhotosActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(100, 100)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(999);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setAdapter(new ImagesAdapter(arrayList, AlbumsAddPhotosActivity.this));
    }

    private void uploadPhotos(String place_name, String caption) {
        progressDialog.setMessage("Message is Sending");
        progressDialog.show();
        int id = new SharedPref().getid(AlbumsAddPhotosActivity.this);
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(AlbumsAddPhotosActivity.this).create(APIInterface.class).uploadPhotos(place_name, caption, id + "");
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("Message Does Not Send");
                        Toast.makeText(AlbumsAddPhotosActivity.this, msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AlbumsAddPhotosActivity.this, "Message Send Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AlbumsAddPhotosActivity.this, MainActivity.class));
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