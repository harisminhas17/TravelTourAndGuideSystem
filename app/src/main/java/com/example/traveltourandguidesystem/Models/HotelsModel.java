package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

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

    ArrayList<ImageModel> images;

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
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

    protected HotelsModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        city_id = in.readInt();
        country_at = in.readInt();
        province_id = in.readInt();
        latitude = in.readFloat();
        longitude = in.readFloat();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(city_id);
        dest.writeInt(country_at);
        dest.writeInt(province_id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
    }
}
