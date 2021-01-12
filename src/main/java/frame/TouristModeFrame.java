package frame;

import entity.Book;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import thread.LoopJscThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @ClassName TouristModeFrame
 * @Description TODO
 * @Author 1
 * @Date 2021/1/5
 **/
public class TouristModeFrame extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton 检索Button;
    private JButton 登录Button;
    private JButton 注册Button;
    private JPanel bookPanels;
    private JScrollPane jscrollPanel;
    private JPanel bookPlays;
    private JScrollPane scrollPane;
    private JPanel bottomPanel;
    private JScrollPane scroll;


    //自定义


    private JLabel idLabel;
    private JLabel avatar;
    private JTextField depName;
    private JTextField className;
    private JTextField stuName;
    private JLabel gender;
    private JLabel birthday;
    private JTextField phone;
    private JTextField address;
    private JButton editBtn;
    private JButton delBtn;

    public void init(){
        JFrame frame = new JFrame("TouristModeFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1660,600);
        //this.pack();
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

    public TouristModeFrame(){
        this.setTitle("--游客界面--");
        init();
        centerFrame();
        showBooks();
        loopBooks();

        showBookTable();

        LoopJscThread t = new LoopJscThread();
        t.setjScrollPane(scrollPane);
        t.start();

        jscrollPanel.getVerticalScrollBar().setUnitIncrement(70);
        scrollPane.getVerticalScrollBar().setVisibleAmount(10);
        scrollPane.getVerticalScrollBar().setValue(0);

        登录Button.addActionListener( e -> {
            new Login();
            this.dispose();
        });
        注册Button.addActionListener( e -> {
            new Register();
            this.dispose();
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
    }

    public void showBookTable() {
        List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
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
                new BookInfo(book,null);
                //JOptionPane.showMessageDialog(null,table.getValueAt(row,1).toString()+
                //        table.getValueAt(row,2).toString());
            }
        });
        //添加
        scroll.setViewportView(table);
    }
    public void showBookTable(List<Book> bookList) {
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

    //总览
    private void showBooks(){
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
                new BookInfo(book,null);
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
    private void loopBooks(){
        //移除原有数据
        bookPlays.removeAll();
        //从service层获取所有院系列表
        java.util.List<Book> bookList = ServiceFactory.getBookServiceInstance().getSiZheng();
        //获取总数
        int len = bookList.size();
        //创建一个网格布局
        GridLayout gridLayout = new GridLayout(1,len,25,50);
        bookPlays.setLayout(gridLayout);
        for(Book book : bookList){
            //给每本书对象创建一个面板
            JPanel bkPanel = new JPanel();
            //设置合适大小
            bkPanel.setPreferredSize(new Dimension(500,600));
            //删除按钮
            JButton selBtn = new JButton("查询");
            selBtn.setPreferredSize(new Dimension(600,40));
            selBtn.addActionListener(e -> {
                new BookInfo(book,null);
            });
            //书名称设置给面板标题
            bkPanel.setBorder(BorderFactory.createTitledBorder(book.getBookName()));
            //新建JLabel标签，放书图片
            JLabel bookNameLabel = new JLabel("<html><img style='height: 20px;width: 20px;'src ='"+ book.getUrl_img()+"'/></html>");
            //图标标签放入书本面板
            bkPanel.add(bookNameLabel, BorderLayout.NORTH);
            bkPanel.add(selBtn,BorderLayout.SOUTH);
            //院系面板放入主题内容面板
            bookPlays.add(bkPanel,BorderLayout.CENTER);
            //刷新主题内容面板
            bookPlays.revalidate();
        }
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
        new TouristModeFrame();
    }
}
