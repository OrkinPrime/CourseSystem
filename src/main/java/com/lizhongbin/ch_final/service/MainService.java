package com.lizhongbin.ch_final.service;

import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;
import com.lizhongbin.ch_final.model.StudentCourses;

import java.util.List;

public interface MainService {

    //账户信息验证
    boolean verifyAccount(Account account);
    //增加账户信息
    int addAccount(Account account);
    //注册时验证登录名重复性
    boolean isAccountNameExist(String accountName);
    //通过登录名获取账户信息
    Account getAccountByName(String accountName);
    //通过学生id查询选课情况
    List<Course> getCoursesByStuId(int stuId);
    //通过账户id获取对应学生信息
    Student getStudentByAccountId(int accountId);
    //增加学生信息
    int addStudent(Student student);
    //获取全部课程信息
    List<Course> getAllCourses();
    //增加课程信息
    int addCourse(Course course);
    //删除中间信息
    int deleteCourse(int id);
    //增加选课记录
    int addStudentCourses(StudentCourses studentCourses);
    //删除选课记录
    boolean deleteStudentCourses(StudentCourses studentCourses);
    //通过学生id获取学生可选课情况
    List<Course> getUnPickedCoursesByStudentId(int studentId);
    //通过课程id获取课程信息
    Course getCoursesById(int courseId);
    //更新课程信息
    int updateCourse(Course course);
}
