package frame;

import entity.Book;
import entity.account.Admin;
import factory.ServiceFactory;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @ClassName AddFrame
 * @Description TODO
 * @Author 1
 * @Date 2021/1/12
 **/
public class AddFrame extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton 清空退出Button;
    private JButton 添加Button;
    private JPanel mainPanel;

    JFrame frame;
    public void init(){
        frame = new JFrame("添加");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setSize(800,600);
        this.setTitle("登录 -- 图书管理系统");



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

    public AddFrame(Admin admin) {
        init();
        centerFrame();

        SimpleDateFormat sdf =  new  SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(new Date());
        textField5.setText(dateNowStr);

        清空退出Button.addActionListener( e->{
            if("".equals(textField1.getText())&&"".equals(textField2.getText())
                    &&"".equals(textField3.getText())
            &&"".equals(textField4.getText())&&"".equals(textField5.getText())
                    &&"".equals(textField6.getText())
            &&"".equals(textField7.getText())&&"".equals(textField8.getText())){
                this.dispose();
                new Login();
            }else{
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
            }
        });
        添加Button.addActionListener( e->{
            if("".equals(textField1.getText())&&"".equals(textField2.getText())
                    &&"".equals(textField3.getText())
                    &&"".equals(textField4.getText())&&"".equals(textField5.getText())
                    &&"".equals(textField6.getText())
                    &&"".equals(textField7.getText())&&"".equals(textField8.getText())){
                JOptionPane.showMessageDialog(null,"数据不能为空！");
            }else{
                Book bk = new Book();
                bk.setISBN(textField1.getText());
                bk.setBookName(textField2.getText());
                bk.setAuthor(textField3.getText());
                bk.setPress(textField4.getText());
                bk.setPressDate(textField5.getText());
                bk.setSynopsis(textField6.getText());
                bk.setUrl_img(textField7.getText());
                bk.setCount(textField8.getText());
                boolean b = ServiceFactory.getBookServiceInstance().insertBook(bk);
                if(b){
                    JOptionPane.showMessageDialog(null,"添加成功");
                }else{
                    JOptionPane.showMessageDialog(null,"添加失败");
                }}
        });
    }

    public static void main(String[] args) {
        new AddFrame(ServiceFactory.getAdminServiceInstance().getSel("WE001"));
    }
}
