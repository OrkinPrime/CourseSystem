package com.lizhongbin.ch_final.model;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private Integer capacity;

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(int id, String courseName, String description, Integer capacity) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.capacity = capacity;
    }

    public Course(String courseName, String description, Integer capacity) {
        this.courseName = courseName;
        this.description = description;
        this.capacity = capacity;
    }

    public Course(String description, Integer capacity) {
        this.description = description;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
