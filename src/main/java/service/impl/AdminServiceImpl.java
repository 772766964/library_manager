package service.impl;

import dao.AdminDao;
import entity.account.Admin;
import factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;
import service.AdminService;
import utils.ResultEntity;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return ResultEntity
     */
    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity;
        Admin admin = null;
        try {
            admin = adminDao.findAdminByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if(admin != null){
            if(DigestUtils.md5Hex(password).equals(admin.getPassword())){
                resultEntity = ResultEntity.builder().code(0).message("登录成功！").data(admin).build();
            }else{
                resultEntity = ResultEntity.builder().code(1).message("密码错误！").build();
                System.out.println(DigestUtils.md5Hex(password));
            }
        }else{
            resultEntity = ResultEntity.builder().code(2).message("账户不存在！").build();
        }
        return resultEntity;
    }

    @Override
    public Admin getSel(String account) {
        Admin admin = null;
        try {
            admin = adminDao.findAdminByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}
