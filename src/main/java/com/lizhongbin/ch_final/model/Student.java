package com.lizhongbin.ch_final.model;

public class Student {
    private int id;
    private long stuNo;
    private String stuName;
    private int accountId;

    public Student() {
    }

    public Student(long stuNo, String stuName, int accountId) {
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.accountId = accountId;
    }

    public Student(long stuNo, String stuName) {
        this.stuNo = stuNo;
        this.stuName = stuName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStuNo() {
        return stuNo;
    }

    public void setStuNo(long stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
