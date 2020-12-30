package dao;

import entity.User;

import java.sql.SQLException;

/**
 * @author 1
 */
public interface UserDao {
    /**
     * 根据账号找管理员
     *
     * @param account：账号入参
     * @return  返回 User
     * @throws SQLException 异常
     */
    User findUserByAccount(String account) throws SQLException;
}
