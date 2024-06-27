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

    //通过登录名获取账户信息
    @Select("select * from accountinfo where loginName = #{loginName}")
    Account selectAccountByLoginName(String loginName);
    //通过登录名判断账户是否存在
    @Select("SELECT COUNT(*) FROM accountinfo WHERE loginName = #{loginName}")
    Integer isAccountNameExist(String loginName);
    //增加账户信息
    @Insert("insert into accountinfo(loginName,password,accountType) VALUES (#{loginName},#{password},#{accountType})")
    int insertAccount(Account account);
    //通过账户id获取对应学生信息
    @Select("select * from students where accountId = #{accountId}")
    Student selectStudentByAccountId(int accountId);
    //增加学生信息
    @Insert("insert into students(stuNo,stuName,accountId) VALUES (#{stuNo},#{stuName},#{accountId})")
    int insertStudent(Student student);
    //查全部课程信息
    @Select("select * from courses")
    List<Course> selectAllCourses();
    //通过id，查全部可选课程信息
    @Select("SELECT c.id, c.courseName, c.description, c.capacity " +
            "FROM courses c " +
            "WHERE NOT EXISTS (SELECT 1 FROM student_courses sc WHERE sc.stuId = #{stuId} AND sc.courseId = c.id)")
    List<Course> selectUnPickedCourse(int stuId);
    //增加课程信息
    @Insert("insert into courses(courseName,description,capacity) VALUES (#{courseName},#{description},#{capacity})")
    int insertCourse(Course course);
    //通过课程id获取课程信息
    @Select("select * from courses where id = #{courseId}")
    Course selectCourseById(int courseId);
    //删除课程信息
    @Delete("delete from courses where courses.id=#{courseId}")
    int deleteCourseById(int courseId);
    //更新课程信息
    @Update({
            "<script>",
            "update courses",
            "<set>",
            "<if test = 'courseName!=null'> courseName=#{courseName},</if>",
            "<if test = 'description!=null'> description=#{description},</if>",
            "<if test = 'capacity!=null'> capacity=#{capacity},</if>",
            "</set>",
            "where id=#{id}",
            "</script>"
    })
    int updateCourse(Course course);
    //通过学生id获取全部已选课程信息
    @Select("SELECT c.id, c.courseName, c.description, c.capacity " +
            "FROM courses c " +
            "INNER JOIN student_courses sc ON c.id = sc.courseId " +
            "WHERE sc.stuId = #{stuId}")
    List<Course> getCoursesByStuId(int stuId);
    //增加选课记录
    @Insert("insert into student_courses (stuId, courseId) values (#{stuId},#{courseId})")
    int insertStudentCourses(StudentCourses studentCourses);
    //删除选课记录
    @Delete("DELETE FROM student_courses WHERE stuId = #{stuId} AND courseId = #{courseId}")
    int deleteStudentCourseByStuIdAndCourseId(StudentCourses studentCourses);

}
