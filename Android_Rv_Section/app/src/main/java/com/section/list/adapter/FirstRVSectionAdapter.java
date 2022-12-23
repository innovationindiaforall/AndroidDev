package com.section.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.section.list.R;
import com.section.list.model.RVModel;

import java.util.ArrayList;


public class FirstRVSectionAdapter extends RecyclerView.Adapter<FirstRVSectionAdapter.FirstSectionViewHolder>
        implements Filterable {

    private Context context;
    private ArrayList<com.section.list.model.RVModel> sectionModelArrayList;
    private ArrayList<RVModel> filterList;
    private CustomFilterForSearch filter;

    public FirstRVSectionAdapter(Context context, ArrayList<RVModel> sectionModelArrayList) {
        this.context = context;
        this.sectionModelArrayList = sectionModelArrayList;
        this.filterList = sectionModelArrayList;
    }

    @Override
    public FirstSectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_custom_rv_section_layout, parent, false);
        return new FirstSectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FirstSectionViewHolder holder, int position) {
        final RVModel sectionModel = sectionModelArrayList.get(position);
        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler com.section.list.view for items
        holder.secondRecyclerView.setHasFixedSize(true);
        holder.secondRecyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.secondRecyclerView.setLayoutManager(linearLayoutManager);

        SecondRVSectionAdapter adapter = new SecondRVSectionAdapter(context, sectionModel.getItemArrayList());
        holder.secondRecyclerView.setAdapter(adapter);

    }

    class FirstSectionViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionLabel;
        private RecyclerView secondRecyclerView;

        public FirstSectionViewHolder(View itemView) {
            super(itemView);
            sectionLabel = (TextView) itemView.findViewById(R.id.section_label);
            secondRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler_view);
        }
    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilterForSearch();
        }
        return filter;
    }
    //----------------------------------------------------------------------------------------------
    //INNER CLASS
    class CustomFilterForSearch extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                constraint = constraint.toString().toUpperCase();
                ArrayList<RVModel> filters = new ArrayList<RVModel>();

                //get specific items
                for (int i = 0; i < filterList.size(); i++) {
                    String name = "", addr = "";
                    for (int j = 0; j < filterList.get(i).getItemArrayList().size(); j++) {
                        addr = filterList.get(i).getItemArrayList().get(j);
                        ArrayList<String> itemArrayList = new ArrayList<>();
                        itemArrayList.add(addr);
                        if (addr.toUpperCase().contains(constraint)) {
                            RVModel p = new RVModel(name, itemArrayList);
                            filters.add(p);
                        }
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            sectionModelArrayList = (ArrayList<RVModel>) results.values;
            notifyDataSetChanged();
        }
    }
    //----------------------------------------------------------------------------------------------
}
