package com.lizhongbin.ch_final.model;

public class Account {
    enum AccountType {
        STUDENT,
        ADMIN,
        TEACHER
    }
    private int id;
    private String loginName;
    private String password;
    private AccountType accountType;

    public Account() {
    }

    public Account(AccountType accountType, String password, String loginName, int id) {
        this.accountType = accountType;
        this.password = password;
        this.loginName = loginName;
        this.id = id;
    }

    public Account(String loginName, String password, AccountType accountType) {
        this.loginName = loginName;
        this.password = password;
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
