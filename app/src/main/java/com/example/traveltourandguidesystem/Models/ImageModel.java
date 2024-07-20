package com.example.traveltourandguidesystem.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel implements Parcelable {
    int id;
    String image_name;


    public ImageModel(String image_name) {
        this.image_name = image_name;
    }

    public ImageModel(Parcel in) {
        id = in.readInt();
        image_name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image_name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
