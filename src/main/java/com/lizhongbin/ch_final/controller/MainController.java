package com.lizhongbin.ch_final.controller;

import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;
import com.lizhongbin.ch_final.response.Respon;
import com.lizhongbin.ch_final.service.MainService;
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
    public ResponseEntity<Respon> signIn(@RequestBody Account account) {
        if (mainService.isAccountNameExist(account.getLoginName())){
            //如果存在此登录名
            return new ResponseEntity<>(new Respon(false,"注册失败,登录名重复"),HttpStatus.OK);
        }else {
            if(mainService.addAccount(account)!=0)
                return new ResponseEntity<>(new Respon(true,"注册成功"),HttpStatus.OK);
            else
                return new ResponseEntity<>(new Respon(true,"注册失败,原因未知"),HttpStatus.OK);
        }
    }

    @PostMapping("api/v1/login")
    //应该在后端为成功验证前端的账户信息后，生成一个包含对应账户信息的token，并包含在返回体中返回给前端
    //前端提取出token进行保存，注册ajaxsend事件，以实现以后每次的请求头中都携带着Token
    public ResponseEntity<Respon> login(@RequestBody Account account) {
        if (mainService.verifyAccount(account)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
