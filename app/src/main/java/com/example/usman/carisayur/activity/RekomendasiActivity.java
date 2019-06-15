package com.example.usman.carisayur.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usman.carisayur.R;
import com.example.usman.carisayur.adapter.RekomendasiAdapter;
import com.example.usman.carisayur.model.alternatif;
import com.example.usman.carisayur.model.bobot;
import com.example.usman.carisayur.model.lokasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RekomendasiActivity extends AppCompatActivity {

    private ArrayList<alternatif> alternatifListchecked = new ArrayList<>();
    private ArrayList<alternatif> alternatifList;

    private RecyclerView mRecyclerView;
    private RekomendasiAdapter mAdapter;

    private String URL_POST = "http://adinu21.000webhostapp.com/dinamik_topsis.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekomendasi);
        setTitle("Rekomendasi");
        alternatifList = new ArrayList<>();
        final Intent intent = new Intent(this, DetailAlternatifActivity.class);
        loadList(intent);
    }

    private void loadList(final Intent intent){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Isi Get Data","try");
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("ranking");
                    for (int a = 0; a < jsonArray.length(); a ++){
                        JSONObject json = jsonArray.getJSONObject(a);
                        alternatifList.add( new alternatif(json.getString("id"),
                                                json.getString("nama_toko"),
                                                Double.valueOf(json.getString("latitude")),
                                                Double.valueOf(json.getString("longitude")),
                                                json.getString("alamat"),
                                                json.getString("peringkat"),
                                                Float.valueOf(json.getString("rating"))));

                        // Get a handle to the RecyclerView.
                        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewRekomendasi);
                        // Create an adapter and supply the data to be displayed.
                        mAdapter = new RekomendasiAdapter(RekomendasiActivity.this, alternatifList, intent);
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
                Toast.makeText(RekomendasiActivity.this, error+"", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                alternatif alternatif = new alternatif();
                alternatifListchecked = alternatif.getTokoListchecked();
                Log.d("jumlah", ""+ alternatifListchecked.size());
                for (int a = 0; a < alternatifListchecked.size(); a ++){
                    final alternatif alternatifCurrent = alternatifListchecked.get(a);
                    params.put((a+1)+"", alternatifCurrent.toStringisSelected());
                }

                lokasi Lokasi = new lokasi();
                bobot Bobot = new bobot();

                params.put("latti", String.valueOf(Lokasi.getLatitude()));
                params.put("longi", String.valueOf(Lokasi.getLongitude()));
                params.put("value_jarak", String.valueOf(Bobot.getJarak()));
                params.put("value_harga", String.valueOf(Bobot.getHarga()));
                params.put("value_jenis_sayur", String.valueOf(Bobot.getJenis_sayur()));
                params.put("value_rating", String.valueOf(Bobot.getRating()));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy( 5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

}

