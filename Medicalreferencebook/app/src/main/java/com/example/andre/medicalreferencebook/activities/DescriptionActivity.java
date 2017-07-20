package com.example.andre.medicalreferencebook.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.DiseaseEntity;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;

import java.util.List;

/**
 * Created by Andre on 30.04.2017.
 */

public class DescriptionActivity extends AppCompatActivity {

    private WebView webView;

    private List<MedicinesEntity> medicinesEntityList;
    private List<DiseaseEntity> diseaseEntityList;
    private List<MedHerbsEntity> medHerbsEntityList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_entity);

        webView = (WebView) findViewById(R.id.description_activity_web_view);

        Bundle bundle = getIntent().getExtras();
        int currentList = bundle.getInt(Constants.LIST_ACTIVITY_ITEM_LIST_KEY);
        int currentDesc = bundle.getInt(Constants.LIST_ACTIVITY_ITEM_POSITION_KEY);

        switch (currentList) {
            case 1:
                medicinesEntityList = MyApplication.getAllMedicinesData();
                webView.loadData(medicinesEntityList.get(currentDesc - 1).getDescription(), "text/html; charset=UTF-8", null);
                break;
            case 2:
                diseaseEntityList = MyApplication.getAllDiseasesData();
                webView.loadData(diseaseEntityList.get(currentDesc - 1).getDescription(), "text/html; charset=UTF-8", null);
                break;
            case 3:
                medHerbsEntityList = MyApplication.getAllMedHerbsData();
                webView.loadData(medHerbsEntityList.get(currentDesc - 1).getDescription(), "text/html; charset=UTF-8", null);
                break;
        }
    }
}
