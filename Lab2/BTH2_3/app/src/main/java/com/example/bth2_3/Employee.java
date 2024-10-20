package com.example.bth2_3;

import androidx.annotation.NonNull;

public class Employee {
    private String id;
    private String name;
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double tinhLuong() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}

class EmployeePartTime extends Employee {

    // Constructor
    public EmployeePartTime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 1000;
    }

    @NonNull
    @Override
    public String toString() {
        return "id" + getId() + " - " + getName() + " --> " +"FullTime = " + tinhLuong();
    }
}

class EmployeeFullTime extends Employee {

    // Constructor
    public EmployeeFullTime(String id, String name) {
        super(id, name);
    }

    @Override
    public double tinhLuong() {
        return 3000;
    }

    @NonNull
    @Override
    public String toString() {
        return "id" + getId() + " - " + getName() + " --> " +"PartTime = " + tinhLuong();
    }
}

