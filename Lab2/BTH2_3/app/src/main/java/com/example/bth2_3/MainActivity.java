package com.example.bth2_3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etMaNV;
    EditText etTenNV;
    RadioGroup rgNV;
    ListView lvNV;
    Button bNV;
    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;
    Employee employee;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        etMaNV = findViewById(R.id.etMaNV);
        etTenNV = findViewById(R.id.etTenNV);
        lvNV = findViewById(R.id.lvNV); // Khởi tạo ListView
        rgNV = findViewById(R.id.rgNV);
        bNV = findViewById(R.id.bNV);

        employees = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        lvNV.setAdapter(adapter);

        bNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = rgNV.getCheckedRadioButtonId();
                String id = etMaNV.getText().toString();
                String name = etTenNV.getText().toString();

                if (type == R.id.rbCT) {
                    employee = new EmployeeFullTime(id, name);
                } else {
                    employee = new EmployeePartTime(id, name);
                }

                employees.add(employee);
                adapter.notifyDataSetChanged();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
