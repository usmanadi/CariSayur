package com.example.usman.carisayur.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usman.carisayur.R;
import com.example.usman.carisayur.model.alternatif;

import java.util.ArrayList;

public class RingkasanAdapter extends RecyclerView.Adapter<RingkasanAdapter.RingkasanHolder>{

    private final ArrayList<alternatif> mWordList;
    private LayoutInflater mInflater;

    @NonNull
    @Override
    public RingkasanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_ringkasan, parent, false);
        return new RingkasanAdapter.RingkasanHolder(mItemView, this);
    }

    public RingkasanAdapter(Context context, ArrayList<alternatif> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public void onBindViewHolder(@NonNull RingkasanAdapter.RingkasanHolder holder, int position) {
        final alternatif alternatifCurrent = mWordList.get(position);
        holder.alternatif.setText(alternatifCurrent.getNama());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class RingkasanHolder extends RecyclerView.ViewHolder {
        final RingkasanAdapter mAdapter;
        public TextView alternatif;
        public RingkasanHolder(View itemView, RingkasanAdapter adapter) {
            super(itemView);
            alternatif = itemView.findViewById(R.id.alternatifR);
            this.mAdapter = adapter;
        }
    }
}
