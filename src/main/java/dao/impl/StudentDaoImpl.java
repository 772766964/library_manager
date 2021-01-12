package dao.impl;

import dao.StudentDao;
import entity.Book;
import entity.Student;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/11
 **/
public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStu(boolean b, String str) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "";
        if(b){
            sql = "Select * from Student where readerId like '%"+str+"%'";
        }else{
            sql = "Select * from Student where readerName like '%"+str+"%'";
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //pstmt.setString(1,str);
        ResultSet rs = pstmt.executeQuery();
        List<Student> stuList = new ArrayList<>();
        while(rs.next()){
            Student student = new Student();
            student.setReaderId(rs.getString(1));
            student.setReaderName(rs.getString(2));
            student.setReaderSex(rs.getString(3));
            student.setPhone(rs.getString(4));
            student.setDept(rs.getString(5));
            student.setSituation(rs.getString(6));
            student.setBorrowAdd(rs.getString(7));
            student.setImg(rs.getString(8));
            stuList.add(student);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return stuList;
    }

    @Override
    public Student loginStu(String uId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "Select * from Student where readerId =?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,uId);
        ResultSet rs = pstmt.executeQuery();
        Student student = new Student();
        while(rs.next()){
            student.setReaderId(rs.getString(1));
            student.setReaderName(rs.getString(2));
            student.setReaderSex(rs.getString(3));
            student.setPhone(rs.getString(4));
            student.setDept(rs.getString(5));
            student.setSituation(rs.getString(6));
            student.setBorrowAdd(rs.getString(7));
            student.setImg(rs.getString(8));
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return student;
    }
}
