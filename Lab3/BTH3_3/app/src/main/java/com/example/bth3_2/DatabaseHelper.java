package com.example.bth3_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 3;

    private static final String SQL_CREATE_TABLE_STUDENTS =
            "CREATE TABLE students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "age INTEGER," +
                    "score FLOAT)";
    private static final String SQL_DELETE_TABLE_STUDENTS =
            "DROP TABLE IF EXISTS students";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_STUDENTS);
        onCreate(db);
    }

    // Phương thức thêm sinh viên
    public long addStudent(String name, int age, float score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("score", score);
        return db.insert("students", null, values);
    }

    // Phương thức lấy danh sách tất cả sinh viên
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM students", null);
    }

    // Phương thức cập nhật thông tin sinh viên
    public void updateStudent(int id, String name, int age, float score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("score", score);
        db.update("students", values, "id = ?", new String[]{String.valueOf(id)});
    }

    // Phương thức xóa sinh viên
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("students", "id = ?", new String[]{String.valueOf(id)});
    }
}
