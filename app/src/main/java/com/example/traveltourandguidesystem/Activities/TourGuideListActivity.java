package com.example.traveltourandguidesystem.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.GuiderAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Models.TourGuiderModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourGuideListActivity extends AppCompatActivity {
    RecyclerView recyclerView_top;
    Button tv_guider_skip;
    private Context context;
    static int city_id;
    ArrayList<TourGuiderModel> tourGuiderModels = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        context = TourGuideListActivity.this;
        setContentView(R.layout.activity_tour_guide_list_activty);

        city_id = getIntent().getIntExtra("city_id", 0);

        recyclerView_top = findViewById(R.id.recyclerView_top);
        tv_guider_skip = findViewById(R.id.tv_guider_skip);

        tv_guider_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TransportationListActivity.class).putExtra("city_id", city_id));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        findGuiderByCityid();
    }

    private void findGuiderByCityid() {
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(context).create(APIInterface.class).findGuiderByCityid(city_id);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                tourGuiderModels.clear();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        JSONArray records = jsonObject.getJSONArray("records");
                        for (int i = 0; i < records.length(); i++) {
                            TourGuiderModel tourGuiderModel = new Gson().fromJson(records.get(i).toString(), TourGuiderModel.class);
                            tourGuiderModels.add(tourGuiderModel);
                        }
                        GuiderAdapter guiderAdapter = new GuiderAdapter(tourGuiderModels, context, false);
                        recyclerView_top.setAdapter(guiderAdapter);
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