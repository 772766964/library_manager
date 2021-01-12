package frame;

import entity.Book;
import entity.Student;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName BookInfo
 * @Description TODO
 * @Author 1
 * @Date 2021/1/11
 **/
public class BookInfo extends JFrame {

    private JPanel mainPanel;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel q1;
    private JLabel q2;
    private JLabel q3;
    private JLabel q5;
    private JLabel q4;
    private JButton 关闭Button;
    private JButton 借阅Button;
    JFrame frame;

    public BookInfo(Book book, Student stu){
        init();
        centerFrame();
        //l1.setText("<html><img src='http://image12.bookschina.com/2013/20130315/B5678585.jpg'></html>");
        l1.setText("<html><img src='"+ book.getUrl_img() +"'></html>");
        q1.setText(book.getISBN());
        q2.setText(book.getBookName());
        q3.setText(book.getAuthor());
        q4.setText(book.getPress());
        q5.setText(book.getSynopsis());

        this.setTitle(book.getBookName());
        关闭Button.addActionListener(e->{
            this.dispose();
        });
        借阅Button.addActionListener(e->{
            if(stu!=null){
                //if书！=0
                JOptionPane.showMessageDialog(null,"借阅成功");
            }else{
                System.out.println("ERROE:账户错误！");
                JOptionPane.showMessageDialog(null,"ERROE:账户错误！");
            }
        });
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

    public void init(){
        frame = new JFrame("BookInfo");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        //this.pack();
        setVisible(true);
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
        new BookInfo(ServiceFactory.getBookServiceInstance().getSelect().get(0),null);
    }
}
