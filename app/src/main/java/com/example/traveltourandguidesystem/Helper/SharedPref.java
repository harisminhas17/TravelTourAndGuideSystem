package com.example.traveltourandguidesystem.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    public void saveLoginData(Context context,String name, String email, String password){
        final SharedPreferences sharedPreferences=context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Email",email);
        editor.putString("Name",name);
        editor.putString("Password",password);
        editor.commit();
    }
    public String getEmailData(Context context){
        final SharedPreferences sharedPreferences=context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email","");

    }
    public String getNameData(Context context){
        final SharedPreferences sharedPreferences=context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Name","");

    }
}
