package frame;

import entity.Student;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName showStuInfo
 * @Description TODO
 * @Author 1
 * @Date 2021/1/12
 **/
public class ShowStuInfo extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JLabel l4;
    private JLabel q1;
    private JLabel q3;
    private JLabel l3;
    private JLabel q2;
    private JLabel q4;
    private JLabel q5;
    private JLabel q6;
    private JLabel q7;
    private JLabel l2;
    private JLabel l1;
    private JButton 关闭Button;

    JFrame frame;
    public void init(String name){
        frame = new JFrame("show");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        this.setSize(1024,600);
        this.setTitle(name);

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

    public ShowStuInfo(Student student){
        init("读者id："+student.getReaderId());
        centerFrame();

        l1.setText("<html><img src='"+student.getImg()+"'mode='scaleToFill' width=200 height=200 /></html>");
        //l1.setBounds(120,110,50,50);

        q1.setText(student.getReaderId());
        q2.setText(student.getReaderName());
        q3.setText(student.getReaderSex());
        q4.setText(student.getPhone());
        q5.setText(student.getDept());
        q6.setText(student.getSituation());
        q7.setText(student.getBorrowAdd());
        关闭Button.addActionListener(e->{
            this.dispose();
        });
    }

    public static void main(String[] args) {
        try {
            //设置本属性将改变窗口边框样式定义
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;

            //去除‘设置’
            UIManager.put("RootPane.setupButtonVisible", false);

            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

            //改变InsetsUIResource参数的值即可实现
            UIManager.put("TabbedPane.tabAreaInsets"
                    , new javax.swing.plaf.InsetsUIResource(3,750,2,20));
        } catch (Exception e) {
            //TODO exception
        }
        //..................... 你的程序代码 .........................
        //..................... 你的程序代码 .........................
        new ShowStuInfo(ServiceFactory.getStudentServiceInstance().loginStu("RE001"));
    }
}
