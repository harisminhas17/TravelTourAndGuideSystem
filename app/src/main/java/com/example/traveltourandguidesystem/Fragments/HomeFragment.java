package com.example.traveltourandguidesystem.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Activities.EmergencyDetailActivity;
import com.example.traveltourandguidesystem.Activities.NotificationActivity;
import com.example.traveltourandguidesystem.Activities.ProfileActivity;
import com.example.traveltourandguidesystem.Activities.SearchActivity;
import com.example.traveltourandguidesystem.Adapters.PlacesAdapter;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    ImageView notify_btn, tv_search_bar;
    ImageView emg_btn;
    ImageView profile_btn;
    RecyclerView recyclerView_top;
    RecyclerView recyclerView, recyclerView_all;
    ArrayList<PlacesModel> placesModels = new ArrayList<>();
    ProgressDialog progressDialog;
    private Context context;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        clickListeners();

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);


        showAllPlaces();
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView_top = view.findViewById(R.id.recyclerView_top);
        recyclerView_all = view.findViewById(R.id.recyclerView_all);
        profile_btn = view.findViewById(R.id.profile_btn);
        notify_btn = view.findViewById(R.id.notify_btn);
        emg_btn = view.findViewById(R.id.emg_btn);
        tv_search_bar = view.findViewById(R.id.tv_search_bar);

    }

    private void clickListeners() {
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ProfileActivity.class));
            }
        });
        notify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, NotificationActivity.class));
            }
        });
        emg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, EmergencyDetailActivity.class));
            }
        });
        tv_search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchActivity.class));
            }
        });
    }

    private void showAllPlaces() {
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(context).create(APIInterface.class).showAllPlaces();
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
                            PlacesModel placesModel = new Gson().fromJson(records.get(i).toString(), PlacesModel.class);
                            placesModels.add(placesModel);
                        }
                        PlacesAdapter placesAdapter = new PlacesAdapter(placesModels, context, false);
                        recyclerView_top.setAdapter(placesAdapter);
                        PlacesAdapter placesAdapter2 = new PlacesAdapter(placesModels, context, false);
                        recyclerView.setAdapter(placesAdapter2);
                        PlacesAdapter placesAdapter3 = new PlacesAdapter(placesModels, context, true);
                        recyclerView_all.setAdapter(placesAdapter3);
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