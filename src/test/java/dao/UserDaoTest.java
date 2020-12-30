package dao;

import entity.User;
import factory.DaoFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void findUserByAccount() {
    }
}