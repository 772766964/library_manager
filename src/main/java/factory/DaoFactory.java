package factory;

import dao.*;
import dao.impl.*;
import entity.Manager;
import entity.Student;
import entity.account.User;

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
    public static AdminDao getAdminDaoInstance(){
        return new AdminDaoImpl();
    }
    public static BookDao getBookDaoInstance(){  return new BookDaoImpl(); }
    public static ManagerDao getManagerInstance(){ return new ManagerDaoImpl(); }
    public static StudentDao getStudentInstance(){  return new StudentDaoImpl();  }
}
