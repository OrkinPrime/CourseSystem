package com.lizhongbin.ch_final.service;

import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;

import java.util.List;

public interface MainService {

    //对账户
    //查全部
    List<Account> getAllAccounts();
    //登录验证
    boolean verifyAccount(Account account);
    //增加账户信息
    int addAccount(Account account);
    //注册时验证登录名重复性
    boolean isAccountNameExist(String accountName);
    //注销账号（需要将学生表里的学生信息删除）
    int deleteAccount(int accountId);
    //更换密码（需要在Controller中验证旧密码正确性）
    int updateAccountPassword(Account account);

    //对学生
    List<Student> getAllStudents();

    //对班级
    List<Course> getAllCourses();
    int addCourse(Course course);
    int deleteCourse(int id);
    int updateCourse(Course course);
}
