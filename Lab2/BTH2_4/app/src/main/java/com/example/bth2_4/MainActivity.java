package com.example.bth2_4;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Declare
        EditText edId = findViewById(R.id.edId);
        EditText edName = findViewById(R.id.edName);
        CheckBox cbManager = findViewById(R.id.cbManager);
        Button bAdd = findViewById(R.id.bAdd);
        ListView lvEmployee = findViewById(R.id.lvEmployee);
        ArrayList<Employee> employees = new ArrayList<Employee>();
        EmployeeAdapter adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployee.setAdapter(adapter);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String id = edId.getText().toString();
                Employee employee = new Employee(id, name, false);
                if (cbManager.isChecked()) {
                    employee.setManager(true);
                } else {
                    employee.setManager(false);
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