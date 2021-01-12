package service;

import dao.BookDao;
import entity.Book;

import java.util.List;

/**
 * @author 1
 */
public interface BookService {
    /**
     * 查找图书
     *
     * @return 书集合
     */
    List<Book> getSelect();


    /**
     * 查找图书
     *
     * @return 书集合
     */
    List<Book> getSiZheng();


    /**
     * 查找图书
     *
     * @return 书集合
     */
    List<Book> getResearch(String str ,boolean swi);

    /**
     * 插入图书
     * @param book
     * @return
     */
    boolean insertBook(Book book);

    /**
     * 删除行
     * @param ISBN
     * @return
     */
    boolean delBook(String ISBN);

    /**
     * 更新数据
     * @param book
     * @return
     */
    boolean updateBook(Book book);
}
