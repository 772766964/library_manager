package dao;

import entity.Book;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
public interface BookDao {
    /**
     *  获取所有的图书信息
     *
     * @return Book集合
     * @throws SQLException
     */
    List<Book> getAll() throws SQLException;


    /**
     *  获取指定的所有图书信息
     *  思政书籍推荐
     *
     * @return Book集合
     * @throws SQLException
     */
    List<Book> getDirAll() throws SQLException;


    /**
     *  获取指定的所有图书信息
     *  检索
     *
     * @return Book集合  true--书名   author--作者
     * @throws SQLException
     * @param str
     * @param swi
     */
    List<Book> researchBookAll(String str , boolean swi) throws SQLException;

    /**
     * 插入数据
     *
     * @param book
     * @return
     * @throws SQLException
     */
    boolean insertBook(Book book)throws SQLException;

    /**更新图书
     *
     * @param book
     * @return
     * @throws SQLException
     */
    boolean updateBook(Book book) throws SQLException;


    /**删除图书
     *
     * @param ISBN
     * @return
     * @throws SQLException
     */
    boolean delBook(String ISBN) throws SQLException;
}
