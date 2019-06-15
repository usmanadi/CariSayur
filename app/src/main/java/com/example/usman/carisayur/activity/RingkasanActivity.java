package com.example.usman.carisayur.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usman.carisayur.R;
import com.example.usman.carisayur.adapter.RingkasanAdapter;
import com.example.usman.carisayur.model.bobot;
import com.example.usman.carisayur.model.lokasi;
import com.example.usman.carisayur.model.alternatif;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class RingkasanActivity extends AppCompatActivity {

    private TextView bobot1, bobot2, bobot3, bobot4;
    private Button btnStart;
    private ArrayList<alternatif> alternatifListchecked = new ArrayList<>();
    private static final String[] permisi = {
            android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringkasan);
        setTitle("Ringkasan");
        bobot1 = findViewById(R.id.value1);
        bobot2 = findViewById(R.id.value2);
        bobot3 = findViewById(R.id.value3);
        bobot4 = findViewById(R.id.value4);
        btnStart = findViewById(R.id.btn_start);
        final Intent intent = new Intent(this, RekomendasiActivity.class);

        getBobot();
        getListAlternatif();
        getLocation();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permisi, 1);
        } else {
            FusedLocationProviderClient mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            try {

                final Task location = mfusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            double latti = currentLocation.getLatitude();
                            double longi = currentLocation.getLongitude();
                            lokasi Lokasi = new lokasi(latti,longi);

                        } else {
                            Log.d("gagal", "onComplete : current location is null");
                            Toast.makeText(getApplicationContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (SecurityException sqe) {
                Log.e("gagal", "Security exception " + sqe.getMessage());
            }
        }
    }

    public void getListAlternatif(){
        alternatif alternatif = new alternatif();
        alternatifListchecked = alternatif.getTokoListchecked();

        ArrayList<alternatif> alternatifList = new ArrayList<>();
        for (int a = 0; a < alternatifListchecked.size(); a ++){
            final alternatif alternatifCurrent = alternatifListchecked.get(a);
            if (alternatifCurrent.isSelected()){
                alternatifList.add(alternatifCurrent);
            }
        }

        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecor.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));

        RecyclerView mRecyclerView = findViewById(R.id.recyclerviewR);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Create an adapter and supply the data to be displayed.
        RingkasanAdapter mAdapter = new RingkasanAdapter(getApplicationContext(), alternatifList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.addItemDecoration(itemDecor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void getBobot(){
        bobot Bobot = new bobot();
        bobot1.setText(""+Bobot.getJarak());
        bobot2.setText(""+Bobot.getHarga());
        bobot3.setText(""+Bobot.getJenis_sayur());
        bobot4.setText(""+Bobot.getRating());
    }

}
