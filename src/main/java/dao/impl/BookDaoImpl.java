package dao.impl;

import dao.BookDao;
import entity.Book;
import utils.JdbcUtil;
import utils.ResultEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookDaoImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> getAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "Select * from Book";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setISBN(rs.getString(1));
            book.setBookName(rs.getString(2));
            book.setAuthor(rs.getString(3));
            book.setPress(rs.getString(4));
            book.setPressDate(rs.getString(5));
            book.setSynopsis(rs.getString(6));
            book.setUrl_img(rs.getString(7));
            book.setCount(rs.getString(8));
            bookList.add(book);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return bookList;
    }

    @Override
    public List<Book> getDirAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "Select * from Book where synopsis like '%思政%'";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setISBN(rs.getString(1));
            book.setBookName(rs.getString(2));
            book.setAuthor(rs.getString(3));
            book.setPress(rs.getString(4));
            book.setPressDate(rs.getString(5));
            book.setSynopsis(rs.getString(6));
            book.setUrl_img(rs.getString(7));
            book.setCount(rs.getString(8));
            bookList.add(book);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return bookList;
    }

    @Override
    public List<Book> researchBookAll(String str, boolean swi) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql = "";
        if(swi){
            sql = "Select * from Book where bookName like '%"+str+"%'";
        }else{
            sql = "Select * from Book where author like '%"+str+"%'";
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while(rs.next()){
            Book book = new Book();
            book.setISBN(rs.getString(1));
            book.setBookName(rs.getString(2));
            book.setAuthor(rs.getString(3));
            book.setPress(rs.getString(4));
            book.setPressDate(rs.getString(5));
            book.setSynopsis(rs.getString(6));
            book.setUrl_img(rs.getString(7));
            book.setCount(rs.getString(8));
            bookList.add(book);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return bookList;
    }

    @Override
    public boolean insertBook(Book book) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        //insert into BOOK values
        //('NG001','中国中篇小说经典','吴义勤','凤凰出版社','2009-06-08','故事集','url',3)
        Connection conn = jdbcUtil.getConnection();
        String sql = "insert into BOOK values (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,book.getISBN());
        pstmt.setString(2,book.getBookName());
        pstmt.setString(3,book.getAuthor());
        pstmt.setString(4,book.getPress());
        pstmt.setString(5,book.getPressDate());
        pstmt.setString(6,book.getSynopsis());
        pstmt.setString(7,book.getUrl_img());
        pstmt.setString(8,book.getCount());
        int rs = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rs==1;
    }

    //Book bk = null;
    //while(rs.next()){
    //    bk=new Book();
    //    bk.setISBN(rs.getString(1));
    //    bk.setBookName(rs.getString(2));
    //    bk.setAuthor(rs.getString(3));
    //    bk.setPress(rs.getString(4));
    //    bk.setPressDate(rs.getString(5));
    //    bk.setSynopsis(rs.getString(6));
    //    bk.setUrl_img(rs.getString(7));
    //    bk.setCount(rs.getString(8));
    //}
    //rs.close();

    @Override
    public boolean updateBook(Book book) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection conn = jdbcUtil.getConnection();
        String sql ="update book set synopsis=? ,url_img=?,count=? where ISBN=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(4,book.getISBN());
        pstmt.setString(1,book.getSynopsis());
        pstmt.setString(2,book.getUrl_img());
        pstmt.setString(3,book.getCount());
        int rs = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rs==1;
    }

    @Override
    public boolean delBook(String ISBN) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        //insert into BOOK values
        //('NG001','中国中篇小说经典','吴义勤','凤凰出版社','2009-06-08','故事集','url',3)
        Connection conn = jdbcUtil.getConnection();
        String sql = " delete from book where ISBN=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,ISBN);
        int rs = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rs==1;
    }
}
