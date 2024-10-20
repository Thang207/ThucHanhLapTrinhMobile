package com.example.bth2_4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter < Employee > {
    private Activity context;
    private  int layoutID;
    private List< Employee > objects;
    public EmployeeAdapter(Activity context, int layoutID, List < Employee >
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_employee, null,
                            false);
        }
        // Get item
        Employee employee = getItem(position);
        // Get view
        TextView tvFullName = (TextView)
                convertView.findViewById(R.id.item_employee_tv_fullname);
        TextView tvPosition = (TextView)
                convertView.findViewById(R.id.item_employee_tv_position);
        ImageView ivManager = (ImageView)
                convertView.findViewById(R.id.item_employee_iv_manager);
        LinearLayout llParent = (LinearLayout)
                convertView.findViewById(R.id.item_employee_ll_parent);
        // Set fullname
        assert employee != null;
        if (employee.getFullName() != null) {
            tvFullName.setText(employee.getFullName());
        } else tvFullName.setText("");
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager()) {
            ivManager.setVisibility(View.VISIBLE);
            tvPosition.setVisibility(View.GONE);
        } else {
            ivManager.setVisibility(View.GONE);
            tvPosition.setVisibility(View.VISIBLE);
            tvPosition.setText(context.getString(R.string.staff));
        }
        // Show different color backgrounds for 2 continuous employees
        if (position % 2 == 0) {
            llParent.setBackgroundResource(R.color.white);
        } else {
            llParent.setBackgroundResource(R.color.light_blue);
        }
        return convertView;
    }
}