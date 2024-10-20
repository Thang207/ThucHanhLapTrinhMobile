package com.example.bth2_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String[] lvPerson = {"Tèo", "Tý", "Bin", "Bo"};
    ArrayAdapter<String> myAdapter;
    ListView lv_person;
    TextView tv_selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //
        tv_selection = findViewById(R.id.tv_selection);
        lv_person = findViewById(R.id.lv_person);
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,lvPerson);
        lv_person.setAdapter(myAdapter);
        // Sự kiện khi click vào list view
        lv_person.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_selection.setText("Position: " +position+ ", Value: "+ lvPerson[position]);
            }
        });
        // Title bar

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}