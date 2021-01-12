package service.impl;

import dao.ManagerDao;
import entity.Manager;
import factory.DaoFactory;
import service.ManagerService;

import java.sql.SQLException;

/**
 * @ClassName ManagerServiceImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public class ManagerServiceImpl implements ManagerService {
    private final ManagerDao managerDao = DaoFactory.getManagerInstance();

    @Override
    public Manager getSelect(String aId) {

        Manager manager=null;
        try {
            manager = managerDao.getManager(aId);
        } catch (SQLException e) {
            System.err.println("查找管理员出现异常");
        }
        return manager;
    }

    @Override
    public int setPhoneByWorkerId(String workerId, String phone) {
        int n = 0;
        try {
            n = managerDao.setPhone(workerId,phone);
        } catch (SQLException e) {
            System.err.println("设置手机号出现异常");
        }
        return n;
    }
}
