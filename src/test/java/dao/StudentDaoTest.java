package dao;

import entity.Student;
import factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private final StudentDao studentDao = DaoFactory.getStudentInstance();

    @Test
    public void getStu() {
        try {
            java.util.List<Student> stuList = studentDao.getStu(true,"RE0");
            assert stuList != null;
            stuList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}