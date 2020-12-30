package dao.impl;

import dao.UserDao;
import entity.User;
import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class UserDaoImpl implements UserDao {
    /**
     * 根据账号找管理员
     *
     * @param account ：账号入参
     * @return 返回 User
     * @throws SQLException 异常
     */
    @Override
    public User findUserByAccount(String account) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "SELECT * FROM 表 WHERE account = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,account);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        while(rs.next()){
            int a = rs.getInt("");
            user = new User();
            //
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return user;
    }
}
