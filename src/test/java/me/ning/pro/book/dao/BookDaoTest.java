package me.ning.pro.book.dao;

import me.ning.pro.book.entity.Book;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;


    @Test
    @Ignore

    public void queryAll() {
        List<Book> books = bookDao.queryAll();
        System.out.println(books);
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(1);
        assertEquals(book != null ,true);
        System.out.println("name："+book.getName());;
    }

    @Test
    @Ignore
    public void queryBookByName(){
        List<Book> books = bookDao.queryBookByName("重");
        books.stream().forEach(System.out::println);
    }

    @Test
    @Ignore
    public void insertBook() {
        Book book = new Book("理解人性","阿德勒");
        int effectedNum = bookDao.insertBook(book);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void insertBooks() {
        List<Book> books = new ArrayList<>();
        Book book1= new Book("意识的探秘","科赫");
        Book book2 = new Book("心思大开","史蒂芬.约翰逊");
        Book book3 = new Book("写作这回事","史蒂芬.金");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        int effectedNum  = bookDao.insertBooks(books);
        assertEquals(3,effectedNum);
    }

    @Test
    @Ignore
    public void updateBook() {
        Book book = new Book();
        book.setISBN(9787547810149l);
        book.setBookId(3);
        book.setPrice(BigDecimal.valueOf(75));
        book.setPagesNum(587);
        int effectedNum  = bookDao.updateBook(book);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void deleteBooKById() {
        int id = 5;
        int effectedNum = bookDao.deleteBookById(id);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void deleteBooks() {
        int id1 = 3,id2 = 4;
        List<Integer> ids = new ArrayList<>();
        ids.add(id1);
        ids.add(id2);
        int effectedNum = bookDao.deleteBooks(ids);
        assertEquals(2,effectedNum);
    }
}