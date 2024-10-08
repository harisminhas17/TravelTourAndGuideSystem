package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HotelsModel implements Parcelable {
    int id;
    String name;
    int city_id;
    int country_at;
    int province_id;
    float latitude;
    float longitude;
    int rating;
    String address;
    String chinese_food;

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    String prices;
    String fast_food;
    String about;
    ArrayList<ImageModel> images;
    String wifi;
    String single_room;
    String double_room;

    protected HotelsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        city_id = in.readInt();
        country_at = in.readInt();
        province_id = in.readInt();
        latitude = in.readFloat();
        longitude = in.readFloat();
        rating = in.readInt();
        address = in.readString();
        chinese_food = in.readString();
        fast_food = in.readString();
        about = in.readString();
        images = in.createTypedArrayList(ImageModel.CREATOR);
        wifi = in.readString();
        single_room = in.readString();
        double_room = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(city_id);
        dest.writeInt(country_at);
        dest.writeInt(province_id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeInt(rating);
        dest.writeString(address);
        dest.writeString(chinese_food);
        dest.writeString(fast_food);
        dest.writeString(about);
        dest.writeTypedList(images);
        dest.writeString(wifi);
        dest.writeString(single_room);
        dest.writeString(double_room);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HotelsModel> CREATOR = new Creator<HotelsModel>() {
        @Override
        public HotelsModel createFromParcel(Parcel in) {
            return new HotelsModel(in);
        }

        @Override
        public HotelsModel[] newArray(int size) {
            return new HotelsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getCountry_at() {
        return country_at;
    }

    public void setCountry_at(int country_at) {
        this.country_at = country_at;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChinese_food() {
        return chinese_food;
    }

    public void setChinese_food(String chinese_food) {
        this.chinese_food = chinese_food;
    }

    public String getFast_food() {
        return fast_food;
    }

    public void setFast_food(String fast_food) {
        this.fast_food = fast_food;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getSingle_room() {
        return single_room;
    }

    public void setSingle_room(String single_room) {
        this.single_room = single_room;
    }

    public String getDouble_room() {
        return double_room;
    }

    public void setDouble_room(String double_room) {
        this.double_room = double_room;
    }
}
