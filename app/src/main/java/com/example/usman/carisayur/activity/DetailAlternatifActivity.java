package com.example.usman.carisayur.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usman.carisayur.R;
import com.example.usman.carisayur.adapter.SayurListAdapter;
import com.example.usman.carisayur.model.lokasi;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailAlternatifActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private TextView nama, alamat, harga, jumlahSayur, jarak, valueRating;
    private ArrayList<String> listSayur = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SayurListAdapter mAdapter;
    private double lattiAwal, longiAwal;
    private String URL_POST, URL_SAYUR_POST;
    private String latti, longi;
    private RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alternatif);
        setTitle("Detail Alternatif / Toko");

        nama = findViewById(R.id.nama_detail_toko);
        alamat = findViewById(R.id.alamat_detail_toko);
        jarak = findViewById(R.id.alamat_detail_jarak);
        harga = findViewById(R.id.rata_harga_detail_toko);
        jumlahSayur = findViewById(R.id.jumlah_sayur_detail_toko);
        Button btnRute = findViewById(R.id.btn_rute);
        rating = findViewById(R.id.ratingD);
        valueRating = findViewById(R.id.valueRating);

        Bundle extras = getIntent().getExtras();
        lokasi Lokasi = new lokasi();
        lattiAwal = Lokasi.getLatitude();
        longiAwal = Lokasi.getLongitude();
        latti = "" + extras.getDouble("latti2");
        longi = "" + extras.getDouble("longi2");
        Log.d("id", ""+extras.getString("id"));
        URL_POST = "http://adinu21.000webhostapp.com/informasi_alernatif.php";
        URL_SAYUR_POST = "http://adinu21.000webhostapp.com/informasi_sayur.php";

        loadAlternatif();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+ lattiAwal +","+ longiAwal +"&daddr="+ latti +","+ longi));

        btnRute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }

   private void loadAlternatif(){
       StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject = new JSONObject(response);
                   JSONArray jsonArray = jsonObject.getJSONArray("informasi alternatif");
                   JSONObject json = jsonArray.getJSONObject(0);
                   nama.setText(json.getString("nama"));
                   valueRating.setText(json.getString("rating"));
                   rating.setRating(Float.valueOf(json.getString("rating")));
                   alamat.setText(json.getString("alamat"));
                   jarak.setText(json.getString("jarak")+" km");
                   harga.setText(json.getString("harga"));
                   jumlahSayur.setText(json.getString("jumlah_sayur")+" yaitu,");


               }catch (JSONException e){
                   e.printStackTrace();
                   Log.e("Error json",e.getMessage());
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(DetailAlternatifActivity.this, error+"", Toast.LENGTH_SHORT).show();
           }
       }
       ){
           @Override
           protected Map<String, String> getParams() {
               Map<String, String> params = new HashMap<String, String>();
               Bundle extras = getIntent().getExtras();
               Log.d("id", ""+extras.getString("id"));
               params.put("id", extras.getString("id"));
               params.put("latti", String.valueOf(lattiAwal));
               params.put("longi", String.valueOf(longiAwal));
               return params;
           }
       };
       RequestQueue requestQueue = Volley.newRequestQueue(this);
       requestQueue.add(stringRequest);

       StringRequest stringRequest2 = new StringRequest(Request.Method.POST, URL_SAYUR_POST, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject jsonObject = new JSONObject(response);
                   JSONArray jsonArray = jsonObject.getJSONArray("informasi sayur");
                   for (int a = 0; a < jsonArray.length(); a ++){
                       JSONObject json = jsonArray.getJSONObject(a);
                       listSayur.add( (json.getString("nama_sayur")));
                       // Get a handle to the RecyclerView.
                       mRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewS);
                       valueRating.setNestedScrollingEnabled(false);
                       // Create an adapter and supply the data to be displayed.
                       mAdapter = new SayurListAdapter(DetailAlternatifActivity.this, listSayur);
                       // Connect the adapter with the RecyclerView.
                       mRecyclerView.setAdapter(mAdapter);
//                         Give the RecyclerView a default layout manager.
                       mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                   }

               }catch (JSONException e){
                   e.printStackTrace();
                   Log.e("Error json",e.getMessage());
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(DetailAlternatifActivity.this, error+"", Toast.LENGTH_SHORT).show();
           }
       }
       ){
           @Override
           protected Map<String, String> getParams() {
               Map<String, String> params = new HashMap<String, String>();
               Bundle extras = getIntent().getExtras();
               Log.d("id", ""+extras.getString("id"));
               params.put("id", extras.getString("id"));
               return params;
           }
       };
       RequestQueue requestQueue2 = Volley.newRequestQueue(this);
       requestQueue2.add(stringRequest2);
   }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng lokasi = new LatLng(Double.valueOf(latti), Double.valueOf(longi));
        map.addMarker(new MarkerOptions().position(lokasi).title("lokasi"));
        float zoomLevel = 16.0f; //This goes up to 21
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi, zoomLevel));
    }
}
