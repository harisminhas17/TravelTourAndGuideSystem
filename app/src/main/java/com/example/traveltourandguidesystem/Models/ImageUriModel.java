package com.example.traveltourandguidesystem.Models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageUriModel implements Parcelable {
    Uri uri;

    public ImageUriModel() {
    }

    public ImageUriModel(Parcel in) {
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<ImageUriModel> CREATOR = new Creator<ImageUriModel>() {
        @Override
        public ImageUriModel createFromParcel(Parcel in) {
            return new ImageUriModel(in);
        }

        @Override
        public ImageUriModel[] newArray(int size) {
            return new ImageUriModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(uri, flags);
    }

    public Uri getUri() {
        return uri;
    }

    public ImageUriModel setUri(Uri uri) {
        this.uri = uri;
        return null;
    }
}
