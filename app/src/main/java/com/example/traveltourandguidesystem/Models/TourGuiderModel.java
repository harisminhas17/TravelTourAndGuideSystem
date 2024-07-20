package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TourGuiderModel implements Parcelable {
    int id;
    int city_id;
    String name;
    String phone_number;
    String experience;
    String about;
    int rating;
    String address;
    String image;
    String price;
    String languages;

    protected TourGuiderModel(Parcel in) {
        id = in.readInt();
        city_id = in.readInt();
        name = in.readString();
        phone_number = in.readString();
        experience = in.readString();
        about = in.readString();
        rating = in.readInt();
        address = in.readString();
        image = in.readString();
        price = in.readString();
        languages = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(city_id);
        dest.writeString(name);
        dest.writeString(phone_number);
        dest.writeString(experience);
        dest.writeString(about);
        dest.writeInt(rating);
        dest.writeString(address);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(languages);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}

