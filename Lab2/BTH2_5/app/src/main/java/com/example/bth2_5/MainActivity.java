package com.example.bth2_5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerDish;
    SpinnerAdapter spinnerAdapter;
    Dish res_dish;
    Button bAdd;
    GridView gvDish;
    EditText etName;
    DishAdapter adapter;
    CheckBox cbPromotion;
    ArrayList<Dish> arrDish;
    ArrayList<Dish> arrGvDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dish);

        arrDish = new ArrayList<Dish>();
        arrDish.add(new Dish("Dish 1", R.drawable.dish1));
        arrDish.add(new Dish("Dish 2", R.drawable.dish2));
        arrDish.add(new Dish("Dish 3", R.drawable.dish3));
        arrDish.add(new Dish("Dish 4", R.drawable.dish4));
        arrDish.add(new Dish("Dish 5", R.drawable.dish5));

        spinnerDish = (Spinner) findViewById(R.id.spinnerDish);
        spinnerAdapter = new SpinnerAdapter(this, R.layout.dropdown, arrDish);
        spinnerDish.setAdapter(spinnerAdapter);

        spinnerDish.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                res_dish = arrDish.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        cbPromotion = (CheckBox) findViewById(R.id.checkBox);
        bAdd = (Button) findViewById(R.id.bAdd);
        gvDish = (GridView) findViewById(R.id.gvDish);
        etName = (EditText) findViewById(R.id.etName);

        arrGvDish = new ArrayList<Dish>();
        adapter = new DishAdapter(this, R.layout.items, arrGvDish);
        gvDish.setAdapter(adapter);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Dish dish = new Dish(name, res_dish.getThumbnail(), cbPromotion.isChecked());
                arrGvDish.add(dish);
                Toast.makeText(MainActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                etName.setText("");
                spinnerDish.setSelection(0);
                cbPromotion.setChecked(false);
                adapter.notifyDataSetChanged();
            }
        });

    }
}