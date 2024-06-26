package com.lizhongbin.ch_final.controller;

import com.lizhongbin.ch_final.entity.CourseInfo;
import com.lizhongbin.ch_final.model.*;
import com.lizhongbin.ch_final.entity.Respon;
import com.lizhongbin.ch_final.service.MainService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("api/v1/signin")
    //接收前端传来的账户信息，已经保证密码格式
    //先验证账户信息中登录名是否重复，如果重复，返回给前端的消息里包含error，和message提示登录名重复
    //如果不重复，则添加账户信息到数据库，返回消息包含success，和message提示注册成功，并且跳转到登录界面
    public ResponseEntity<Respon> signIn(@RequestBody Account_Student accountStudent) {
        Account account = new Account(accountStudent.getLoginName(), accountStudent.getPassword(), accountStudent.getAccountType());
        if (mainService.isAccountNameExist(accountStudent.getLoginName())) {
            //如果存在此登录名
            return new ResponseEntity<>(new Respon(false, "注册失败,登录名重复"), HttpStatus.OK);
        } else {
            if (mainService.addAccount(account) != 0) {
                Student student = new Student(accountStudent.getStuNo(), accountStudent.getStuName(), mainService.getAccountByName(accountStudent.getLoginName()).getId());
                if (mainService.addStudent(student) != 0)
                    return new ResponseEntity<>(new Respon(true, "注册成功"), HttpStatus.OK);
                else
                    return new ResponseEntity<>(new Respon(false, "注册失败,学生信息添加失败"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Respon(false, "注册失败,账户信息添加失败"), HttpStatus.OK);
            }
        }
    }

    @PostMapping("api/v1/login")
    public ResponseEntity<Respon> login(@RequestBody Account account) {
        if (mainService.verifyAccount(account)) {
            account.setId(mainService.getAccountByName(account.getLoginName()).getId());
            String type = String.valueOf(mainService.getAccountByName(account.getLoginName()).getAccountType());
            if (type == "STUDENT") {
                int stuId = mainService.getStudentByAccountId(account.getId()).getId();
                return ResponseEntity.ok(new Respon(true, "登录成功", account.getId(), stuId, "STUDENT"));
            } else if (type == "ADMIN") {
                return ResponseEntity.ok(new Respon(true, "管理员登录成功", account.getId(), "ADMIN"));
            }else{
                return ResponseEntity.ok(new Respon(true, "教师登录成功", account.getId(), "TEACHER"));
            }
        } else {
            // 改为使用401 Unauthorized，前端可以在error回调中处理
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Respon(false, "登录失败，密码或登录名错误"));
        }
    }

    @PostMapping("api/v1/pick")
    public ResponseEntity<Respon> pick(@RequestBody CourseInfo courseInfo) {
        int stuId = courseInfo.getStuId();
        int[] courseId = courseInfo.getCourseId();
        for (int courseIds : courseId) {
            StudentCourses studentCourses = new StudentCourses(stuId, courseIds);
            if (mainService.addStudentCourses(studentCourses) == 0) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<>(new Respon(true, "选课成功"), HttpStatus.OK);
    }

    @GetMapping("api/v1/picked/{stuId}")
    public List<Course> picked(@PathVariable int stuId) {
        return mainService.getCoursesByStuId(stuId);
    }

    @DeleteMapping("api/v1/drop")
    public void drop(@RequestBody CourseInfo courseInfo) {
        int stuId = courseInfo.getStuId();
        int[] courseId = courseInfo.getCourseId();
        for (int courseIds : courseId) {
            StudentCourses studentCourses = new StudentCourses(stuId, courseIds);
            mainService.deleteStudentCourses(studentCourses);
        }
    }
}
