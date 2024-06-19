package com.lizhongbin.ch_final.controller;

import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;
import com.lizhongbin.ch_final.service.MainService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("api/v1/test/student")
    public List<Student> testStudent() {
        return mainService.getAllStudents();
    }

    @GetMapping("api/v1/test/course")
    public List<Course> testCourse() {
        return mainService.getAllCourses();
    }

    @GetMapping("api/v1/test/account")
    public List<Account> testAccount() {
        return mainService.getAllAccounts();
    }
}
