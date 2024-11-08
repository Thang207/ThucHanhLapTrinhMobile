package com.example.bth3_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> contactList) {
        super(context, 0, contactList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        Contact contact = getItem(position);

        TextView textViewNAME = convertView.findViewById(R.id.tv_name);
        TextView textViewPHONE = convertView.findViewById(R.id.tv_phoneNumber);

        assert contact != null;
        textViewNAME.setText(contact.getName());
        textViewPHONE.setText(contact.getPhoneNumber());

        return convertView;
    }
}
