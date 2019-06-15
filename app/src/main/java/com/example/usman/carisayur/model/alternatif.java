package com.example.usman.carisayur.model;

import java.util.ArrayList;

public class alternatif{

    private String id_toko, nama, alamat, peringkat;
    private float  rating;
    private double lat, longi;
    private boolean status;

    private static int count = 0;
    private static ArrayList<alternatif> alternatifListchecked = new ArrayList<>();

    public alternatif(String id_toko, String nama, double lat, double longi, String alamat, String peringkat, float rating){
        this.id_toko = id_toko;
        this.nama = nama;
        this.lat = lat;
        this.longi = longi;
        this.alamat = alamat;
        this.peringkat = peringkat;
        this.rating = rating;
    }

    public alternatif(){}

    public alternatif(String id_toko, String nama){
        this.id_toko = id_toko;
        this.nama = nama;
    }

    public alternatif(String id_toko, String nama, Boolean status){
        this.id_toko = id_toko;
        this.nama = nama;
        this.status = status;
    }

    public static int getCount() {
        return count;
    }

    public static void setCountMin() {
        count--;
    }

    public static void setCountPlus() {
        count++;
    }

    public String getId_toko() {
        return id_toko;
    }

    public String getNama() {
        return nama;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }



    public boolean isSelected() {
        return status;
    }

    public String toStringisSelected(){
        if (isSelected()){
            return "true";
        }else
            return "false";
    }

    public void setSelected(boolean status) {
        this.status = status;
    }

    public ArrayList<alternatif> getTokoListchecked() {
        return alternatifListchecked;
    }

    public void setTokoListchecked(ArrayList<alternatif> alternatifListchecked) {
        this.alternatifListchecked = alternatifListchecked;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public float getRating() {
        return rating;
    }
}
