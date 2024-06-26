package com.lizhongbin.ch_final.model;

public class StudentCourses {
    private int id;
    private int stuId;
    private int courseId;

    public StudentCourses() {
    }

    public StudentCourses(int stuId, int courseId) {
        this.stuId = stuId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
