package com.example.traveltourandguidesystem.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.TransportationAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Models.TransporationModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportationListActivity extends AppCompatActivity {

    RecyclerView recyclerView_top;
    Button tv_v_skip;
    private Context context;
    int city_id;


    ArrayList<TransporationModel> transporationModel = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_transportation_list);
        city_id = getIntent().getIntExtra("city_id", 0);
        recyclerView_top = findViewById(R.id.recyclerView_top);
        tv_v_skip = findViewById(R.id.tv_v_skip);
        context = TransportationListActivity.this;

        tv_v_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(context,));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        findTransportationByCityid();
    }

    private void findTransportationByCityid() {
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(context).create(APIInterface.class).findTransportationByCityid(city_id);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                transporationModel.clear();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (!error) {
                        JSONArray records = jsonObject.getJSONArray("records");
                        for (int i = 0; i < records.length(); i++) {
                            TransporationModel transporationModel = new Gson().fromJson(records.get(i).toString(), TransporationModel.class);
                            transporationModel.add(transporationModel);
                        }
                        TransportationAdapter transportationAdapter = new TransportationAdapter(transporationModel, context, false);
                        recyclerView_top.setAdapter(transportationAdapter);
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