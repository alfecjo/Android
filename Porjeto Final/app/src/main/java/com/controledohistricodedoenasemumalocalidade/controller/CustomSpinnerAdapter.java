package com.controledohistricodedoenasemumalocalidade.controller;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.controledohistricodedoenasemumalocalidade.R;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, List<String> items) {
        super(context, android.R.layout.simple_spinner_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.purple_200));

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.purple_200));

        return view;
    }
}
