package com.example.traveltourandguidesystem.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.HotelsAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Models.HotelsModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsListActivity extends AppCompatActivity {

    static int city_id;
    static String tourGuiderPrice;
    static String vehiclePrice;
    RecyclerView recyclerView_top;

    ArrayList<HotelsModel> hotelsModels = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_list);
        getSupportActionBar().hide();
        context = HotelsListActivity.this;
        recyclerView_top = findViewById(R.id.recyclerView_top);

        Intent intent = getIntent();
        city_id = getIntent().getIntExtra("city_id", 0);
        tourGuiderPrice = intent.getStringExtra("tourGuiderPrice");
        vehiclePrice = intent.getStringExtra("vehiclePrice");


    }

    @Override
    protected void onResume() {
        super.onResume();
        getHotelbyCityid();
    }

    private void getHotelbyCityid() {
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(context).create(APIInterface.class).getHotelbyCityid(city_id);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                hotelsModels.clear();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        JSONArray records = jsonObject.getJSONArray("records");
                        for (int i = 0; i < records.length(); i++) {
                            HotelsModel hotelsModel = new Gson().fromJson(records.get(i).toString(), HotelsModel.class);
                            hotelsModels.add(hotelsModel);
                        }
                        HotelsAdapter hotelsAdapter = new HotelsAdapter(hotelsModels, context, false);
                        recyclerView_top.setAdapter(hotelsAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}