package com.lizhongbin.ch_final.entity;

public class CourseInfo {
    private int stuId;
    private int[] courseId;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int[] getCourseId() {
        return courseId;
    }

    public void setCourseId(int[] courseId) {
        this.courseId = courseId;
    }

    public CourseInfo(int stuId, int[] courseId) {
        this.stuId = stuId;
        this.courseId = courseId;
    }
}
