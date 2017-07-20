package com.example.andre.medicalreferencebook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.adapters.NotesListAdapter;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Andre on 30.04.2017.
 */

public class NotesListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = NotesListActivity.class.getSimpleName();

    private FloatingActionButton fab;
    private Button buttonSortName, buttonSortDate;
    private ListView listView;

    private List<NoteEntity> noteEntityList;

    private NotesListAdapter notesListAdapter;

    private Comparator<NoteEntity> comparatorNotesDate = new Comparator<NoteEntity>() {
        @Override
        public int compare(NoteEntity o1, NoteEntity o2) {
            return ((Integer) o1.getId()).compareTo(o2.getId());
        }
    };

    private Comparator<NoteEntity> comparatorNotesName = new Comparator<NoteEntity>() {
        @Override
        public int compare(NoteEntity o1, NoteEntity o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        noteEntityList = MyApplication.getNotes();

        buttonSortName = (Button) findViewById(R.id.notes_activity_button_sort_name);
        buttonSortDate = (Button) findViewById(R.id.notes_activity_button_sort_date);
        fab = (FloatingActionButton) findViewById(R.id.notes_activity_floating_button);

        listView = (ListView) findViewById(R.id.notes_activity_list_view);
        notesListAdapter = new NotesListAdapter(this, noteEntityList);
        listView.setAdapter(notesListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(NotesListActivity.this, NoteChangeActivity.class)
                        .putExtra(Constants.NOTES_LIST_ACTIVITY_KEY_CURRENT_POSITION, position)
                        .putExtra(Constants.NOTES_LIST_ACTIVITY_KEY_CURRENT_ID, noteEntityList.get(position).getId()));
            }
        });

        buttonSortName.setOnClickListener(this);
        buttonSortDate.setOnClickListener(this);
        fab.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "---------> onRestart()");
        noteEntityList = MyApplication.getNotes();
        notesListAdapter = new NotesListAdapter(this, noteEntityList);
        listView.setAdapter(notesListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notes_activity_button_sort_name:
                Collections.sort(noteEntityList, comparatorNotesName);
                notesListAdapter.notifyDataSetChanged();
                break;
            case R.id.notes_activity_button_sort_date:
                Collections.sort(noteEntityList, comparatorNotesDate);
                notesListAdapter.notifyDataSetChanged();
                break;
            case R.id.notes_activity_floating_button:
                Snackbar.make(v, "Добавить заметку", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(NotesListActivity.this, AddNoteActivity.class));
                break;
        }
    }
}
