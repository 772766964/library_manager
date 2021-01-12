package service;

import entity.Manager;
import org.junit.Test;
import service.impl.ManagerServiceImpl;

import static org.junit.Assert.*;

public class ManagerServiceTest {

    private ManagerService managerService = new ManagerServiceImpl();

    @Test
    public void getSelect() {
        System.out.println(managerService.getSelect("WE001"));;
    }

    @Test
    public void setPhoneByWorkerId() {
        int n = managerService.setPhoneByWorkerId("WE001","119");
        assertEquals(1,n);
    }
}