package com.example.andre.medicalreferencebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andre.medicalreferencebook.R;
import com.example.andre.medicalreferencebook.entities.MedHerbsEntity;
import com.example.andre.medicalreferencebook.entities.MedicinesEntity;

import java.util.List;

/**
 * Created by Andre on 29.04.2017.
 */

public class MedHerbsListAdapter extends BaseAdapter {

    private List<MedHerbsEntity> medHerbsEntityList;
    private Context context;

    public MedHerbsListAdapter(List<MedHerbsEntity> medHerbsEntityList, Context context) {
        this.medHerbsEntityList = medHerbsEntityList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return medHerbsEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return medHerbsEntityList.get(position);
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

        viewHolder.textViewTitle.setText(medHerbsEntityList.get(position).getName());

        return v;
    }

    class ViewHolder {
        public TextView textViewTitle;

        public ViewHolder(View v) {
            this.textViewTitle = (TextView) v.findViewById(R.id.list_activity_item_text_view_title);
        }
    }
}
