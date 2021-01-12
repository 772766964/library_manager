package dao;

import entity.account.User;
import factory.DaoFactory;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

public class UserDaoTest {

    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void findUserByAccount() {
        User user;
        try{
            user = userDao.findUserByAccount("zhangsan");
            assertEquals("张三",user.getAdmin_name());
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertUser() {
        User user;
        try {
            user = new User("RE002","lisi","lisi","李四");
            assertEquals("2",userDao.insertUser(user));
        } catch (SQLException e) {
            System.err.println("异常---新增用户");
        }
    }

    @Test
    public void findUserByUId() {
        try {
            boolean result = userDao.findUserByUId("RE001");
            assertEquals(true , result);
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}