package com.example.usman.carisayur.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usman.carisayur.R;

import java.util.ArrayList;

public class SayurListAdapter extends RecyclerView.Adapter<SayurListAdapter.SayurListHolder>{
    private final ArrayList<String> mWordList;
    private LayoutInflater mInflater;

    @NonNull
    @Override
    public SayurListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_sayur, parent, false);
        return new SayurListAdapter.SayurListHolder(mItemView, this);
    }

    public SayurListAdapter(Context context, ArrayList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public void onBindViewHolder(@NonNull SayurListAdapter.SayurListHolder holder, int position) {
        String sayurCurrent = mWordList.get(position);
        holder.sayur.setText(sayurCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class SayurListHolder extends RecyclerView.ViewHolder {
        final SayurListAdapter mAdapter;
        public TextView sayur;
        public SayurListHolder(View itemView, SayurListAdapter adapter) {
            super(itemView);
            sayur = itemView.findViewById(R.id.nama_sayur);
            this.mAdapter = adapter;
        }
    }
}
