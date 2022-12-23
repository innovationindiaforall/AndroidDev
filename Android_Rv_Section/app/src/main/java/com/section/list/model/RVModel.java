package com.section.list.model;

import java.util.ArrayList;

public class RVModel {
    private String sectionTitleLabel;
    private ArrayList<String> itemArrayList;

    public RVModel(String sectionLabel, ArrayList<String> itemArrayList) {
        this.sectionTitleLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
    }

    public String getSectionLabel() {
        return sectionTitleLabel;
    }

    public ArrayList<String> getItemArrayList() {
        return itemArrayList;
    }
}
