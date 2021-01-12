package frame;

import entity.Book;
import entity.Manager;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName BookAlter
 * @Description TODO
 * @Author 1
 * @Date 2021/1/12
 **/
public class BookAlter extends JFrame {
    private JLabel l2;
    private JLabel l4;
    private JLabel q1;
    private JLabel q3;
    private JLabel l3;
    private JLabel q2;
    private JLabel l1;
    private JLabel q4;
    private JLabel q5;
    private JButton 关闭Button;
    private JButton 修改Button;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;
    private JPanel mainPanel;


    public BookAlter(Book book, Manager manager){
        init();
        centerFrame();
        //l1.setText("<html><img src='http://image12.bookschina.com/2013/20130315/B5678585.jpg'></html>");
        textField1.setText(book.getISBN());
        textField2.setText(book.getBookName());
        textField3.setText(book.getAuthor());
        textField4.setText(book.getPress());
        t5.setText(book.getSynopsis());
        t6.setText(book.getUrl_img());
        t7.setText(book.getCount());

        this.setTitle(book.getBookName());
        关闭Button.addActionListener(e->{
            this.dispose();
        });
        修改Button.addActionListener(e->{
            if(manager!=null){
                //if书！=0
                book.setSynopsis(t5.getText());
                book.setUrl_img(t6.getText());
                book.setCount(t7.getText());
                boolean b = ServiceFactory.getBookServiceInstance().updateBook(book);
                if(b){
                    JOptionPane.showMessageDialog(null,"修改成功");
                }
            }else{
                System.out.println("ERROE:账户错误！");
                JOptionPane.showMessageDialog(null,"ERROE:无管理员错误！");
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

    JFrame frame;
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
        new BookAlter(ServiceFactory.getBookServiceInstance().getSelect().get(0),ServiceFactory.getManagerServiceInstance().getSelect("WE001"));
    }
}
