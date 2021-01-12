package service.impl;

import dao.StudentDao;
import entity.Student;
import factory.DaoFactory;
import service.StudentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/11
 **/
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = DaoFactory.getStudentInstance();
    @Override
    public List<Student> getStu(boolean b, String str) {
        List<Student> stu=null;
        try {
            stu = studentDao.getStu(b, str);
        } catch (SQLException e) {
            System.err.println("学生service异常");
        }
        return stu;
    }

    @Override
    public Student loginStu(String uId) {
        Student stu=null;
        try {
            stu = studentDao.loginStu(uId);
        } catch (SQLException e) {
            System.err.println("学生service异常");
        }
        return stu;
    }
}
