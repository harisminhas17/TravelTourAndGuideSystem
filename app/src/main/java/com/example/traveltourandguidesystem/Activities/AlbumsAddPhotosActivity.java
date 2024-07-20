package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AlbumsAddPhotosActivity extends AppCompatActivity {

    ImageView tv_add_photos_back_btn;
    ImageView tv_add_photos_notify_btn, tv_albums_add_photos_upload;
    Button tv_albums_add_photos_cancel, tv_image_select;

    static RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_add_photos);
        getSupportActionBar().hide();

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

//        tv_albums_add_photos_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = tv_support_name.getText().toString();
//                String email = tv_support_email.getText().toString();
//                String message = tv_support_message.getText().toString();
//                if (name.length() < 2) {
//                    Toast.makeText(AlbumsAddPhotosActivity.this, "Place Name Must Be Valid", Toast.LENGTH_SHORT).show();
//                } else if (message.length() < 4) {
//                    Toast.makeText(AlbumsAddPhotosActivity.this, "Message Must Be Greater Then 5 Characters", Toast.LENGTH_SHORT).show();
//                } else {
//                    addSupportMessage(name, email, message);
//                }
//            }
//        });
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

//        static ArrayList<ImageUriModel> arrayList = new ArrayList<>();
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (resultCode == Activity.RESULT_OK && requestCode == 999) {
//
//                //Image Uri will not be null for RESULT_OK
//
//                Uri uri = data.getData();
//                ImageUriModel imageUriModel = new ImageUriModel();
//                imageUriModel.setUri(uri);
//                arrayList.add(imageUriModel);
//                recyclerView.getAdapter().notifyDataSetChanged();
//
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Photos Not Uploaded", Toast.LENGTH_SHORT).show();
//            }
//
//        }


    }
}