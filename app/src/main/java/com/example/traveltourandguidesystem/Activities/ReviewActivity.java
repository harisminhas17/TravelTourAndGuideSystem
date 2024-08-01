package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.Models.HotelsModel;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    private Context context;
    PlacesModel placesModel;
    HotelsModel hotelsModel;
    ImageView tv_reviews_btn;
    Button tv_rating_submit;
    RatingBar ratingBar_item;
    EditText tv_option_item, tv_review_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(ReviewActivity.this);
        progressDialog.setCancelable(true);

        context = ReviewActivity.this;
        hotelsModel = getIntent().getParcelableExtra("hotelsModel");
        placesModel = getIntent().getParcelableExtra("placesModel");

        tv_rating_submit = findViewById(R.id.tv_rating_submit);
        tv_reviews_btn = findViewById(R.id.tv_reviews_btn);
        ratingBar_item = findViewById(R.id.ratingBar_item);

        tv_option_item = findViewById(R.id.tv_option_item);

        tv_review_message = findViewById(R.id.tv_review_message);

        tv_reviews_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (hotelsModel != null) {
            tv_option_item.setText(hotelsModel.getName());
        }

        if (placesModel != null) {
            tv_option_item.setText(placesModel.getName());
        }

        tv_rating_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String item_type = "";
                int item_id = 0;
                if (hotelsModel != null) {
                    item_id = hotelsModel.getId();
                    item_type = "hotel";
                } else if (placesModel != null) {
                    item_id = placesModel.getId();
                    item_type = "place";
                }

                // Get values from the input fields

                float rating = ratingBar_item.getRating();
                String review = tv_review_message.getText().toString();
                sendUserHotelReview(item_id, rating, review, item_type);

            }
        });

    }

    private void sendUserHotelReview(int item_id, float rating, String review, String item_type) {
        int user_id = new SharedPref().getid(context);
        progressDialog.setMessage("Message is Sending");
        progressDialog.show();
        int id = new SharedPref().getid(ReviewActivity.this);
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(ReviewActivity.this).create(APIInterface.class).sendUserHotelReview(user_id, rating, review, item_id, item_type);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("Message Does Not Send");
                        Toast.makeText(ReviewActivity.this, msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ReviewActivity.this, "Message Send Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ReviewActivity.this, MainActivity.class));
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
