package factory;

import entity.Book;
import service.*;
import service.impl.*;

import java.util.List;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }
    public static AdminService getAdminServiceInstance(){
        return new AdminServiceImpl();
    }
    public static BookService getBookServiceInstance() { return new BookServiceImpl(); }
    public static ManagerService getManagerServiceInstance(){ return new ManagerServiceImpl();  }
    public static StudentService getStudentServiceInstance(){ return new StudentServiceImpl();}
}
