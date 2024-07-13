package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TourGuiderModel implements Parcelable {
    int id;
    String name;
    int city_id;
    int phone_number;
    int experience;
    String about;
    String image;
    String address;

    protected TourGuiderModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        city_id = in.readInt();
        phone_number = in.readInt();
        experience = in.readInt();
        about = in.readString();
        address = in.readString();
        image = in.readString();
    }

    public static final Creator<TourGuiderModel> CREATOR = new Creator<TourGuiderModel>() {
        @Override
        public TourGuiderModel createFromParcel(Parcel in) {
            return new TourGuiderModel(in);
        }

        @Override
        public TourGuiderModel[] newArray(int size) {
            return new TourGuiderModel[size];
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

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(city_id);
        dest.writeInt(phone_number);
        dest.writeInt(experience);
        dest.writeString(about);
        dest.writeString(address);
        dest.writeString(image);
    }
}
