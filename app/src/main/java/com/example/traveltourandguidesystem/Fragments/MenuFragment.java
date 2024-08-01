package com.example.traveltourandguidesystem.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.traveltourandguidesystem.Activities.LoginActivity;
import com.example.traveltourandguidesystem.Activities.MainActivity;
import com.example.traveltourandguidesystem.Activities.SignupActivity;
import com.example.traveltourandguidesystem.Helper.APIInterface;
import com.example.traveltourandguidesystem.Helper.ApiClient;
import com.example.traveltourandguidesystem.Helper.SharedPref;
import com.example.traveltourandguidesystem.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {
    ImageView back_btn, tv_share_to_others;
    TextView tv_language;
    TextView tv_delete_account;
    TextView tv_reviews;
    TextView tv_logout;
    SwitchCompat tv_btn_push;
    SwitchCompat tv_btn_payment_method;

    private Context context;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.context = getContext();
        initViews(view);
        clickListeners();
        loadLocale();

    }


    private void initViews(View view) {
        back_btn = view.findViewById(R.id.back_btn);
        tv_language = view.findViewById(R.id.tv_language);
        tv_delete_account = view.findViewById(R.id.tv_delete_account);
        tv_reviews = view.findViewById(R.id.tv_reviews);
        tv_btn_push = view.findViewById(R.id.tv_btn_push);
        tv_btn_payment_method = view.findViewById(R.id.tv_btn_payment_method);
        tv_logout = view.findViewById(R.id.tv_logout);
        tv_share_to_others = view.findViewById(R.id.tv_share_to_others);
    }


    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout Your Account!");
        builder.setMessage("Do you want to logout your account?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Logout Successfully", Toast.LENGTH_SHORT).show();
                SharedPref sharedPref = new SharedPref();
                sharedPref.logout(context);
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                ((Activity) context).finish();
            }
        });
        builder.setNegativeButton("No", null).show();
    }

    private void clickListeners() {
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        tv_share_to_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Book Your Tour Now!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "TRAVEL TOUR & GUIDE SYSTEM \n BOOK YOUR TOUR NOW!");
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });


        tv_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        tv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changelanguage();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, MainActivity.class));
            }
        });

        tv_btn_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tv_btn_push.isChecked()) {
                    Log.d("You are :", "Checked");
                } else {
                    Log.d("You are :", " Not Checked");
                    // Toast.makeText(getApplicationContext(), "Switch1 :" + tv_btn_push,Toast.LENGTH_LONG).show(); // display the current state for switch's
                }
            }


        });
    }


    //language change here
    private void changelanguage() {

        final String languages[] = {"English", "Urdu"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    setlocale("");
                    ((Activity) context).recreate();
                } else if (which == 1) {
                    setlocale("Ur");
                    ((Activity) context).recreate();
                }
            }
        });
        builder.create();
        builder.show();
    }


    //language settings and controlling of the language
    private void setlocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        //saving of the langauge when app run again or load

        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("app_lang", language);
        editor.apply();
    }

    //load of the settings when app get start
    private void loadLocale() {
        SharedPreferences preferences = context.getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("app_lang", "");
        setlocale(language);
    }

    public void deleteUser() {

        int id2 = new SharedPref().getid(getContext());
        String password = new SharedPref().getpassword(getContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Deletion of Account!");
        builder.setMessage("Are You Sure To Delete Your Account?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(context, SignupActivity.class);
                startActivity(intent);
                ((Activity) context).finish();
                Toast.makeText(context, "Your Account Has Been Deleted Successfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();


        ApiClient apiClient = new ApiClient();
        Call<Object> responseCall = apiClient.getClient(getContext()).create(APIInterface.class).deleteUser(id2, password);
        responseCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    boolean error = jsonObject.getBoolean("error");
                    if (error) {
                        String msg = jsonObject.getString("Please Wait");
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