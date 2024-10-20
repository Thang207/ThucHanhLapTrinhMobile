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

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context=null;
    private int layout;
    private ArrayList<Dish> dishList=null;
    public DishAdapter(Context context, int layout, ArrayList<Dish> dishList) {
        this.context = context;
        this.layout = layout;
        this.dishList = dishList;
    }
    @Override
    public int getCount(){
        return dishList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.items, null,
                            false);
        }
        Dish dish = dishList.get(position);
        ImageView imgDish = (ImageView) convertView.findViewById(R.id.imgMonan);
        TextView txtDish = (TextView) convertView.findViewById(R.id.txtMonan);
        txtDish.setSelected(true);
        ImageView icnStar = (ImageView) convertView.findViewById(R.id.icnStar);
        if (dish.isPromotion())
        {
            icnStar.setVisibility(View.VISIBLE);

        }
        else
        {
            icnStar.setVisibility(View.GONE);
        }
        imgDish.setImageResource(dish.getThumbnail());
        txtDish.setText(dish.getName());
        return convertView;
    }
}
