package service;

import entity.Manager;

/**
 * @author 1
 */
public interface ManagerService {
    /**
     * 找管理员信息
     * @param aId
     * @return
     */
    Manager getSelect(String aId);

    /**
     *  通过工号修改手机号
     * @param workerId
     * @param phones
     * @return
     */
    int setPhoneByWorkerId(String workerId, String phone);
}
