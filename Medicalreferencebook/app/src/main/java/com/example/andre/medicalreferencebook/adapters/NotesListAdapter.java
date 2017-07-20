package com.example.andre.medicalreferencebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.NoteEntity;

import java.util.List;

/**
 * Created by Andre on 01.05.2017.
 */

public class NotesListAdapter extends BaseAdapter {

    private List<NoteEntity> noteEntityList;

    private Context context;

    public NotesListAdapter(Context context, List<NoteEntity> noteEntityList) {
        this.context = context;
        this.noteEntityList = noteEntityList;
    }

    @Override
    public int getCount() {
        return noteEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HolderView holder;

        if(row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            row = inflater.inflate(R.layout.notes_activity_item, parent, false);

            holder = new HolderView(row);

            row.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

        holder.textTitle.setText(noteEntityList.get(position).getTitle());
        holder.textDescription.setText(noteEntityList.get(position).getDescription());

        return row;
    }

    class HolderView {
        TextView textTitle;
        TextView textDescription;

        public HolderView(View v) {
            this.textTitle = (TextView) v.findViewById(R.id.notes_activity_item_text_title);
            this.textDescription = (TextView) v.findViewById(R.id.notes_activity_item_text_description);
        }
    }
}
