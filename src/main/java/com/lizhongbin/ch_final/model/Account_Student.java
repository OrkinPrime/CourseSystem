package com.lizhongbin.ch_final.model;

public class Account_Student {
    private enum AccountType {
        STUDENT,
        ADMIN,
        TEACHER
    }
    private String loginName;
    private String password;
    private Account.AccountType accountType;
    private long stuNo;
    private String stuName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account.AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(Account.AccountType accountType) {
        this.accountType = accountType;
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

    public Account_Student() {
    }

    public Account_Student(String loginName, String password, Account.AccountType accountType, long stuNo, String stuName) {
        this.loginName = loginName;
        this.password = password;
        this.accountType = accountType;
        this.stuNo = stuNo;
        this.stuName = stuName;
    }
}
