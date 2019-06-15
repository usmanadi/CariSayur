package com.example.usman.carisayur.model;

public class bobot{

    private static float jarak;
    private static float harga;
    private static float jenis_sayur;
    private static float rating;

    public bobot(float jarak, float harga, float jenis_sayur, float rating){
        this.jarak = jarak;
        this.harga = harga;
        this.jenis_sayur = jenis_sayur;
        this.rating = rating;
    }

    public bobot(){}

    public static float getRating() {
        return rating;
    }

    public static void setRating(float rating) {
        bobot.rating = rating;
    }

    public float getJarak() {
        return jarak;
    }

    public void setJarak(float jarak) {
        this.jarak = jarak;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public float getJenis_sayur() {
        return jenis_sayur;
    }

    public void setJenis_sayur(float jenis_sayur) {
        this.jenis_sayur = jenis_sayur;
    }

}
