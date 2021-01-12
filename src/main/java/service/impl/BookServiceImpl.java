package service.impl;

import dao.BookDao;
import entity.Book;
import factory.DaoFactory;
import service.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public class BookServiceImpl implements BookService {

    private final BookDao bookDao = DaoFactory.getBookDaoInstance();

    private List<Book> bookList = new ArrayList<>();

    @Override
    public List<Book> getSelect() {
        try {
            bookList = bookDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public List<Book> getSiZheng() {
        try {
            bookList = bookDao.getDirAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public List<Book> getResearch(String str, boolean swi) {
        try {
            bookList = bookDao.researchBookAll(str,swi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean insertBook(Book book) {
        boolean t=false;
        try {
            t= bookDao.insertBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean delBook(String ISBN) {
        boolean t=false;
        try {
            t= bookDao.delBook(ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean updateBook(Book book) {
        boolean t=false;
        try {
            t= bookDao.updateBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
