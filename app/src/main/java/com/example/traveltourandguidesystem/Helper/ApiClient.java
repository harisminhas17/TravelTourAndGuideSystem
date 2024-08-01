package com.example.traveltourandguidesystem.Helper;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public Retrofit getClient (Context context){


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .followRedirects(false)
                .followSslRedirects(false)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .cache(null)
                .build();

//        OkHttpClient.Builder client = new OkHttpClient.Builder()
//                .callTimeout(20, TimeUnit.SECONDS)
//                .addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
