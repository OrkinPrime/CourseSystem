package com.lizhongbin.ch_final.service;

import com.lizhongbin.ch_final.mapper.MainMapper;
import com.lizhongbin.ch_final.model.Course;
import com.lizhongbin.ch_final.model.Account;
import com.lizhongbin.ch_final.model.Student;
import com.lizhongbin.ch_final.security.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceIml implements MainService {

    PasswordManager passwordManager;
    MainMapper mainMapper;

    @Autowired
    public MainServiceIml(MainMapper mainMapper) {
        passwordManager = new PasswordManager();
        this.mainMapper = mainMapper;
    }

    @Override
    public List<Student> getAllStudents() {
        return mainMapper.selectAllStudent();
    }

    @Override
    public List<Account> getAllAccounts() {
        return mainMapper.selectAllAccount();
    }

    @Override
    public boolean verifyAccount(Account account) {
        if (mainMapper.selectAccountByLoginName(account.getLoginName()) != null) {
            return passwordManager.checkPassword(account.getPassword(), mainMapper.selectAccountByLoginName(account.getLoginName()).getPassword());
            //密码正确或者错误
        } else
            return false;
        //账户不存在

    }

    @Override
    public int addAccount(Account account) {
        //将前端传来的密码加密
        account.setPassword(passwordManager.hashPassword(account.getPassword()));
        return mainMapper.insertAccount(account);
    }

    @Override
    public int deleteAccount(int accountId) {
        return mainMapper.deleteAccountById(accountId);
    }

    @Override
    public int updateAccountPassword(Account account) {
        //将前端传来的密码加密
        account.setPassword(passwordManager.hashPassword(account.getPassword()));
        return mainMapper.updateAccountPassword(account);
    }

    @Override
    public List<Course> getAllCourses() {

        return mainMapper.selectAllCourses();
    }

    @Override
    public int addCourse(Course course) {
        return mainMapper.insertCourse(course);
    }

    @Override
    public int deleteCourse(int id) {
        return mainMapper.deleteAccountById(id);
    }

    @Override
    public int updateCourse(Course course) {
        return 0;
    }
}
