package dao.impl;

import dao.ManagerDao;
import entity.Manager;
import entity.account.Admin;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName ManagerDaoImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public class ManagerDaoImpl implements ManagerDao {

    @Override
    public Manager getManager(String workerId) throws SQLException {

        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "SELECT * FROM manager WHERE workerId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,workerId);
        ResultSet rs = pstmt.executeQuery();
        Manager manager = null;
        while (rs.next()){
            String a = rs.getString(1);
            String b = rs.getString(2);
            String c = rs.getString(3);
            String d = rs.getString(4);
            manager = new Manager();
            manager.setWorkerId(a);
            manager.setName(b);
            manager.setSex(c);
            manager.setPhones(d);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return manager;
    }

    @Override
    public int setPhone(String workerId, String phone) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "UPDATE manager SET phoneS= ? where workerId=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,phone);
        pstmt.setString(2,workerId);
        int result = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return result;
    }
}
