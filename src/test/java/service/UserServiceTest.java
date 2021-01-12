package service;

import entity.account.User;
import factory.ServiceFactory;
import org.junit.Test;
import utils.ResultEntity;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void userLogin() {
        ResultEntity resultEntity = userService.userLogin("zhangsan","zhangsan");
        assertEquals(0,resultEntity.getCode());
        System.out.println(resultEntity);
    }

    @Test
    public void userInsert() {
        User user= new User("RE003","zhaosi","zhaosi","赵四");
        int result = userService.userInsert(user);
        assertEquals(1,result);
        System.out.println("插入成功");
    }
}