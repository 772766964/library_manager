package dao.impl;

import dao.AdminDao;
import entity.account.Admin;
import utils.JdbcUtil;
import utils.ResultEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName AdminDaoImpl
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public class AdminDaoImpl implements AdminDao {
    /**
     * 查找管理员账户
     *
     * @param account ：账号入参
     * @return 返回 Admin
     * @throws SQLException 异常
     */
    @Override
    public Admin findAdminByAccount(String account) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "SELECT * FROM admin WHERE account = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,account);
        ResultSet rs = pstmt.executeQuery();
        Admin admin = null;
        while (rs.next()){
           String aId = rs.getString(1);
           String adminAccount = rs.getString(2);
           String password = rs.getString(3);
           String admin_name = rs.getString(4);
           admin = new Admin();
           admin.setAId(aId);
           admin.setAccount(account);
           admin.setPassword(password);
           admin.setAdmin_name(admin_name);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return admin;
    }
}
