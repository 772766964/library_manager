package dao;

import entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName StudentDao
 * @Description TODO
 * @Author 1
 * @Date 2021/1/11
 **/
public interface StudentDao {
    /**
     *
     * 获取指定学生信息
     *
     * @param b
     * @param str
     * @return 学生集合
     */
    List<Student> getStu(boolean b , String str) throws SQLException;

    /**
     * 用来登录
     *
     * @param uId
     * @return Student
     * @throws SQLException
     */
    Student loginStu(String uId) throws  SQLException;
}
