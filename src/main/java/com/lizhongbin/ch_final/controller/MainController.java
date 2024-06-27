package com.lizhongbin.ch_final.controller;

import com.lizhongbin.ch_final.entity.CourseInfo;
import com.lizhongbin.ch_final.model.*;
import com.lizhongbin.ch_final.entity.Respon;
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

    //接收前端传来的账户信息，已经保证密码格式
    //先验证账户信息中登录名是否重复，如果重复，返回给前端的消息里包含error，和message提示登录名重复
    //如果不重复，则添加账户信息到数据库，返回消息包含success，和message提示注册成功，并且跳转到登录界面
    @PostMapping("api/v1/signin")
    public ResponseEntity<Respon> signIn(@RequestBody Account_Student accountStudent) {
        Account account = new Account(accountStudent.getLoginName(), accountStudent.getPassword(), accountStudent.getAccountType());

        if (mainService.isAccountNameExist(accountStudent.getLoginName())) {
            // 登录名重复
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Respon(false, "登录名重复"));
        } else {
            if (mainService.addAccount(account) != 0) {
                Student student = new Student(accountStudent.getStuNo(), accountStudent.getStuName(), mainService.getAccountByName(accountStudent.getLoginName()).getId());
                if (mainService.addStudent(student) != 0) {
                    // 注册成功
                    return ResponseEntity.status(HttpStatus.CREATED).body(new Respon(true, "注册成功"));
                } else {
                    // 学生信息添加失败
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Respon(false, "学生信息添加失败"));
                }
            } else {
                // 账户信息添加失败
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Respon(false, "账户信息添加失败"));
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

    @GetMapping("api/v1/pick/{stuId}")
    public ResponseEntity<Respon> coursesCanBePick(@PathVariable int stuId) {
        List<Course> courses = mainService.getUnPickedCoursesByStudentId(stuId);
        return ResponseEntity.ok(new Respon(true, "查询成功", courses));
    }

    @PostMapping("api/v1/pick")
    public ResponseEntity<Respon> pick(@RequestBody CourseInfo courseInfo) {
        int stuId = courseInfo.getStuId();
        int[] courseId = courseInfo.getCourseId();

        boolean allSuccess = true;
        for (int id : courseId) {
            StudentCourses studentCourses = new StudentCourses(stuId, id);
            if (mainService.addStudentCourses(studentCourses) == 0) {
                allSuccess = false;
            }
        }

        if (allSuccess) {
            return ResponseEntity.ok(new Respon(true, "选课成功"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respon(false, "选课失败，可能是课程已满或其他原因"));
        }
    }

    @GetMapping("api/v1/picked/{stuId}")
    public ResponseEntity<Respon> picked(@PathVariable int stuId) {
        List<Course> courses = mainService.getCoursesByStuId(stuId);
        return ResponseEntity.ok(new Respon(true, "查询成功", courses));
    }

    @DeleteMapping("api/v1/drop")
    public ResponseEntity<Respon> drop(@RequestBody CourseInfo courseInfo) {
        int stuId = courseInfo.getStuId();
        int[] courseId = courseInfo.getCourseId();
        boolean allSuccess = true;
        for (int id : courseId) {
            StudentCourses studentCourses = new StudentCourses(stuId, id);
            if (!mainService.deleteStudentCourses(studentCourses)) {
                allSuccess = false;
            }
        }
        if (allSuccess) {
            return ResponseEntity.ok(new Respon(true, "退课成功"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respon(false, "退课失败，可能是课程未选或其他原因"));
        }
    }

    @PostMapping("api/v1/admin/courses")
    public ResponseEntity<Respon> addCourse(@RequestBody Course course) {
        if (mainService.addCourse(course)!=0)
            return ResponseEntity.status(HttpStatus.CREATED).body(new Respon(true,"添加课程信息成功",course));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respon(false, "添加课程信息失败"));
    }
    @GetMapping("api/v1/admin/courses/{courseId}")
    public ResponseEntity<Respon> getCourseById(@PathVariable int courseId) {
        if(mainService.getCoursesById(courseId)!=null)
            return ResponseEntity.ok(new Respon(true,"获取课程信息成功",mainService.getCoursesById(courseId)));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Respon(false,"获取课程信息失败"));
    }
    @PatchMapping("api/v1/admin/courses/{courseId}") // 假设按照课程ID更新，路径中添加了{courseId}
    public ResponseEntity<Respon> updateAdminCourse(@PathVariable int courseId, @RequestBody Course course) {

        Course pkg = new Course(courseId,course.getCourseName(), course.getDescription(), course.getCapacity());
        // 假设mainService.updateCourse接受课程ID和更新的信息
        boolean updateSuccess = mainService.updateCourse(pkg) != 0;

        if (updateSuccess) {
            // 如果更新成功，返回200 OK状态码和成功的响应体
            Respon successResponse = new Respon(true,"课程更新成功");
            return ResponseEntity.ok(successResponse);
        } else {
            // 如果更新失败，返回400 Bad Request或404 Not Found等适当状态码，以及错误信息
            Respon errorResponse = new Respon(false,"课程更新失败");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    @DeleteMapping("api/v1/admin/courses/{courseId}")
    public ResponseEntity<Respon> deleteCourseById(@PathVariable int courseId) {
        if (mainService.deleteCourse(courseId)!=0) {
            return ResponseEntity.ok(new Respon(true, "删除成功"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respon(false, "退课失败，删除失败"));
        }
    }

}
