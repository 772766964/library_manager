package service;

import factory.ServiceFactory;
import org.junit.Test;
import utils.ResultEntity;

import static org.junit.Assert.*;

public class AdminServiceTest {

    private final AdminService adminService = ServiceFactory.getAdminServiceInstance();

    @Test
    public void adminLogin() {
        ResultEntity resultEntity = adminService.adminLogin("qianshang","qianshang");
        assertEquals(0,resultEntity.getCode());
        System.out.println(resultEntity);
    }
}