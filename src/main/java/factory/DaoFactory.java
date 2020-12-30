package factory;

import dao.UserDao;
import dao.impl.UserDaoImpl;

/**
 * @ClassName DaoFactory
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29{
 **/
public class DaoFactory {
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
}
