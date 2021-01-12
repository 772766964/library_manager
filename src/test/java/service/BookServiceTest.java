package service;

import dao.BookDao;
import entity.Book;
import factory.DaoFactory;
import factory.ServiceFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private final BookService bookService = ServiceFactory.getBookServiceInstance();

    @Test
    public void getSelect() {
        List<Book> bookList = bookService.getSelect();
        bookList.forEach(System.out::println);
    }

    @Test
    public void getResearch() {
        List<Book> bookList = bookService.getResearch("毛泽东选集",true);
        bookList.forEach(System.out::println);
    }
}