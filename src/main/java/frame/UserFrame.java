package frame;

import entity.Book;
import entity.Student;
import factory.ServiceFactory;
import thread.LoopJscThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @ClassName UserFrame
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class UserFrame extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton 检索Button;
    private JScrollPane scroll;
    private JScrollPane scr;
    private JPanel bookPlayss;
    private JScrollPane jscrollPanel;
    private JPanel bookPanels;
    private JLabel l2;
    private JLabel l4;
    private JLabel q1;
    private JLabel q3;
    private JLabel l3;
    private JLabel q2;
    private JLabel l1;
    private JLabel q4;
    private JLabel q5;
    private JButton 隐藏Button;
    private JButton 个人信息Button;
    private JPanel leftPanel;
    private JLabel q6;
    private JLabel q7;
    private JScrollPane jscrollPanel1;
    private JPanel bookPanelss;
    private JButton 注销Button;
    private JLabel ll;
    private JFrame frame;

    public void init(String title){
        frame = new JFrame("UserFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        this.setSize(1660,800);
        this.setVisible(true);

        this.setTitle(title);
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

    //总览
    private void showBooks(Student stu){
        //移除原有数据
        bookPanels.removeAll();
        //从service层获取所有院系列表
        List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
        //获取总数
        int len = bookList.size();
        //根据书总数算出行数，（每行定义n个）
        int row = len % 3 == 0 ? len / 3 : len / 3+1 ;
        //创建一个网格布局
        GridLayout gridLayout = new GridLayout(row,3,50,50);
        bookPanels.setLayout(gridLayout);
        for(Book book : bookList){
            //给每本书对象创建一个面板
            JPanel bkPanel = new JPanel();
            //设置合适大小
            bkPanel.setPreferredSize(new Dimension(200,390));
            //查看按钮
            JButton selBtn = new JButton("查询");
            selBtn.setPreferredSize(new Dimension(300,40));
            selBtn.addActionListener(e -> {
                new BookInfo(book,stu);
            });
            //书名称设置给面板标题
            bkPanel.setBorder(BorderFactory.createTitledBorder(book.getBookName()));
            //新建JLabel标签，放书图片
            JLabel bookNameLabel = new JLabel("<html><img src ='"+ book.getUrl_img()+"' width='10;' height='10;'/></html>");
            //图标标签放入书本面板
            bkPanel.add(bookNameLabel,BorderLayout.NORTH);
            bkPanel.add(selBtn,BorderLayout.SOUTH);
            //院系面板放入主题内容面板
            bookPanels.add(bkPanel,BorderLayout.CENTER);
            //刷新主题内容面板
            bookPanels.revalidate();
        }
    }

    //轮播-思政
    private void loopBooks(Student stu){
        //移除原有数据
        bookPlayss.removeAll();
        //从service层获取所有院系列表
        java.util.List<Book> bookList = ServiceFactory.getBookServiceInstance().getSiZheng();
        //获取总数
        int len = bookList.size();
        //创建一个网格布局
        GridLayout gridLayout = new GridLayout(2,len,25,50);
        bookPlayss.setLayout(gridLayout);
        for(Book book : bookList){
            //给每本书对象创建一个面板
            JPanel bkPanel = new JPanel();
            //设置合适大小
            bkPanel.setPreferredSize(new Dimension(500,300));
            //删除按钮
            JButton selBtn = new JButton("查询");
            selBtn.setPreferredSize(new Dimension(600,40));
            selBtn.addActionListener(e -> {
                new BookInfo(book,stu);
            });
            //书名称设置给面板标题
            bkPanel.setBorder(BorderFactory.createTitledBorder(book.getBookName()));
            //新建JLabel标签，放书图片
            JLabel bookNameLabel = new JLabel("<html><img height=230 width=200 'src ='"+ book.getUrl_img()+"'/></html>");
            //图标标签放入书本面板
            bkPanel.add(bookNameLabel, BorderLayout.NORTH);
            bkPanel.add(selBtn,BorderLayout.SOUTH);
            //院系面板放入主题内容面板
            bookPlayss.add(bkPanel,BorderLayout.CENTER);
            //刷新主题内容面板
            bookPlayss.revalidate();
        }
    }

    public UserFrame(Student student){
        init("用户名："+student.getReaderName());
        centerFrame();
        showBookTable(student);
        //总览
        showBooks(student);
        //轮播
        loopBooks(student);

        LoopJscThread t = new LoopJscThread();
        t.setjScrollPane(scr);
        t.start();
        jscrollPanel1.getVerticalScrollBar().setUnitIncrement(100);
        jscrollPanel1.getVerticalScrollBar().setBlockIncrement(100);

        l1.setText("<html><img src='"+student.getImg()+"'mode='scaleToFill' width=200 height=200 /></html>");
        //l1.setBounds(120,110,50,50);

        q1.setText(student.getReaderId());
        q2.setText(student.getReaderName());
        q3.setText(student.getReaderSex());
        q4.setText(student.getPhone());
        q5.setText(student.getDept());
        q6.setText(student.getSituation());
        q7.setText(student.getBorrowAdd());
        隐藏Button.addActionListener(e->{
            leftPanel.setVisible(false);
        });
        个人信息Button.addActionListener(e->{
            leftPanel.setVisible(true);
        });
        检索Button.addActionListener( e->{
            if("".equals(textField1.getText().trim())){
                showBookTable(ServiceFactory.getBookServiceInstance().getSelect());
                System.out.println("if");
            }else{
                boolean swi= comboBox1.getSelectedIndex()==1?false:true;
                showBookTable(ServiceFactory.getBookServiceInstance().getResearch(textField1.getText(),swi));
                System.out.println("else");
            }
        });
        注销Button.addActionListener(e->{
            this.dispose();
            new Login();
        });
    }

    public void showBookTable(Student stu) {
        java.util.List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
        String[] columnNames = {"书籍编号", "书名", "作者", "出版社", "详情简述", "图书url" , "剩余图书"};
        // 定义表格数据数组
        String[][] tableValues = bookArr(bookList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues,columnNames);
        table.getSelectionModel().addListSelectionListener(e->{
            //getValueIsAdjusting  一次
            if(e.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                Book book = ServiceFactory.getBookServiceInstance().getResearch(table.getValueAt(row,1).toString(),true).get(0);
                //System.out.println(book);
                new BookInfo(book,stu);
                //JOptionPane.showMessageDialog(null,table.getValueAt(row,1).toString()+
                //        table.getValueAt(row,2).toString());
            }
        });
        //添加
        scroll.setViewportView(table);
    }
    public void showBookTable(java.util.List<Book> bookList) {
        String[] columnNames = {"书籍编号", "书名", "作者", "出版社", "详情简述", "图书url" , "剩余图书"};
        // 定义表格数据数组
        String[][] tableValues = bookArr(bookList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues,columnNames);
        //添加
        scroll.setViewportView(table);
    }

    //转成二维
    public String[][] bookArr(List<Book> bookList){
        String[][] s= new String[bookList.size()][7];
        int i = 0;
        for (Book book:bookList
        ) {
            s[i][0]=book.getISBN();
            s[i][1]=book.getBookName();
            s[i][2]=book.getAuthor();
            s[i][3]=book.getPress();
            s[i][4]=book.getSynopsis();
            s[i][5]=book.getUrl_img();
            s[i][6]=book.getCount();
            i++;
        }
        return s;
    }



    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            //TODO exception
        }
        //..................... 你的程序代码 .........................
        //..................... 你的程序代码 .........................
        new UserFrame(ServiceFactory.getStudentServiceInstance().loginStu("RE001"));
    }
}
