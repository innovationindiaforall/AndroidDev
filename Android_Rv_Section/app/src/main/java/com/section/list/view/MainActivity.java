package com.section.list.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.section.list.R;
import com.section.list.adapter.FirstRVSectionAdapter;
import com.section.list.model.RVModel;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etSearchView;
    private FirstRVSectionAdapter adapter;
    private ArrayList<String> itemArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        showRV();
        addSearchFunction();

    }

    private String readStringDataFromAsset(Context mContext, String filename) {

        String valueJson = null;
        try {
            InputStream is = mContext.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer, 0, size);
            is.close();
            valueJson = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Log.v("section", "Error.................................:" + e.getMessage());
            return null;
        }
        return valueJson;
    }

    private void initialization() {
        etSearchView = findViewById(R.id.etSearchView);
        recyclerView = findViewById(R.id.sectioned_main_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void showRV() {

        String data = readStringDataFromAsset(this, "datafile");

        List<String> dataList= Arrays.asList(data.split("#"));

        ArrayList<RVModel> sectionModelArrayList = new ArrayList<>();
        itemArrayList = new ArrayList<>();
        for(int i=0; i<dataList.size(); i++){
            Log.v("section", "..............dataList:" + dataList.get(i));
            itemArrayList.add(dataList.get(i).trim());
        }
        sectionModelArrayList.add(new RVModel("Old Mobile Models", itemArrayList));


        String data1 = readStringDataFromAsset(this, "datafile1");
        List<String> data1List= Arrays.asList(data1.split("#"));

        itemArrayList = new ArrayList<>();
        for(int i=0; i<data1List.size(); i++){
            Log.v("section", "..............data1List:" + data1List.get(i));
            itemArrayList.add(data1List.get(i).trim());
        }
        sectionModelArrayList.add(new RVModel("New Mobile Models", itemArrayList));

        adapter = new FirstRVSectionAdapter(this, sectionModelArrayList);
        recyclerView.setAdapter(adapter);
    }

    private void addSearchFunction() {

        etSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
