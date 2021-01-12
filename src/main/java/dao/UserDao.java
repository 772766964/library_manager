package dao;

import entity.Student;
import entity.account.User;

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

    /**
     * 插入 User
     *
     * @param user
     * @return User
     * @throws SQLException
     */
    int insertUser(User user) throws SQLException;

    /**
     * 查找是否存在账户，用于注册验证
     *
     * @param uId
     * @return true -- 存在，，false -- 不存在
     * @throws SQLException
     */
    boolean findUserByUId(String uId) throws SQLException;
}
