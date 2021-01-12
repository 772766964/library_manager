package frame;

import entity.Book;
import entity.Student;
import entity.account.Admin;
import factory.ServiceFactory;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @ClassName AdminFrame
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class AdminFrame extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton 个人中心Button;
    private JButton btn;
    private JTextField t1;
    private JComboBox c1;
    private JScrollPane stuScroll;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton 检索Button;
    private JScrollPane scroll;
    private JButton 注销Button;
    private JButton 新增图书Button;
    private JPanel jP;
    private JFrame frame;


    public void showStuTable() {
        List<Student> stuList = ServiceFactory.getStudentServiceInstance().getStu(true,"RE");
        String[] columnNames = {"学生编号", "学生姓名", "性别", "手机", "系别", "是否有不良记录" , "借书总数","头像"};
        // 定义表格数据数组
        String[][] tableValues = stuArr(stuList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues,columnNames);
        table.getSelectionModel().addListSelectionListener(e->{
            //getValueIsAdjusting  一次
            if(e.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                new ShowStuInfo(ServiceFactory.getStudentServiceInstance().loginStu(table.getValueAt(row,0).toString()));
                //this.dispose();
            }
        });

        //添加
        stuScroll.setViewportView(table);
    }
    public void showStuTable(List<Student> stuList) {
        String[] columnNames = {"学生编号", "学生姓名", "性别", "手机", "系别", "是否有不良记录" , "借书总数","头像"};
        // 定义表格数据数组
        String[][] tableValues = stuArr(stuList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(tableValues,columnNames);
        table.getSelectionModel().addListSelectionListener(e->{
            //getValueIsAdjusting  一次
            if(e.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                new ShowStuInfo(ServiceFactory.getStudentServiceInstance().loginStu(table.getValueAt(row,0).toString()));
                //this.dispose();
            }
        });
        //添加
        stuScroll.setViewportView(table);
    }
    //转成二维
    public String[][] stuArr(List<Student> stuList){
        String[][] s= new String[stuList.size()][8];
        int i = 0;
        for (Student stu:stuList
        ) {
            s[i][0]=stu.getReaderId();
            s[i][1]=stu.getReaderName();
            s[i][2]=stu.getReaderSex();
            s[i][3]=stu.getPhone();
            s[i][4]=stu.getDept();
            s[i][5]=stu.getSituation();
            s[i][6]=stu.getBorrowAdd();
            s[i][7]=stu.getImg();
            i++;
        }
        return s;
    }

    public void init(String name){
        frame = new JFrame("AdminFrame");
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

    static Admin a = null;
    AdminFrame(Admin admin){
        a = admin;
        init("管理员："+admin.getAdmin_name());
        centerFrame();
        showStuTable();
        showBookTable();



        个人中心Button.addActionListener( e -> {
            new PersonalFrame(admin);
            //System.out.println("管理界面："+admin);
        });
        btn.addActionListener(e->{
            List<Student> studentList =null;
            if(c1.getSelectedIndex()==0){
                showStuTable(ServiceFactory.getStudentServiceInstance().getStu(true,t1.getText()));
            }else{
                showStuTable(ServiceFactory.getStudentServiceInstance().getStu(false,t1.getText()));
            }
            System.out.println("点击");
        });

        检索Button.addActionListener( e->{
            if(comboBox1.getSelectedIndex()==0){
                showBookTable(ServiceFactory.getBookServiceInstance().getResearch(textField1.getText(),true));
            }else{
                showBookTable(ServiceFactory.getBookServiceInstance().getResearch(textField1.getText(),false));
            }
        });
        注销Button.addActionListener( e->{
            this.dispose();
            new Login();
        });
        新增图书Button.addActionListener(e->{
            new AddFrame(admin);
            this.dispose();
        });
    }


    public void showBookTable() {
        List<Book> bookList = ServiceFactory.getBookServiceInstance().getSelect();
        String[] columnNames = {"书籍编号", "书名", "作者", "出版社", "详情简述", "图书url" , "剩余图书"};
        // 定义表格数据数组
        String[][] tableValues = bookArr(bookList);
        // 创建指定列名和数据的表格
        JTable table = new JTable(new DefaultTableModel(tableValues,columnNames));
        table.getSelectionModel().addListSelectionListener(e->{
            //getValueIsAdjusting  一次
            if(e.getValueIsAdjusting()){
                int row = table.getSelectedRow();
                Book book = ServiceFactory.getBookServiceInstance().getResearch(table.getValueAt(row,1).toString(),true).get(0);
                //System.out.println(book);
                new BookAlter(book,ServiceFactory.getManagerServiceInstance().getSelect(a.getAId()));
                //this.dispose();
                //System.out.println("已经点击");
                JOptionPane.showMessageDialog(null,table.getValueAt(row,1).toString()+
                        table.getValueAt(row,2).toString());
            }
        });
        //添加
        scroll.setViewportView(table);

        //右键菜单
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }
                int rowindex = table.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                JMenuItem modifyItem=null;
                JMenuItem deleteItem=null;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    //JPopupMenu popup = createYourPopUp();
                    JPopupMenu jPopupMenu = new JPopupMenu();
                    //modifyItem = new JMenuItem("修改");
                    deleteItem = new JMenuItem("删除");
                    //jPopupMenu.add(modifyItem);
                    jPopupMenu.add(deleteItem);
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }

                //修改
                /*
                modifyItem.addActionListener(e1 -> {
                    int row = table.getSelectedRow();
                    int choice = JOptionPane.showConfirmDialog(null, table.getValueAt(row,2));
                    //点击了"确定"
                    //if (choice == 0) {
                    //    //根据当前班级的id删除
                    //    int n = ServiceFactory.getClazzServiceInstance().deleteClazz(clazz.getId());
                    //    if (n == 1) {
                    //        //从list数据模型中移除当前项，先从视觉上看到删除效果
                    //        listModel.remove(index);
                    //        //走后端重新调用下数据
                    //        showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                    //    }
                    //}
                });*/
                //删除
                deleteItem.addActionListener(e1 -> {
                    int row = table.getSelectedRow();
                    int choice = JOptionPane.showConfirmDialog(null, "您确定要删除编号为："+table.getValueAt(row,0).toString()+"的数据嘛？");
                    //点击了"确定"
                    if (choice == 0) {
                        //根据当前班级的id删除
                        boolean n = ServiceFactory.getBookServiceInstance().delBook(table.getValueAt(row,0).toString());
                        if (n) {
                            //从list数据模型中移除当前项，先从视觉上看到删除效果
                            showBookTable();
                            //listModel.remove(index);
                            ////走后端重新调用下数据
                            //showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                        }
                    }
                });

            }
        });


    }
    public void showBookTable(List<Book> bookList) {
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
                new BookAlter(book,ServiceFactory.getManagerServiceInstance().getSelect(a.getAId()));
                System.out.println("已经点击");
                //JOptionPane.showMessageDialog(null,table.getValueAt(row,1).toString()+
                //        table.getValueAt(row,2).toString());
            }
        });
        //添加
        scroll.setViewportView(table);


        //右键菜单
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }
                int rowindex = table.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                JMenuItem modifyItem=null;
                JMenuItem deleteItem=null;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    //JPopupMenu popup = createYourPopUp();
                    JPopupMenu jPopupMenu = new JPopupMenu();
                    //modifyItem = new JMenuItem("修改");
                    deleteItem = new JMenuItem("删除");
                    //jPopupMenu.add(modifyItem);
                    jPopupMenu.add(deleteItem);
                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
                }

                //修改
                /*
                modifyItem.addActionListener(e1 -> {
                    int row = table.getSelectedRow();
                    int choice = JOptionPane.showConfirmDialog(null, table.getValueAt(row,2));
                    //点击了"确定"
                    //if (choice == 0) {
                    //    //根据当前班级的id删除
                    //    int n = ServiceFactory.getClazzServiceInstance().deleteClazz(clazz.getId());
                    //    if (n == 1) {
                    //        //从list数据模型中移除当前项，先从视觉上看到删除效果
                    //        listModel.remove(index);
                    //        //走后端重新调用下数据
                    //        showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                    //    }
                    //}
                });*/
                //删除
                deleteItem.addActionListener(e1 -> {
                    int row = table.getSelectedRow();
                    int choice = JOptionPane.showConfirmDialog(null, "您确定要删除编号为："+table.getValueAt(row,0).toString()+"的数据嘛？");
                    //点击了"确定"
                    if (choice == 0) {
                        //根据当前班级的id删除
                        boolean n = ServiceFactory.getBookServiceInstance().delBook(table.getValueAt(row,0).toString());
                        if (n) {
                            //从list数据模型中移除当前项，先从视觉上看到删除效果
                            showBookTable();
                            //listModel.remove(index);
                            ////走后端重新调用下数据
                            //showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                        }
                    }
                });

            }
        });

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
        new AdminFrame(ServiceFactory.getAdminServiceInstance().getSel("zhaosi"));
    }


}
