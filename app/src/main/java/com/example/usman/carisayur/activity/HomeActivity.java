package com.example.usman.carisayur.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.usman.carisayur.R;

public class HomeActivity extends AppCompatActivity {
    private Button btnCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnCari = findViewById(R.id.btnCari);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cekOnline()){
                    checkGpsEnabled(getApplicationContext());
                }
            }
        });

    }

    public void launchInputBobot() {
        Log.d("launch activity", "Button clicked!");

        Intent intent = new Intent(this, BobotActivity.class);
        startActivity(intent);
    }

    private void checkGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ignored) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ignored) {
        }

        if (!gps_enabled || !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("gps network disable");
            dialog.setPositiveButton(("open location setting"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    //get gps
                }
            });
            dialog.setNegativeButton(("cancel"), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }else{
            launchInputBobot();
        }
    }

    public boolean isOnline(){
        ConnectivityManager cm =(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public boolean  cekOnline(){
        if (isOnline()) {
            return true;
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("No Internet Connection");
                alertDialog.setMessage("You need to have Mobile Data or Wifi to access this. Press ok to exit");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.show();
            } catch (Exception ignored) {

            }
            return false;
        }
    }
}
