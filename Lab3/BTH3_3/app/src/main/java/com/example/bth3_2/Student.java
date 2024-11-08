package com.example.bth3_2;

public class Student {
    private int id;
    private String name;
    private int age;
    private float score;

    public Student(int id, String name, int age, float score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter và Setter cho score
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
