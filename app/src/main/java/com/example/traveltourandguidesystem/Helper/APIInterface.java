package com.example.traveltourandguidesystem.Helper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("login")
    @FormUrlEncoded
    Call<Object> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<Object> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("sendOTP")
    @FormUrlEncoded
    Call<Object> sendotp(@Field("email") String email);

    @POST("verifyOTP")
    @FormUrlEncoded
    Call<Object> verifyOTP(@Field("email") String email,@Field("otp") String otp);

    @POST("setPassword")
    @FormUrlEncoded
    Call<Object> setPassword(@Field("password") String password,@Field("email") String email);

    @GET("showAllPlaces")
    Call<Object> showAllPlaces();

}
