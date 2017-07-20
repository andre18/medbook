package com.example.andre.medicalreferencebook.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.DiseaseEntity;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonDiseases, buttonMedications, buttonMedHerbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMedicationsData();
        initDiseasesData();
        initMedHerbsData();

        buttonDiseases = (Button) findViewById(R.id.activity_main_button_diseases);
        buttonMedications = (Button) findViewById(R.id.activity_main_button_medications);
        buttonMedHerbs = (Button) findViewById(R.id.activity_main_button_medherbs);

        buttonDiseases.setOnClickListener(this);
        buttonMedications.setOnClickListener(this);
        buttonMedHerbs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        switch (v.getId()) {
            case R.id.activity_main_button_medications:
                i = 1;
                break;
            case R.id.activity_main_button_diseases:
                i = 2;
                break;
            case R.id.activity_main_button_medherbs:
                i = 3;
                break;
        }
        startActivity(new Intent(MainActivity.this, ListActivity.class).putExtra(Constants.MAIN_ACTIVITY_BUTTON_KEY, i));
    }

    private void initMedicationsData() {
        List<MedicinesEntity> medicineEntityList = new ArrayList<>();

        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_1_title), getResources().getString(R.string.medicine_1_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_2_title), getResources().getString(R.string.medicine_2_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_3_title), getResources().getString(R.string.medicine_3_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_4_title), getResources().getString(R.string.medicine_4_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_5_title), getResources().getString(R.string.medicine_5_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_6_title), getResources().getString(R.string.medicine_6_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_7_title), getResources().getString(R.string.medicine_7_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_8_title), getResources().getString(R.string.medicine_8_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_9_title), getResources().getString(R.string.medicine_9_description)));
        medicineEntityList.add(new MedicinesEntity(getResources().getString(R.string.medicine_10_title), getResources().getString(R.string.medicine_10_description)));

        MyApplication.addAllMedicationsData(medicineEntityList);
    }

    private void initDiseasesData() {
        List<DiseaseEntity> diseaseEntityList = new ArrayList<>();

        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_1_title), getResources().getString(R.string.disease_1_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_2_title), getResources().getString(R.string.disease_2_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_3_title), getResources().getString(R.string.disease_3_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_4_title), getResources().getString(R.string.disease_4_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_5_title), getResources().getString(R.string.disease_5_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_6_title), getResources().getString(R.string.disease_6_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_7_title), getResources().getString(R.string.disease_7_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_8_title), getResources().getString(R.string.disease_8_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_9_title), getResources().getString(R.string.disease_9_description)));
        diseaseEntityList.add(new DiseaseEntity(getResources().getString(R.string.disease_10_title), getResources().getString(R.string.disease_10_description)));

        MyApplication.addAllDiseasesData(diseaseEntityList);
    }

    private void initMedHerbsData() {
        List<MedHerbsEntity> medHerbsEntityList = new ArrayList<>();

        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_1_name), getResources().getString(R.string.medherbs_1_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_2_name), getResources().getString(R.string.medherbs_2_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_3_name), getResources().getString(R.string.medherbs_3_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_4_name), getResources().getString(R.string.medherbs_4_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_5_name), getResources().getString(R.string.medherbs_5_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_6_name), getResources().getString(R.string.medherbs_6_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_7_name), getResources().getString(R.string.medherbs_7_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_8_name), getResources().getString(R.string.medherbs_8_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_9_name), getResources().getString(R.string.medherbs_9_description)));
        medHerbsEntityList.add(new MedHerbsEntity(getResources().getString(R.string.medherbs_10_name), getResources().getString(R.string.medherbs_10_description)));

        MyApplication.addAllMedHerbsDsta(medHerbsEntityList);
    }
}
