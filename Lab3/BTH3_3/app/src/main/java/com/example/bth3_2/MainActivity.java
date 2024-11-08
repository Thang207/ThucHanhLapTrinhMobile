package com.example.bth3_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    private EditText editTextName;
    private EditText editTextAge;
    private EditText editAVGScore;
    private StudentAdapter studentAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edtName);
        editTextAge = findViewById(R.id.edtAge);
        editAVGScore = findViewById(R.id.edtScore);
        Button buttonAdd = findViewById(R.id.btnAdd);
        RecyclerView recyclerViewStudents = findViewById(R.id.rvStudents);

        studentAdapter = new StudentAdapter();
        studentAdapter.setOnItemClickListener(this);
        recyclerViewStudents.setAdapter(studentAdapter);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo DBHelper và SQLiteDatabase
        dbHelper = new DatabaseHelper(this);

        // Lấy dữ liệu từ cơ sở dữ liệu và hiển thị
        loadStudentsFromDatabase();

        buttonAdd.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String ageText = editTextAge.getText().toString().trim();
            String scoreText = editAVGScore.getText().toString().trim();

            if (name.isEmpty() || ageText.isEmpty() || scoreText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                float score = Float.parseFloat(scoreText);
                // Thêm sinh viên vào cơ sở dữ liệu
                long id = dbHelper.addStudent(name, age, score);
                Student student = new Student((int) id, name, age, score);
                studentAdapter.addStudent(student);
                clearInputs();
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Invalid input for age or score", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadStudentsFromDatabase() {
        // Lấy danh sách sinh viên từ cơ sở dữ liệu
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo chỉ số cột hợp lệ
            int idColumnIndex = cursor.getColumnIndex("id");
            int nameColumnIndex = cursor.getColumnIndex("name");
            int ageColumnIndex = cursor.getColumnIndex("age");
            int scoreColumnIndex = cursor.getColumnIndex("score");

            // Kiểm tra nếu có chỉ số cột không hợp lệ (ví dụ: -1)
            if (idColumnIndex == -1 || nameColumnIndex == -1 || ageColumnIndex == -1 || scoreColumnIndex == -1) {
                Toast.makeText(MainActivity.this, "Error: Invalid column index", Toast.LENGTH_SHORT).show();
                cursor.close();
                return;
            }

            do {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                int age = cursor.getInt(ageColumnIndex);
                float score = cursor.getFloat(scoreColumnIndex);
                Student student = new Student(id, name, age, score);
                studentAdapter.addStudent(student);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(MainActivity.this, "No students found", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        editTextName.setText("");
        editTextAge.setText("");
        editAVGScore.setText("");
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClick(final Student student) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit student");
        View view = LayoutInflater.from(this).inflate(R.layout.edit_dialog, null);
        final EditText editTextName = view.findViewById(R.id.edtDialogName);
        final EditText editTextAge = view.findViewById(R.id.edtDialogAge);
        final EditText editTextAVGScore = view.findViewById(R.id.edtDialogScore);
        editTextName.setText(student.getName());
        editTextAge.setText(String.valueOf(student.getAge()));
        editTextAVGScore.setText(String.valueOf(student.getScore()));
        builder.setView(view);

        builder.setPositiveButton("Save", (dialog, which) -> {
            // Tạo một hộp thoại xác nhận để hỏi người dùng có chắc chắn muốn lưu không
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirm Save")
                    .setMessage("Are you sure you want to save the changes?")
                    .setPositiveButton("Yes", (dialog1, which1) -> {
                        String name = editTextName.getText().toString().trim();
                        String ageText = editTextAge.getText().toString().trim();
                        String scoreText = editTextAVGScore.getText().toString().trim();

                        if (name.isEmpty() || ageText.isEmpty() || scoreText.isEmpty()) {
                            Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int age = Integer.parseInt(ageText);
                            float score = Float.parseFloat(scoreText);
                            // Cập nhật sinh viên trong cơ sở dữ liệu
                            dbHelper.updateStudent(student.getId(), name, age, score);
                            // Cập nhật thông tin sinh viên trong adapter
                            student.setName(name);
                            student.setAge(age);
                            student.setScore(score);
                            studentAdapter.updateStudent(student);

                            // Làm mới danh sách trong adapter
                            studentAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Student information updated successfully", Toast.LENGTH_SHORT).show();

                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Invalid input for age or score", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null) // Nếu chọn "No", chỉ cần đóng hộp thoại xác nhận
                    .show();
        });


        builder.setNegativeButton("Delete", (dialog, which) -> new AlertDialog.Builder(MainActivity.this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this student?")
                .setPositiveButton("Yes", (dialog1, which1) -> {
                    dbHelper.deleteStudent(student.getId());
                    studentAdapter.deleteStudent(student);
                })
                .setNegativeButton("No", null)
                .show());

        builder.show();
    }
}
