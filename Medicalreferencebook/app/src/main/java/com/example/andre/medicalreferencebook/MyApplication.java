package com.example.andre.medicalreferencebook;

import android.app.Application;

import com.example.andre.medicalreferencebook.entities.DiseaseEntity;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

import java.util.List;

/**
 * Created by Andre on 26.04.2017.
 */

public class MyApplication extends Application {

    private static MyDataBase myDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        myDataBase = new MyDataBase(this);
    }

    public static void addAllDiseasesData(List<DiseaseEntity> list) {
        myDataBase.addDiseasesList(list);
    }

    public static void addAllMedHerbsDsta(List<MedHerbsEntity> list) {
        myDataBase.addMedHerbsList(list);
    }

    public static void addAllMedicationsData(List<MedicinesEntity> list) {
        myDataBase.addMedicationsList(list);
    }

    public static List<MedicinesEntity> getAllMedicinesData() {
        return myDataBase.getMedicinesData();
    }

    public static List<DiseaseEntity> getAllDiseasesData() {
        return myDataBase.getDiseasesList();
    }

    public static List<MedHerbsEntity> getAllMedHerbsData() {
        return myDataBase.getMedHerbsList();
    }

    public static List<MedicinesEntity> searchMedicineEntity(String s) {
        return myDataBase.searchMedicineEntity(s);
    }

    public static List<DiseaseEntity> searchDiseaseEntity(String s) {
        return myDataBase.searchDiseaseEntity(s);
    }

    public static List<MedHerbsEntity> searchMedHerbEntity(String s) {
        return myDataBase.searchMedHerbEntity(s);
    }

    public static void addNote(NoteEntity entity) {
        myDataBase.addNote(entity);
    }

    public static int updateNote(NoteEntity entity) {
        return myDataBase.updateNote(entity);
    }

    public static void deleteNote(int id) {
        myDataBase.deleteNote(id);
    }

    public static List<NoteEntity> getNotes() {
        return myDataBase.getNotes();
    }

}
