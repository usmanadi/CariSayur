package com.example.usman.carisayur.model;

public class lokasi {

    private static double latitude;
    private static double longitude;

    public lokasi(){}

    public lokasi(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        lokasi.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        lokasi.longitude = longitude;
    }
}
