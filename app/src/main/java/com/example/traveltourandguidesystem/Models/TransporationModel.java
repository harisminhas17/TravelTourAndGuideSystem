package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TransporationModel implements Parcelable {
    int id;
    String vehicle_name;
    int city_id;
    String vehicle_type;
    String vehicle_color;
    String vehicle_plate_number;
    String vehicle_model;
    String vehicle_image;

    protected TransporationModel(Parcel in) {
        id = in.readInt();
        vehicle_name = in.readString();
        city_id = in.readInt();
        vehicle_type = in.readString();
        vehicle_color = in.readString();
        vehicle_plate_number = in.readString();
        vehicle_model = in.readString();
        vehicle_image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(vehicle_name);
        dest.writeInt(city_id);
        dest.writeString(vehicle_type);
        dest.writeString(vehicle_color);
        dest.writeString(vehicle_plate_number);
        dest.writeString(vehicle_model);
        dest.writeString(vehicle_image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TransporationModel> CREATOR = new Creator<TransporationModel>() {
        @Override
        public TransporationModel createFromParcel(Parcel in) {
            return new TransporationModel(in);
        }

        @Override
        public TransporationModel[] newArray(int size) {
            return new TransporationModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }

    public String getVehicle_plate_number() {
        return vehicle_plate_number;
    }

    public void setVehicle_plate_number(String vehicle_plate_number) {
        this.vehicle_plate_number = vehicle_plate_number;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_image() {
        return vehicle_image;
    }

    public void setVehicle_image(String vehicle_image) {
        this.vehicle_image = vehicle_image;
    }
}

