package service;

import entity.account.Admin;
import utils.ResultEntity;

/**
 * @author 1
 */
public interface AdminService {
    /**
     *  登录
     *
     * @param account
     * @param password
     * @return ResultEntity
     */
    ResultEntity adminLogin(String account , String password);

    /**
     *  获取 管理员信息
     * @return Admin
     * @param account
     */
    Admin getSel(String account);
}
