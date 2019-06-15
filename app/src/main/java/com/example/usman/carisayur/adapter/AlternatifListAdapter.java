package com.example.usman.carisayur.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.usman.carisayur.R;
import com.example.usman.carisayur.model.alternatif;

import java.util.ArrayList;

public class AlternatifListAdapter extends RecyclerView.Adapter<AlternatifListAdapter.AlternatifListHolder>{

    private ArrayList<alternatif> alternatifList;
    private LayoutInflater mInflater;
    private final Intent intent;
    private ArrayList<alternatif> alternatifListchecked = new ArrayList<>();

    @NonNull
    @Override
    public AlternatifListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.list_alternatif, parent, false);
        return new AlternatifListHolder(mItemView, this);
    }

    public AlternatifListAdapter(Context context, ArrayList<alternatif> alternatifList, Intent intent) {
        mInflater = LayoutInflater.from(context);
        this.intent = intent;
        this.alternatifList = alternatifList;
    }

    @Override
    public void onBindViewHolder(@NonNull final AlternatifListHolder holder, int position) {
        final alternatif alternatifCurrent = alternatifList.get(position);
        holder.alternatif.setText(alternatifCurrent.getNama());
        holder.idlist.setText(alternatifCurrent.getId_toko());
        intent.putExtra(alternatifCurrent.getId_toko(), "false");

        //in some cases, it will prevent unwanted situations
        holder.alternatif.setOnCheckedChangeListener(null);
        //if true, your checkbox will be selected, else unselected
        holder.alternatif.setChecked(alternatifCurrent.isSelected());
        holder.alternatif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    alternatifCurrent.setCountPlus();
                }else{
                    alternatifCurrent.setCountMin();
                }
                //set your object's last status
                alternatifCurrent.setSelected(isChecked);
                setListAlternatif(alternatifCurrent.getId_toko(), alternatifCurrent.getNama(), true);
                alternatifCurrent.setTokoListchecked(alternatifList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alternatifList.size();
    }

    public class AlternatifListHolder extends RecyclerView.ViewHolder {
        private final CheckBox alternatif;
        public final TextView idlist;
        final AlternatifListAdapter mAdapter;

        private AlternatifListHolder(View itemView, AlternatifListAdapter adapter) {
            super(itemView);
            alternatif = itemView.findViewById(R.id.checkbox_alternatif);
            idlist = itemView.findViewById(R.id.idlist);
            this.mAdapter = adapter;
        }
    }

    public void setListAlternatif(String idToko, String nama, boolean status){
        alternatifListchecked.add( new alternatif(idToko, nama, status));
    }

}
