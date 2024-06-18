package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.NotificationAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.Models.NotificationModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    ImageView back_btn2;
    ProgressDialog progressDialog;
    ArrayList<NotificationModel> notificationModels = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_notification);
        back_btn2 = findViewById(R.id.back_btn2);
        recyclerView = findViewById(R.id.recyclerView);
        progressDialog = new ProgressDialog(NotificationActivity.this);
        progressDialog.setCancelable(true);
        back_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAllNotifications();
    }

    private void showAllNotifications() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        int user_id = new SharedPref().getid(NotificationActivity.this);
        Call<Object> responseCall = apiClient.getClient(NotificationActivity.this).create(APIInterface.class).showAllNotifications(user_id);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        JSONArray records = jsonObject.getJSONArray("records");
                        for (int i = 0; i < records.length(); i++) {
                            NotificationModel notificationModel = new Gson().fromJson(records.get(i).toString(), NotificationModel.class);
                            notificationModels.add(notificationModel);
                        }
                        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationModels, NotificationActivity.this, false);
                        recyclerView.setAdapter(notificationAdapter);
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