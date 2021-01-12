package service.impl;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import dao.UserDao;
import entity.account.User;
import factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;
import service.UserService;
import utils.ResultEntity;

import java.sql.SQLException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoFactory.getUserDaoInstance();
    /**
     * User登录
     *
     * @param account
     * @param password
     */
    @Override
    public ResultEntity userLogin(String account, String password) {
        ResultEntity resultEntity;
        User user = null;
        try {
            user = userDao.findUserByAccount(account);
        } catch (SQLException e) {
            System.err.println("根据账号查找管理员信息出现SQL异常");
        }
        //根据账号查找到了记录
        if(user != null){
            if(DigestUtils.md5Hex(password).equals(user.getPassword())){
                resultEntity = ResultEntity.builder().code(0).message("登录成功！").data(user).build();
            }else{
                //账户存在
                resultEntity = ResultEntity.builder().code(1).message("密码错误").build();
            }
        }else{
            resultEntity = ResultEntity.builder().code(2).message("账户不存在").build();
        }
        return resultEntity;
    }

    @Override
    public int userInsert(User user) {
        //String uId, String account, String password, String admin_name
        //new User(uId,account,password,admin_name
        int result=0;
        try {
            if(userDao.findUserByUId(user.getUId()) == false){
                result = userDao.insertUser(user);
            }
        } catch (SQLException e) {
            System.err.println("插入用户数据出现异常");
        }
        return result;
    }
}
