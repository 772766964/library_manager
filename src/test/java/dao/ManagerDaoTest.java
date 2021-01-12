package dao;

import entity.Manager;
import factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ManagerDaoTest {

    private final ManagerDao managerDao = DaoFactory.getManagerInstance();
    @Test
    public void getManager() {
        Manager manager=null;
        try {
            manager = managerDao.getManager("WE001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals("浅上",manager.getName());
        System.out.println(manager);
    }

    @Test
    public void setPhone() {
        int a=0;
        try {
            a = managerDao.setPhone("WE001","120");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,a);
    }
}