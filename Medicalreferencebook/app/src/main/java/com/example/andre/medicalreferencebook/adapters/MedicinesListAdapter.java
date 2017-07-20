package com.example.andre.medicalreferencebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;

import java.util.List;

/**
 * Created by Andre on 29.04.2017.
 */

public class MedicinesListAdapter extends BaseAdapter {

    private List<MedicinesEntity> medicinesEntityList;
    private Context context;

    public MedicinesListAdapter(List<MedicinesEntity> medicinesEntityList, Context context) {
        this.medicinesEntityList = medicinesEntityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return medicinesEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return medicinesEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder viewHolder;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            v = inflater.inflate(R.layout.list_activity_item, parent, false);

            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textViewTitle.setText(medicinesEntityList.get(position).getName());

        return v;
    }

    class ViewHolder {
        public TextView textViewTitle;

        public ViewHolder(View v) {
            this.textViewTitle = (TextView) v.findViewById(R.id.list_activity_item_text_view_title);
        }
    }
}
