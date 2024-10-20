package com.example.bth2_5;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Dish> arrDish;
    public SpinnerAdapter(Context context, int layout, ArrayList<Dish> arrDish) {
        this.context = context;
        this.layout = layout;
        this.arrDish = arrDish;
    }
    @Override
    public int getCount() {
        return arrDish.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.dropdown, parent, false);
        TextView thbName = row.findViewById(R.id.thbnailText);
        ImageView flag = row.findViewById(R.id.thbnailImage);
        Drawable drawable = context.getResources().getDrawable(arrDish.get(position).getThumbnail());
        thbName.setText(arrDish.get(position).getName());
        flag.setImageDrawable(drawable);
        return row;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.dropdown, parent, false);
        TextView thbName = row.findViewById(R.id.thbnailText);
        thbName.setText(arrDish.get(position).getName());
        return row;
    }

}