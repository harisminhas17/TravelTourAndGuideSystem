package com.example.traveltourandguidesystem.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public void saveLoginData(Context context, String name, String email, String password, int user_id) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Name", name);
        editor.putString("Password", password);
        editor.putInt("user_id", user_id);
        editor.commit();
    }

    public String getEmailData(Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");

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
}
