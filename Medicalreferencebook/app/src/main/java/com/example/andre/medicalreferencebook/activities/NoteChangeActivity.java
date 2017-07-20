package com.example.andre.medicalreferencebook.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andre.medicalreferencebook.Constants;
import com.example.andre.medicalreferencebook.MyApplication;
import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

import java.util.List;

/**
 * Created by Andre on 01.05.2017.
 */

public class NoteChangeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textTitle, textDescription;
    private Button buttonSave, buttonDelete;

    private List<NoteEntity> noteEntityList;
    private int currentPosition, currentId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_change_activity);

        noteEntityList = MyApplication.getNotes();

        Bundle bundle = getIntent().getExtras();
        currentPosition = bundle.getInt(Constants.NOTES_LIST_ACTIVITY_KEY_CURRENT_POSITION);
        currentId = bundle.getInt(Constants.NOTES_LIST_ACTIVITY_KEY_CURRENT_ID);

        textTitle = (EditText) findViewById(R.id.note_change_activity_edit_text_title);
        textDescription = (EditText) findViewById(R.id.note_change_activity_edit_text_description);
        buttonSave = (Button) findViewById(R.id.note_change_activity_save);
        buttonDelete = (Button) findViewById(R.id.note_change_activity_remove);

        textTitle.setText(noteEntityList.get(currentPosition).getTitle());
        textDescription.setText(noteEntityList.get(currentPosition).getDescription());

        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.note_change_activity_save:
                String title = textTitle.getText().toString();
                String description = textDescription.getText().toString();
                if (!(title.isEmpty()) && !(description.isEmpty())) {
                    NoteEntity noteEntity = new NoteEntity();
                    noteEntity.setId(currentId);
                    noteEntity.setTitle(title);
                    noteEntity.setDescription(description);
                    int i = MyApplication.updateNote(noteEntity);
                    if (i == 1) {
                        updatedNoteToast(this);
                    } else {
                        failUpdateNoteToast(this);
                    }
                }
                break;
            case R.id.note_change_activity_remove:
                MyApplication.deleteNote(currentId);
                onBackPressed();
                break;
        }
    }

    private void updatedNoteToast(Context context) {
        Toast.makeText(context, Constants.TOAST_UPDATED_NOTE, Toast.LENGTH_LONG).show();
    }

    private void failUpdateNoteToast(Context context) {
        Toast.makeText(context, Constants.TOAST_FAIL_UPDATE_NOTE, Toast.LENGTH_LONG).show();
    }
}
