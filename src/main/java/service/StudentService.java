package service;

import entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 1
 */
public interface StudentService {
    /**
     *
     * 获取指定学生信息
     *
     * @param b
     * @param str
     * @return 学生集合
     */
    List<Student> getStu(boolean b , String str);

    /**
     * 登录
     * @param uId
     * @return Student
     */
    Student loginStu(String uId);
}
