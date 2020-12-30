package frame;

import sun.rmi.runtime.Log;

import javax.swing.*;
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
    JFrame frame;

    public void init(){
        frame = new JFrame("Login");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setSize(800,600);
        this.setVisible(true);

        l1.setFont(new Font("幼圆", Font.BOLD, 24));
        l2.setFont(new Font("幼圆",Font.BOLD,24));
    }

    public Login(){
        init();
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);

        登录Button.addActionListener( e -> {
            System.out.println(rb1.isSelected());
            //获得输入的账号和密码，注意密码框组件的使用方法
            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            //ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account, password);
            //JOptionPane.showMessageDialog(mainPanel, resultEntity.getMessage());
            //if (resultEntity.getCode() == 0) {
            //    //关闭登录界面，进入主界面
            //    this.dispose();
            //    Admin admin = (Admin) resultEntity.getData();
            //    new MainFrame(admin.getAdminName());
            //} else {
            //    accountField.setText("");
            //    passwordField.setText("");
            //}
        });
    }
    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            //TODO exception
        }
        //..................... 你的程序代码 .........................
        //..................... 你的程序代码 .........................
        new Login();
    }
}
