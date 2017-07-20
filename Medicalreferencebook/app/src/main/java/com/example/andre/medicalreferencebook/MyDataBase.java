package com.example.andre.medicalreferencebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andre.medicalreferencebook.entities.DiseaseEntity;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre on 22.04.2017.
 */

public class MyDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDB";
    private static final int VERSION_DATABASE = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";

    private static final String DISEASES_TABLE_NAME = "Diseases";
    private static final String MEDICATIONS_TABLE_NAME = "Medications";
    private static final String MEDHERBS_TABLE_NAME = "MedHerbs";
    private static final String NOTES_TABLE_NAME = "Notes";

    private static final String CREATE_DATABASE_TABLE_DISEASES = "CREATE TABLE " + DISEASES_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
            + KEY_DESCRIPTION + " TEXT" + ")";

    private static final String CREATE_DATABASE_TABLE_MEDICATIONS = "CREATE TABLE " + MEDICATIONS_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
            + KEY_DESCRIPTION + " TEXT" + ")";

    private static final String CREATE_DATABASE_TABLE_MEDHERBS = "CREATE TABLE " + MEDHERBS_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
            + KEY_DESCRIPTION + " TEXT" + ")";

    private static final String CREATE_DATABASE_TABLE_NOTES = "CREATE TABLE " + NOTES_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
            + KEY_DESCRIPTION + " TEXT" + ")";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE_TABLE_DISEASES);
        db.execSQL(CREATE_DATABASE_TABLE_MEDICATIONS);
        db.execSQL(CREATE_DATABASE_TABLE_MEDHERBS);
        db.execSQL(CREATE_DATABASE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDiseasesList(List<DiseaseEntity> diseaseEntityList) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DISEASES_TABLE_NAME, null);

        if (cursor.getCount() == 0) {
            for (DiseaseEntity de : diseaseEntityList) {
                ContentValues cv = new ContentValues();
                cv.put(KEY_NAME, de.getName());
                cv.put(KEY_DESCRIPTION, de.getDescription());
                db.insert(DISEASES_TABLE_NAME, null, cv);
            }
        }

        db.close();
    }

    public void addMedHerbsList(List<MedHerbsEntity> medHerbsEntities) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDHERBS_TABLE_NAME, null);

        int i = cursor.getCount();
        if (i == 0) {
            for (MedHerbsEntity mhe : medHerbsEntities) {
                ContentValues cv = new ContentValues();
                cv.put(KEY_NAME, mhe.getName());
                cv.put(KEY_DESCRIPTION, mhe.getDescription());
                db.insert(MEDHERBS_TABLE_NAME, null, cv);
            }
        }

        db.close();
    }

    public void addMedicationsList(List<MedicinesEntity> medicinesEntityList) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICATIONS_TABLE_NAME, null);

        if (cursor.getCount() == 0) {
            for (MedicinesEntity me : medicinesEntityList) {
                ContentValues cv = new ContentValues();
                cv.put(KEY_NAME, me.getName());
                cv.put(KEY_DESCRIPTION, me.getDescription());
                db.insert(MEDICATIONS_TABLE_NAME, null, cv);
            }
        }

        db.close();
    }

    public List<MedicinesEntity> getMedicinesData() {
        List<MedicinesEntity> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICATIONS_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                MedicinesEntity me = new MedicinesEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(me);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<DiseaseEntity> getDiseasesList() {
        List<DiseaseEntity> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DISEASES_TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                DiseaseEntity entity = new DiseaseEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<MedHerbsEntity> getMedHerbsList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDHERBS_TABLE_NAME, null);
        List<MedHerbsEntity> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                MedHerbsEntity mhe = new MedHerbsEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(mhe);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<MedicinesEntity> searchMedicineEntity(String str) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDICATIONS_TABLE_NAME +
                " WHERE " + KEY_NAME + " LIKE ?", new String[]{"%" + str + "%"});
        List<MedicinesEntity> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                MedicinesEntity me = new MedicinesEntity(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2));
                list.add(me);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public List<DiseaseEntity> searchDiseaseEntity(String str) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DiseaseEntity> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DISEASES_TABLE_NAME + " WHERE " + KEY_NAME + " LIKE ?", new String[]{"%" + str + "%"});

        if (cursor.moveToFirst()) {
            do {
                DiseaseEntity de = new DiseaseEntity();
                de.setId(cursor.getInt(0));
                de.setName(cursor.getString(1));
                de.setDescription(cursor.getString(2));
                list.add(de);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public List<MedHerbsEntity> searchMedHerbEntity(String str) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MEDHERBS_TABLE_NAME + " WHERE " + KEY_NAME + " LIKE ?", new String[]{"%" + str + "%"});
        List<MedHerbsEntity> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                MedHerbsEntity mhe = new MedHerbsEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                list.add(mhe);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public void addNote(NoteEntity noteEntity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, noteEntity.getTitle());
        cv.put(KEY_DESCRIPTION, noteEntity.getDescription());

        db.insert(NOTES_TABLE_NAME, null, cv);
        db.close();
    }

    public int updateNote(NoteEntity noteEntity) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, noteEntity.getTitle());
        cv.put(KEY_DESCRIPTION, noteEntity.getDescription());

        return db.update(NOTES_TABLE_NAME, cv, KEY_ID + " = ?", new String[]{String.valueOf(noteEntity.getId())});

    }

    public List<NoteEntity> getNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NOTES_TABLE_NAME, null);
        List<NoteEntity> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                NoteEntity entity = new NoteEntity();
                entity.setId(cursor.getInt(0));
                entity.setTitle(cursor.getString(1));
                entity.setDescription(cursor.getString(2));
                list.add(entity);
            } while (cursor.moveToNext());
        }

        return list;
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES_TABLE_NAME, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
