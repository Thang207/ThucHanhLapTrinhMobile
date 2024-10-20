package com.example.bth2_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etID;
    CheckBox cbManager;
    Button bAdd;
    RecyclerView rcvEmployee;;
    ArrayList<Employee> employees;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etName);
        cbManager = (CheckBox) findViewById(R.id.chbxManager);
        bAdd = (Button) findViewById(R.id.bAdd);
        rcvEmployee = (RecyclerView) findViewById(R.id.rcvEmployee);
        employees = new ArrayList<Employee>();

        adapter = new EmployeeAdapter(this, R.layout.item_employee,employees);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvEmployee.setLayoutManager(linearLayoutManager);
        rcvEmployee.setAdapter(adapter);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                Employee employee = new Employee();
                employee.setManager(cbManager.isChecked());
                employee.setFullName(name);
                employees.add(employee);
                adapter.notifyDataSetChanged();
            }
        });
    }
}