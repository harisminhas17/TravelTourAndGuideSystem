package com.example.traveltourandguidesystem.Maps;

public class CurrentLocation {
    private static double lat;
    private static double lng;

    public static double getLat() {
        return lat;
    }

    public static void setLat(double lat) {
        CurrentLocation.lat = lat;
    }

    public static double getLng() {
        return lng;
    }

    public static void setLng(double lng) {
        CurrentLocation.lng = lng;
    }
}
