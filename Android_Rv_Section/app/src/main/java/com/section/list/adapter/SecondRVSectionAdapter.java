package com.section.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.section.list.R;

import java.util.ArrayList;

public class SecondRVSectionAdapter extends RecyclerView.Adapter<SecondRVSectionAdapter.SecondSectionViewHolder> {

    private Context context;
    private ArrayList<String> arrayList;

    public SecondRVSectionAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SecondSectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_custom_rv_section_layout, parent, false);
        return new SecondSectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecondSectionViewHolder holder, int position) {
        holder.itemLabel.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class SecondSectionViewHolder extends RecyclerView.ViewHolder {
        private TextView itemLabel;

        public SecondSectionViewHolder(View itemView) {
            super(itemView);
            itemLabel = (TextView) itemView.findViewById(R.id.item_label);
        }
    }
}
