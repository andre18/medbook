package com.example.andre.medicalreferencebook.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

/**
 * Created by Andre on 01.05.2017.
 */

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextTitle, editTextDescription;
    private Button buttonSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        editTextTitle = (EditText) findViewById(R.id.add_note_activity_edit_text_title);
        editTextDescription = (EditText) findViewById(R.id.add_note_activity_edit_text_description);

        buttonSave = (Button) findViewById(R.id.add_note_activity_button);

        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_note_activity_button:
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                if (!(title.isEmpty()) && !(description.isEmpty())) {
                    NoteEntity noteEntity = new NoteEntity();
                    noteEntity.setTitle(title);
                    noteEntity.setDescription(description);
                    MyApplication.addNote(noteEntity);
                    createdNoteToast(this);
                    onBackPressed();
                }
                break;
        }
    }

    private void createdNoteToast(Context context) {
        Toast.makeText(context, Constants.TOAST_CREATED_NOTE, Toast.LENGTH_LONG).show();
    }
}
