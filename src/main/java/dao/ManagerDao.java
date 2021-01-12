package dao;

import entity.Manager;

import javax.swing.plaf.SliderUI;
import java.sql.SQLException;

/**
 * @author 1
 */
public interface ManagerDao {
    /**
     *获取当前管理员
     * @return manager
     * @throws SQLException
     */
    Manager getManager(String workerId) throws SQLException;

    /**
     * 修改电话
     * @param workerId
     * @param phone
     * @return
     * @throws SQLException
     */
    int setPhone(String workerId,String phone) throws SQLException;
}
