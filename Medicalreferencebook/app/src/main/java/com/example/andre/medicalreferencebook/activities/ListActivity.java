package com.example.andre.medicalreferencebook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.adapters.DiseasesListAdapter;
import com.example.andre.medicalreferencebook.adapters.MedHerbsListAdapter;
import com.example.andre.medicalreferencebook.adapters.MedicinesListAdapter;
import com.example.andre.medicalreferencebook.entities.DiseaseEntity;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andre on 22.04.2017.
 */

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ListActivity.class.getSimpleName();

    private EditText editTextSearch;
    private Button buttonNotes;
    private ListView listView;

    private MedicinesListAdapter medicinesListAdapter;
    private DiseasesListAdapter diseasesListAdapter;
    private MedHerbsListAdapter medHerbsListAdapter;

    private List<MedicinesEntity> medicinesEntityList;
    private List<DiseaseEntity> diseaseEntityList;
    private List<MedHerbsEntity> medHerbsEntityList;

    private int current;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        editTextSearch = (EditText) findViewById(R.id.list_activity_edit_text_search);
        editTextSearch.addTextChangedListener(new TextSearch());
        buttonNotes = (Button) findViewById(R.id.list_activity_button_notes);
        listView = (ListView) findViewById(R.id.list_activity_list_view);

        buttonNotes.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        current = bundle.getInt(Constants.MAIN_ACTIVITY_BUTTON_KEY);
        switch (current) {
            case 1:
                medicinesEntityList = MyApplication.getAllMedicinesData();
                medicinesListAdapter = new MedicinesListAdapter(medicinesEntityList, this);
                listView.setAdapter(medicinesListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(ListActivity.this, DescriptionActivity.class)
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_POSITION_KEY, medicinesEntityList.get(position).getId())
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_LIST_KEY, current));
                    }
                });
                break;
            case 2:
                diseaseEntityList = MyApplication.getAllDiseasesData();
                diseasesListAdapter = new DiseasesListAdapter(diseaseEntityList, this);
                listView.setAdapter(diseasesListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(ListActivity.this, DescriptionActivity.class)
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_POSITION_KEY, diseaseEntityList.get(position).getId())
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_LIST_KEY, current));
                    }
                });
                break;
            case 3:
                medHerbsEntityList = MyApplication.getAllMedHerbsData();
                medHerbsListAdapter = new MedHerbsListAdapter(medHerbsEntityList, this);
                listView.setAdapter(medHerbsListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(ListActivity.this, DescriptionActivity.class)
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_POSITION_KEY, medHerbsEntityList.get(position).getId())
                                .putExtra(Constants.LIST_ACTIVITY_ITEM_LIST_KEY, current));
                    }
                });
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_activity_button_notes:
                startActivity(new Intent(ListActivity.this, NotesListActivity.class));
                break;
        }
    }

    private class TextSearch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.v(TAG, "-------> onTextChanged()");
            String str = s.toString();

            if (current == 1) {
                medicinesEntityList = MyApplication.searchMedicineEntity(str);
                listView.setAdapter(new MedicinesListAdapter(medicinesEntityList, ListActivity.this));
            }

            if (current == 2) {
                Log.v(TAG, "-------> onTextChanged() in if");
                diseaseEntityList = MyApplication.searchDiseaseEntity(str);
                listView.setAdapter(new DiseasesListAdapter(diseaseEntityList, ListActivity.this));
            }

            if (current == 3) {
                medHerbsEntityList = MyApplication.searchMedHerbEntity(str);
                listView.setAdapter(new MedHerbsListAdapter(medHerbsEntityList, ListActivity.this));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.v(TAG, "-------> afterTextChanged()");
        }
    }
}
