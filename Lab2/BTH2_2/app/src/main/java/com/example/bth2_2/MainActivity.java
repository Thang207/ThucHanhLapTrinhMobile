package com.example.bth2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // declare variable
    ArrayAdapter<String> adapter;
    ListView lvPerson;
    TextView tvPerson;
    ArrayList<String> arrayName;
    Button btnSummit;
    EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // bind view by id
        tvPerson = findViewById(R.id.tvPerson);
        lvPerson = findViewById(R.id.lvPerson);
        btnSummit = findViewById(R.id.btnSummit);
        etName = findViewById(R.id.etName);

        // create arraylist
        arrayName = new ArrayList<String>();
        arrayName.add("Teo");
        arrayName.add("Ti");
        arrayName.add("Bin");
        arrayName.add("Bo");

        // create adapter and set adapter for listview
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayName);
        lvPerson.setAdapter(adapter);

        // bind event add button
        btnSummit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String ten = etName.getText().toString();
                arrayName.add(ten); // add item in listview
                adapter.notifyDataSetChanged(); // update listview
            }
        });
        // bind event click
        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = lvPerson.getItemAtPosition(position).toString();
                tvPerson.setText("Position :" + position + " Name: " + name);
            }
        });
        // bind event long click
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayName.remove(position); // remove item in listview
                adapter.notifyDataSetChanged(); // update listview
                return false;
            }
        });
    };
};
