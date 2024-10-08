package com.example.traveltourandguidesystem.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.traveltourandguidesystem.Fragments.AlbumsFragment;
import com.example.traveltourandguidesystem.Fragments.HomeFragment;
import com.example.traveltourandguidesystem.Fragments.MenuFragment;
import com.example.traveltourandguidesystem.Maps.MapFragment;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    ImageView tv_main_loc;
    ImageView tv_main_home;
    ImageView tv_main_gallery;
    ImageView tv_main_bar;
    FragmentContainerView fragment_container;

    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        context = MainActivity.this;
        tv_main_bar = findViewById(R.id.tv_main_bar);
        tv_main_gallery = findViewById(R.id.tv_main_gallery);
        tv_main_loc = findViewById(R.id.tv_main_loc);
        tv_main_home = findViewById(R.id.tv_main_home);
        fragment_container = findViewById(R.id.fragment_container);

        setFragmentContainer(new HomeFragment());
        tv_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_main_home.setImageDrawable(getResources().getDrawable(R.drawable.home));
                tv_main_gallery.setImageDrawable(getResources().getDrawable(R.drawable.ic_albums_white));
                tv_main_loc.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_pin_white));
                tv_main_bar.setImageDrawable(getResources().getDrawable(R.drawable.baseline_segment_24));

                setFragmentContainer(new HomeFragment());
            }
        });
        tv_main_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_main_home.setImageDrawable(getResources().getDrawable(R.drawable.home_white));
                tv_main_gallery.setImageDrawable(getResources().getDrawable(R.drawable.orange_photos));
                tv_main_loc.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_pin_white));
                tv_main_bar.setImageDrawable(getResources().getDrawable(R.drawable.baseline_segment_24));
                setFragmentContainer(new AlbumsFragment());

            }
        });
        tv_main_loc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tv_main_home.setImageDrawable(getResources().getDrawable(R.drawable.home_white));
                tv_main_gallery.setImageDrawable(getResources().getDrawable(R.drawable.ic_albums_white));
                tv_main_loc.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_pin_colored));
                tv_main_bar.setImageDrawable(getResources().getDrawable(R.drawable.baseline_segment_24));
                setFragmentContainer(new MapFragment());
            }
        });
        tv_main_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setFragmentContainer(new MenuFragment());
                tv_main_home.setImageDrawable(getResources().getDrawable(R.drawable.home_white));
                tv_main_gallery.setImageDrawable(getResources().getDrawable(R.drawable.ic_albums_white));
                tv_main_loc.setImageDrawable(getResources().getDrawable(R.drawable.ic_location_pin_white));
                tv_main_bar.setImageDrawable(getResources().getDrawable(R.drawable.baseline_segment_orange_24));
            }
        });
    }

    private void setFragmentContainer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit Alert")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }
}