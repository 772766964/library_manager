package frame;

import entity.Manager;
import entity.account.Admin;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import service.ManagerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName PersonalFrame
 * @Description TODO
 * @Author 1
 * @Date 2021/1/5
 **/
public class PersonalFrame extends JFrame {
    private JLabel l1;
    private JButton 关闭Button;
    private JPanel mainPanel;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JButton 修改Button;

    JFrame frame;
    public void init(){
        frame = new JFrame("PersonalFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        this.setSize(800,600);
        this.setVisible(true);

        //this.setTitle();
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
    public PersonalFrame(Admin admin){
        init();
        centerFrame();
        Manager manager = ServiceFactory.getManagerServiceInstance().getSelect(admin.getAId());
        this.setTitle("管理员账号名："+manager.getName());
        t1.setText(manager.getWorkerId());
        t2.setText(manager.getName());
        t3.setText(manager.getSex());
        t4.setText(manager.getPhones());

        修改Button.addActionListener( e -> {
            String phone = t4.getText();
            int n =ServiceFactory.getManagerServiceInstance().setPhoneByWorkerId(admin.getAId(),t4.getText());
            if(n==1){
                JOptionPane.showMessageDialog(null,"修改成功");
            }else{
                JOptionPane.showMessageDialog(null,"修改失败");
            }
        });
        关闭Button.addActionListener( e -> {
            this.dispose();
            //System.out.println("个人中心"+admin);
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
        //new PersonalFrame("WE001");
    }

}
