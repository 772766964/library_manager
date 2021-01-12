package frame;

import dao.UserDao;
import entity.account.User;
import factory.DaoFactory;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import sun.rmi.runtime.Log;
import sun.security.provider.MD5;
import utils.ResultEntity;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * @ClassName Register
 * @Description TODO
 * @Author 1
 * @Date 2020/12/31
 **/
public class Register extends JFrame {
    private JPanel mainPanel;
    private JLabel l1;
    private JLabel l2;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton 注册登录Button;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JTextField p1;
    private JTextField nameField;
    private JButton 清空Button;
    private JLabel l3;
    private JPasswordField passwordField1;
    private JTextField numberId;

    public void init(){
        JFrame frame = new JFrame("Register");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        //frame.setSize(200,400);

        this.setTitle("用户注册 -- 图书管理系统");


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
    public Register() {
        init();
        centerFrame();

        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        rb1.setSelected(true);

        清空Button.addActionListener(e -> {
            accountField.setText("");
            passwordField.setText("");
            passwordField1.setText("");
            nameField.setText("");
            numberId.setText("");
            rb1.setSelected(true);
            rb2.setSelected(false);
            rb3.setSelected(false);
        });
        注册登录Button.addActionListener(e -> {
            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String passTrue = new String(passwordField1.getPassword()).trim();
            String userName = accountField.getText();
            String uId = numberId.getText();
            if("".equals(account) && "".equals(password) && "".equals(userName) && "".equals(uId)) {
                new Login();
                this.dispose();
            }else if("".equals(account)  || "".equals(userName) || "".equals(uId) || "".equals(password)) {
                JOptionPane.showMessageDialog(null, "请勿为空");
            }else{
                if(password.equals(passTrue)){
                    //注册先查找账户
                    int result = ServiceFactory.getUserServiceInstance().userInsert(new User(uId.toUpperCase(),account, password,userName));

                    if(result==1){
                        JOptionPane.showMessageDialog(null,"注册成功");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        this.dispose();
                        new Login();

                    }else{
                        JOptionPane.showMessageDialog(null,"账号存在");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"密码不一致！");
                }} 
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
        new Register();
    }
}
