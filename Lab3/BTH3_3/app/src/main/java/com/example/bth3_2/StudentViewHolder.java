package com.example.bth3_2;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView txtName;
    private final TextView txtAge;
    private final TextView txtScore;

    public StudentViewHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        txtAge = itemView.findViewById(R.id.txtAge);
        txtScore = itemView.findViewById(R.id.txtScore);
    }

    @SuppressLint("SetTextI18n")
    public void bind(final Student student, final OnItemClickListener listener) {
        txtName.setText(student.getName());
        txtAge.setText("Age: " + student.getAge() + " ");
        txtScore.setText("Score: " + student.getScore());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(student);
            }
        });
    }
}