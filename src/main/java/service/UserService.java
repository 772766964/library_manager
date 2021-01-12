package service;

import entity.Student;
import entity.account.User;
import utils.ResultEntity;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public interface UserService {
    /**
     * User登录
     */
    ResultEntity userLogin(String account , String password);

    /**
     * 插入数据
     * @param user
     * @return
     */
    int userInsert(User user);
}
