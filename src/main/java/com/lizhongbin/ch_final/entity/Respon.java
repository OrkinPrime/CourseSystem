package com.lizhongbin.ch_final.entity;

import com.lizhongbin.ch_final.model.Course;

import java.util.List;

public class Respon {
    private Boolean success;
    private String message;
    private int userId;
    private int stuId;
    private String type;
    private List<Course> courses;
    private Course course;

    public Respon(Boolean success, String message, Course course) {
        this.success = success;
        this.message = message;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Respon(Boolean success, String message, List<Course> courses) {
        this.success = success;
        this.message = message;
        this.courses = courses;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Respon(Boolean success, String message, int userId, int stuId, String type) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.stuId = stuId;
        this.type = type;
    }

    public Respon(Boolean success, String message, int userId, String type) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.type = type;
    }

    public Respon(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
