package com.lizhongbin.ch_final.mapper;

import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;
import com.lizhongbin.ch_final.model.StudentCourses;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MainMapper {

    //对账户表
    //查全部
    @Select("select * from accountinfo")
    List<Account> selectAllAccount();
    //查账户是否存在
    @Select("select * from accountinfo where loginName = #{loginName}")
    Account selectAccountByLoginName(String loginName);
/*
    //根据账户信息查对应学生信息
    @Select("select * from accountinfo a join students s on s.accountId=a.id where accountType='STUDENT' and a.id=#{accountId}")
    Student selectStudentByAccountId(int accountId);
*/

    //增（注册时使用）
    @Insert("insert into accountinfo(name,password,accountType) VALUES (#{name},#{password},#{accountType})")
    int insertAccount(Account account);
    //删
    @Delete("delete from accountinfo where id=#{accountId}")
    int deleteAccountById(int accountId);
    //改（密码）
    @Update("update accountinfo set password=#{password} when id=#{id}")
    int updateAccountPassword(Account account);


    //对学生表
    //查全部
    @Select("select * from students")
    List<Student> selectAllStudent();
    //增
    @Insert("insert into students(stuNo,stuName,accountId) VALUES (#{stuNo},#{stuName},#{accountId})")
    int insertStudent(Student student);
    //删
    @Delete("delete from studnets where id = #{stuId}")
    int deleteStudentById(int stuId);
    //改
    @Update({
            "<script>",
            "update studnets",
            "<set>",
            "<if test = 'stuNo!=null'> stuNo=#{stuNo},</if>",
            "<if test = 'stuName!=null'> stuName=#{stuName},</if>",
            "</set>",
            "where id=#{id}",
            "</script>"
    })
    int updateStudent(Student student);


    //对班级表
    //查全部
    @Select("select * from courses")
    List<Course> selectAllCourses();
    //增
    @Insert("insert into courses(courseName,description,capacity) VALUES (#{courseName},#{description},#{capacity})")
    int insertCourse(Course course);
    //删
    @Delete("delete from course where course.id=#{courseId}")
    int deleteCourseById(int courseId);
    //改
    @Update({
            "<script>",
            "update course",
            "<set>",
            "<if test = 'courseName!=null'> courseName=#{courseName},</if>",
            "<if test = 'description!=null'> description=#{description},</if>",
            "<if test = 'capacity!=null'> capacity=#{capacity},</if>",
            "</set>",
            "where id=#{id}",
            "</script>"
    })
    int updateCourse(Course course);

    //对学生班级中间表
    //查全部
    @Select("select * from student_courses")
    List<StudentCourses> selectAllStudentCourses();
    //根据学生ID，查学生的选课情况
    @Select("select * from student_courses when webdb4.student_courses.student_id=#{studentId}")
    List<StudentCourses> selectSCByStudentId(int studentId);
    //根据课程ID，查课程的参与情况
    @Select("select * from student_courses when webdb4.student_courses.course_id=#{courseId}")
    List<StudentCourses> selectSCByCourseId(int courseId);
    //学生选课
    @Insert("insert into student_courses (student_id, course_id) values (#{student_id},#{course_id})")
    int insertStudentCourses(StudentCourses studentCourses);
}