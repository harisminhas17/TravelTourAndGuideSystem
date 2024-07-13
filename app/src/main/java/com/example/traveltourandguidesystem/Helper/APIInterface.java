package com.example.traveltourandguidesystem.Helper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {
    @POST("login")
    @FormUrlEncoded
    Call<Object> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<Object> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("updateProfile")
    @FormUrlEncoded
    Call<Object> updateProfile(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("sendOTP")
    @FormUrlEncoded
    Call<Object> sendotp(@Field("email") String email);

    @POST("verifyOTP")
    @FormUrlEncoded
    Call<Object> verifyOTP(@Field("email") String email, @Field("otp") String otp);

    @POST("setPassword")
    @FormUrlEncoded
    Call<Object> setPassword(@Field("password") String password, @Field("email") String email);

    @POST("addSupportMessage")
    @FormUrlEncoded
    Call<Object> addSupportMessage(
            @Field("name") String name,
            @Field("email") String email,
            @Field("message") String message,
            @Field("user_id") String user_id);

    @GET("showAllPlaces")
    Call<Object> showAllPlaces();

    @GET("showAllHotels")
    Call<Object> showAllHotels();

    @GET("findGuiderByCityid")
    Call<Object> findGuiderByCityid(@Query("city_id") int city_id);

    @GET("findTransportationByCityid")
    Call<Object> findTransportationByCityid(@Query("city_id") int city_id);

    @GET("showAllNotifications")
    Call<Object> showAllNotifications(@Query("user_id") int user_id);

    @POST("updateNotificationToken")
    @FormUrlEncoded
    Call<Object> updateNotificationToken(@Field("email") String email, @Field("token") String token);

    @POST("deleteUser")
    @FormUrlEncoded
    Call<Object> deleteUser(@Query("id") int id, @Field("password") String password);


}
