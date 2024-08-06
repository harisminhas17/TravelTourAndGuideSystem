package com.example.traveltourandguidesystem.Helper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.traveltourandguidesystem.Models.HotelsModel;
import com.example.traveltourandguidesystem.Models.PlacesModel;
import com.google.gson.Gson;

public class SharedPref {
    public void saveLoginData(Context context, String name, String email, String password, int user_id, String address) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Name", name);
        editor.putString("Password", password);
        editor.putInt("user_id", user_id);
        editor.putString("Address", address);
        editor.commit();
    }

    public void savePlaceDetail(Context context, PlacesModel placesModel) {
        if (context == null)
            return;
        final SharedPreferences sharedPreferences = context.getSharedPreferences("placeModel", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("placeModel", new Gson().toJson(placesModel));
        editor.apply();
    }

    public PlacesModel getPlaceDetails(Context context) {
        if (context == null)
            return null;
        final SharedPreferences sharedPreferences = context.getSharedPreferences("placeModel", MODE_PRIVATE);
        return new Gson().fromJson(sharedPreferences.getString("placeModel", ""), PlacesModel.class);
    }

    public void saveHotelDetails(Context context, HotelsModel hotelsModel) {
        if (context == null)
            return;
        final SharedPreferences sharedPreferences = context.getSharedPreferences("hotelsModel", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hotelsModel", new Gson().toJson(hotelsModel));
        editor.apply();
    }

    public HotelsModel getHotelDetails(Context context) {
        if (context == null)
            return null;
        final SharedPreferences sharedPreferences = context.getSharedPreferences("hotelsModel", MODE_PRIVATE);
        return new Gson().fromJson(sharedPreferences.getString("hotelsModel", ""), HotelsModel.class);
    }

    public String getEmailData(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");

    }

    public static String getaddressData(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Address", "");

    }

    public String getNameData(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name", "");

    }

    public int getid(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", -1);

    }

    public String getpassword(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }

    public void logout(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Email");
        editor.remove("Name");
        editor.remove("Password");
        editor.remove("user_id");
        editor.commit();
    }

    public void saveBookingPrice(Context context, int totalPrice) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("totalPrice", totalPrice);
        editor.apply();
    }
}
