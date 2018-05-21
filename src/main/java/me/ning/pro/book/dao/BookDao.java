package me.ning.pro.book.dao;

import me.ning.pro.book.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDao {

    List<Book> queryAll();

    Book queryBookById(Integer bookId);

    int insertBook(Book book);

    int insertBooks(@Param("books") List<Book> books);

    int updateBook(Book book);

    int deleteBookById(int bookId);

    int deleteBooks(@Param("bookIds") List<Integer> bookIds);


    List<Book> queryBookByName(String name);
}
