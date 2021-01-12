package frame;

import entity.Book;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import thread.LoopJscThread;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @ClassName test
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public class test extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JPanel bookPanels;
    private JScrollPane scroll;

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
        new test();
    }

    public test() {
        init();
        //showBooks();
        showBookTable();
        
        button1.addActionListener(e->{
            Scrollbar bar = new Scrollbar();
            bar.setValue(10);
            scroll.add(bar);


            LoopJscThread t = new LoopJscThread();
            t.setjScrollPane(scroll);
            t.start();
        });
    }


    public void showBookTable() {
        java.util.List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
        String[] columnNames = {"书籍编号", "书名", "作者", "出版社", "详情简述", "图书url" , "剩余图书"};
        // 定义表格数据数组
        Object[][] tableValues = bookArr(bookList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues,columnNames);
        //添加
        scroll.setViewportView(table);
    }

    //public void showBookTable(java.util.List<Book> bookList) {
    //    String[] columnNames = {"书籍编号", "书名", "作者", "出版社", "详情简述", "图书url" , "剩余图书"};
    //    // 定义表格数据数组
    //    String[][] tableValues = bookArr(bookList);
    //    // 创建指定列名和数据的表格
    //    JTable table = new JTable(tableValues,columnNames);
    //    //添加
    //    scroll.setViewportView(table);
    //}

    //转成二维
    public Object[][] bookArr(List<Book> bookList){
        Object[][] s= new Object[bookList.size()][7];
        int i = 0;
        for (Book book:bookList
        ) {
            s[i][0]=book.getISBN();
            s[i][1]=book.getBookName();
            s[i][2]=book.getAuthor();
            s[i][3]=book.getPress();
            s[i][4]=book.getSynopsis();
            s[i][5]=book.getUrl_img();



            //删除按钮
            JButton selBtn = new JButton("查询");
            selBtn.setPreferredSize(new Dimension(600,40));
            selBtn.addActionListener(e -> {
                new BookInfo(book,null);
            });

            s[i][6]=selBtn;
            i++;
        }
        return s;
    }


    /*private void showBooks(){
        //移除原有数据
        bookPanels.removeAll();
        //从service层获取所有院系列表
        java.util.List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
        //获取总数
        int len = bookList.size();
        //根据书总数算出行数，（每行定义n个）
        int row = len % 3 == 0 ? len / 3 : len / 3+1 ;
        //创建一个网格布局
        GridLayout gridLayout = new GridLayout(1,len,50,50);
        bookPanels.setLayout(gridLayout);
        for(Book book : bookList){
            //给每本书对象创建一个面板
            JPanel bkPanel = new JPanel();
            //设置合适大小
            bkPanel.setPreferredSize(new Dimension(200,390));
            //删除按钮
            JButton selBtn = new JButton("查询");
            selBtn.setPreferredSize(new Dimension(300,40));
            selBtn.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(this, "？");
                if (result == 0) {
                    //int n = ServiceFactory.getBookServiceInstance().delete(department.getId());
                    //if (n == 1) {
                    //    JOptionPane.showMessageDialog(this, "成功");
                    //    showBooks();
                    //}
                } else {
                    JOptionPane.showMessageDialog(this, "失败");
                }
            });
            //书名称设置给面板标题
            bkPanel.setBorder(BorderFactory.createTitledBorder(book.getBookName()));
            //新建JLabel标签，放书图片
            JLabel bookNameLabel = new JLabel("<html><img src ='"+ book.getUrl_img()+"' width='10px;' height='10px;'/></html>");
            //图标标签放入书本面板
            bkPanel.add(bookNameLabel, BorderLayout.NORTH);
            bkPanel.add(selBtn,BorderLayout.SOUTH);
            //院系面板放入主题内容面板
            bookPanels.add(bkPanel,BorderLayout.CENTER);
            //刷新主题内容面板
            bookPanels.revalidate();
        }
    }*/

    private void init() {
        JFrame frame = new JFrame("test");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.pack();
        this.setSize(800,600);
        this.setVisible(true);
    }
}
