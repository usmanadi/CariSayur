package com.example.usman.carisayur.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usman.carisayur.R;
import com.example.usman.carisayur.adapter.AlternatifListAdapter;
import com.example.usman.carisayur.model.alternatif;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlternatifActivity extends AppCompatActivity {

    private ArrayList<alternatif> alternatifList;
    private RecyclerView mRecyclerView;
    private AlternatifListAdapter mAdapter;
    private Intent intent;
    private Button btnCariRekomendasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_alternative);
        setTitle("Alternatif / Toko");
        intent = new Intent(this, RingkasanActivity.class);

        getAlternatif();

        btnCariRekomendasi = findViewById(R.id.btn_cari_rekomendasi);
        final alternatif al = new alternatif();
        btnCariRekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (al.getCount() > 1) {
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "Centang lebih dari 1 checkbox", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAlternatif() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://adinu21.000webhostapp.com/read_list_alternatif.php";

        //initializing the productlist
        alternatifList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Isi Get Data", "try");
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("alternatif");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        //adding some items to our list
                        alternatifList.add(new alternatif(json.getString("id"), json.getString("nama")));

                        // Get a handle to the RecyclerView.
                        mRecyclerView = findViewById(R.id.recyclerview);
                        // Create an adapter and supply the data to be displayed.
                        mAdapter = new AlternatifListAdapter(getApplicationContext(), alternatifList, intent);
                        // Connect the adapter with the RecyclerView.
                        mRecyclerView.setAdapter(mAdapter);
                        // Give the RecyclerView a default layout manager.
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Error json", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error valley", error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }

}
