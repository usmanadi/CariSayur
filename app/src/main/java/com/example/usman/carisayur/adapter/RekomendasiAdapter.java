package com.example.usman.carisayur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.usman.carisayur.R;
import com.example.usman.carisayur.model.alternatif;

import java.util.ArrayList;

public class RekomendasiAdapter extends RecyclerView.Adapter<RekomendasiAdapter.RekomendasiListHolder>{

    private LayoutInflater mInflater;
    private ArrayList<alternatif> alternatifList;

    private Context con;
    private Intent intent;

    @NonNull
    @Override
    public RekomendasiListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_rekomendasi, parent, false);
        return new RekomendasiListHolder(mItemView, this);
    }

    public RekomendasiAdapter(Context context, ArrayList<alternatif> alternatifList, Intent intent) {
        mInflater = LayoutInflater.from(context);
        this.con = context;
        this.intent = intent;
        this.alternatifList = alternatifList;
    }

    @Override
    public void onBindViewHolder(@NonNull final RekomendasiListHolder holder, final int position) {
        final alternatif alternatifCurrent = alternatifList.get(position);
        holder.namaToko.setText(alternatifCurrent.getNama());
        holder.alamatToko.setText(alternatifCurrent.getAlamat());
        holder.peringkat.setText(alternatifCurrent.getPeringkat());
        holder.vRating.setText(""+ alternatifCurrent.getRating());
        holder.rating.setRating(alternatifCurrent.getRating());
        holder.cv_rekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", alternatifCurrent.getId_toko());
                intent.putExtra("latti2", alternatifCurrent.getLat());
                intent.putExtra("longi2", alternatifCurrent.getLongi());
                Log.d("lat", ""+ alternatifCurrent.getId_toko());
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alternatifList.size();
    }

    public class RekomendasiListHolder extends RecyclerView.ViewHolder {
        public final CardView cv_rekomendasi;
        private final TextView namaToko;
        private final TextView alamatToko;
        private final TextView peringkat;
        private final TextView vRating;
        private final RatingBar rating;
        final RekomendasiAdapter mAdapter;

        private RekomendasiListHolder(View mItemView, RekomendasiAdapter rekomendasiAdapter) {
            super(mItemView);
            cv_rekomendasi = mItemView.findViewById(R.id.cv_rekomendasi);
            namaToko = mItemView.findViewById(R.id.nama_toko);
            alamatToko = mItemView.findViewById(R.id.alamat_toko);
            peringkat = mItemView.findViewById(R.id.angka);
            vRating = mItemView.findViewById(R.id.ratingV);
            rating = mItemView.findViewById(R.id.rating);
            this.mAdapter = rekomendasiAdapter;
        }
    }
}
