package dao;

import entity.account.Admin;

import java.sql.SQLException;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public interface AdminDao {
    /**
     * 查找管理员账户
     *
     * @param account：账号入参
     * @return  返回 Admin
     * @throws SQLException 异常
     */
    Admin findAdminByAccount(String account) throws SQLException;

}
