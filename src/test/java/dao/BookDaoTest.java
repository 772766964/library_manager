package dao;

import entity.Book;
import factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private final BookDao bookDao = DaoFactory.getBookDaoInstance();

    @Test
    public void getAll() {
        List<Book> bookList = null;
        try {
            bookList = bookDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert bookList != null;
        bookList.forEach(System.out::println);
    }

    @Test
    public void getDirAll() {
        List<Book> bookList = null;
        try {
            bookList = bookDao.getDirAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert bookList != null;
        bookList.forEach(System.out::println);
    }

    @Test
    public void researchBookAll() {
        List<Book> bookList = null;
        try {
            bookList = bookDao.researchBookAll("毛泽东",false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert bookList != null;
        bookList.forEach(System.out::println);
    }

    @Test
    public void insertBook() {
        try {
            assert bookDao.insertBook(new Book("NG031","【修订】中国中篇小说经典","吴义勤。","凤凰出版社","2009-06-08","故事集","http://image31.bookschina.com/2005/051219/B1235381.jpg","3"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateBook() {
        try {
            boolean a = bookDao.updateBook(new Book("NG031", "中国中篇小说经典", "吴义勤", "凤凰出版社", "2009-06-08", "故事集修订", "http://image31.bookschina.com/2005/051219/B1235381.jpg", "3"));
            assertEquals(true,a);
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}