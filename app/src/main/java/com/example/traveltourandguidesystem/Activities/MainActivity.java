package com.example.traveltourandguidesystem.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Adapters.PlacesAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView notify_btn;
    ImageView profile_btn;
    RecyclerView recyclerView_top;
    RecyclerView recyclerView;
    ArrayList <PlacesModel> placesModels=new ArrayList<>();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView_top=findViewById(R.id.recyclerView_top);
        profile_btn=findViewById(R.id.profile_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });
        notify_btn=findViewById(R.id.notify_btn);
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NotificationActivity.class));
            }
        });
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);
        ArrayList<String> mTitleDataList = new ArrayList();
        mTitleDataList.add("All");
        mTitleDataList.add("Popular");
        mTitleDataList.add("Nearby");


        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setEnabled(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        magicIndicator.onPageScrolled(index,0,0);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showAllPlaces();
    }

    private void showAllPlaces() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(MainActivity.this).create(APIInterface.class).showAllPlaces();
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (!error){
                        JSONArray records=jsonObject.getJSONArray("records");
                        for (int i = 0; i < records.length() ; i++) {
                            PlacesModel placesModel=new Gson().fromJson(records.get(i).toString(),PlacesModel.class);
                            placesModels.add(placesModel);
                        }
                        PlacesAdapter placesAdapter=new PlacesAdapter(placesModels,MainActivity.this, false);
                        recyclerView_top.setAdapter(placesAdapter);
                        PlacesAdapter placesAdapter2=new PlacesAdapter(placesModels,MainActivity.this, true);
                        recyclerView.setAdapter(placesAdapter2);

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