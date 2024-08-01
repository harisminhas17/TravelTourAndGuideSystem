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
    Call<Object> login(@Field("email") String email,
                       @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<Object> register(@Field("name") String name,
                          @Field("email") String email,
                          @Field("password") String password);

    @POST("updateProfile")
    @FormUrlEncoded
    Call<Object> updateProfile(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password,
                               @Field("address") String address);

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

    @GET("searchPlaces")
    Call<Object> searchPlaces(@Query("query") String query);

    @GET("getHotelbyCityid")
    Call<Object> getHotelbyCityid(@Query("city_id") int city_id);

    @GET("findGuiderByCityid")
    Call<Object> findGuiderByCityid(@Query("city_id") int city_id);

    @GET("findTransportationbyCityid")
    Call<Object> findTransportationByCityid(@Query("city_id") int city_id);

    @GET("showAllNotifications")
    Call<Object> showAllNotifications(@Query("user_id") int user_id);

    @POST("updateNotificationToken")
    @FormUrlEncoded
    Call<Object> updateNotificationToken(@Field("email") String email, @Field("token") String token);

    @POST("deleteUser")
    @FormUrlEncoded
    Call<Object> deleteUser(@Query("id") int id, @Field("password") String password);

    @POST("bookingTour")
    @FormUrlEncoded
    Call<Object> bookingTour(@Field("name") String name,
                             @Field("phone_number") String phone_number,
                             @Field("address") String address,
                             @Field("city") String city,
                             @Field("arrival_date") String arrival_date,
                             @Field("departure_date") String departure_date,
                             @Field("province") String province,
                             @Field("room_type") String room_type,
                             @Field("no_of_rooms") String no_of_rooms,
                             @Field("bed_type") String bed_type,
                             @Field("no_of_guests") String no_of_guests,
                             @Field("special_request") String special_request,
                             @Field("user_id") String user_id);

    @POST("uploadPhotos")
    @FormUrlEncoded
    Call<Object> uploadPhotos(@Field("place_name") String place_name, @Field("caption") String caption, @Field("user_id") String user_id);

    @POST("sendUserHotelReview")
    @FormUrlEncoded
    Call<Object> sendUserHotelReview(@Field("user_id") int user_id, @Field("rating") float rating, @Field("review") String review, @Field("item_id") int item_id, @Field("item_type") String item_type);
}
