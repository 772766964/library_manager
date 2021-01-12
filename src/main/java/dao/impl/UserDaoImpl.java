package dao.impl;

import dao.UserDao;
import entity.Student;
import entity.account.User;
import org.apache.commons.codec.digest.DigestUtils;
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
        String sql = "SELECT * FROM users WHERE account = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,account);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        while(rs.next()){
            String uId = rs.getString("uId");
            String userAccount = rs.getString("account");
            String password = rs.getString("password");
            String admin_name = rs.getString("admin_name");
            user = new User();
            user.setUId(uId);
            user.setAccount(userAccount);
            user.setPassword(password);
            user.setAdmin_name(admin_name);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return user;
    }

    /**
     * 插入 User
     *
     * @param user
     * @return User
     * @throws SQLException
     */
    @Override
    public int insertUser(User user) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "INSERT INTO Users VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getUId());
        pstmt.setString(2,user.getAccount());
        pstmt.setString(3, DigestUtils.md5Hex(user.getPassword()));
        pstmt.setString(4,user.getAdmin_name());

        int n = pstmt.executeUpdate();

        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

    @Override
    public boolean findUserByUId(String uId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "SELECT * from users where uId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,uId);
        ResultSet rs = pstmt.executeQuery();
        String account = null;

        return rs.next();
    }
}
