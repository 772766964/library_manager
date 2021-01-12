package frame;

import com.sun.awt.AWTUtilities;
import entity.Student;
import entity.account.Admin;
import entity.account.User;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.ResultEntity;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName Login
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton 登录Button;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JLabel l1;
    private JLabel l2;
    private JButton 注册Button;
    private JButton 游Button;
    JFrame frame;

    //自定义变量

    public void init(){
        frame = new JFrame("Login");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setSize(1024,600);
        this.setTitle("登录 -- 图书管理系统");

        l1.setFont(new Font("幼圆", Font.BOLD, 24));
        l2.setFont(new Font("幼圆",Font.BOLD,24));


        // set frame full transparent
        //this.setUndecorated(true);
        //AWTUtilities.setWindowOpaque(this, true);
        //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);


        this.setVisible(true);
    }

    public void centerFrame(){
        int windowWidth = this.getWidth();
        int windowHeight = this.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    public Login(){
        init();
        centerFrame();
        ButtonGroup group = new ButtonGroup();
        rb1.setSelected(true);
        group.add(rb1);
        group.add(rb2);

        登录Button.addActionListener( e -> {

            //获得输入的账号和密码，注意密码框组件的使用方法
            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            //判断是管理员还是用户
            boolean swi = rb1.isSelected();
            //true -- 管理员      false -- 用户
            if(swi){
                ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account,password);
                JOptionPane.showMessageDialog(mainPanel,resultEntity.getMessage());
                if (resultEntity.getCode() == 0) {
                    //关闭登录界面，进入主界面
                    //this.dispose();
                    Admin admin = (Admin) resultEntity.getData();
                    new AdminFrame(admin);
                } else {
                    accountField.setText("");
                    passwordField.setText("");
                }
            }else{
                ResultEntity resultEntity = ServiceFactory.getUserServiceInstance().userLogin(account, password);
                JOptionPane.showMessageDialog(mainPanel, resultEntity.getMessage());
                if (resultEntity.getCode() == 0) {
                    //关闭登录界面，进入主界面
                    //this.dispose();
                    User user = (User) resultEntity.getData();
                    new UserFrame(ServiceFactory.getStudentServiceInstance().loginStu(user.getUId()));
                } else {
                    accountField.setText("");
                    passwordField.setText("");
                }
            }
        });
        注册Button.addActionListener( e -> {
            new Register();
            //this.dispose();
        });
        游Button.addActionListener( e->{
            new TouristModeFrame();
            //this.dispose();
        });
    }
    public static void main(String[] args) {
        try {
            //设置本属性将改变窗口边框样式定义
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            //去除‘设置’
            UIManager.put("RootPane.setupButtonVisible", false);

            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            //TODO exception
        }
        //..................... 你的程序代码 .........................
        //..................... 你的程序代码 .........................
        new Login();
    }

}
