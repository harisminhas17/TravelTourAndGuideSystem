package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PlacesModel implements Parcelable  {
    int id;
    String name;
    int city_id;
    int country_id;
    int province_id;
    float latitude;
    float longitude;
    String address;
    String description;
    ArrayList <ImageModel> images;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected PlacesModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        city_id = in.readInt();
        country_id = in.readInt();
        province_id = in.readInt();
        latitude = in.readFloat();
        longitude = in.readFloat();
        address = in.readString();
        description = in.readString();
        images = in.createTypedArrayList(ImageModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(city_id);
        dest.writeInt(country_id);
        dest.writeInt(province_id);
        dest.writeFloat(latitude);
        dest.writeFloat(longitude);
        dest.writeString(address);
        dest.writeString(description);
        dest.writeTypedList(images);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlacesModel> CREATOR = new Creator<PlacesModel>() {
        @Override
        public PlacesModel createFromParcel(Parcel in) {
            return new PlacesModel(in);
        }

        @Override
        public PlacesModel[] newArray(int size) {
            return new PlacesModel[size];
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

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }
}
