package dao;

import entity.account.Admin;
import factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDaoTest {

    private AdminDao adminDao = DaoFactory.getAdminDaoInstance();

    @Test
    public void findAdminByAccount() {
        Admin admin;
        try {
            admin = adminDao.findAdminByAccount("qianshang");
            assertEquals("浅上",admin.getAdmin_name());
            System.out.println(admin);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.err.println("查找管理员账户出现异常");
        }
    }
}