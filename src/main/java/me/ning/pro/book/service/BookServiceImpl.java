package me.ning.pro.book.service;

import me.ning.pro.book.dao.BookDao;
import me.ning.pro.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public List<Book> queryAll() {
       return bookDao.queryAll();
    }

    @Override
    public Book queryBookById(Integer bookId) {
        return bookDao.queryBookById(bookId);
    }

    @Override
    public List<Book> queryBookByName(String name) {
        return bookDao.queryBookByName(name);
    }

    @Override
    @Transactional
    public boolean insertBook(Book book) {
    checkBookName(book);
       try {
           int effectedNum = bookDao.insertBook(book);
           if(effectedNum <=0){
               throw new RuntimeException("数据插入失败");
           }
       }catch (Exception e){
           throw new RuntimeException("数据插入失败:"+e.getMessage());
       }
       return true;
    }

    @Override
    @Transactional
    public boolean insertBooks(List<Book> books) {
        books.forEach(book->checkBookName(book));
        try{
            int effectedNum  = bookDao.insertBooks(books);
            if(effectedNum <= 0){
                throw new RuntimeException("数据插入失败");
            }
        }catch (Exception e){
            throw new RuntimeException("数据插入失败:"+e.getMessage());
        }
        return true;
    }


    private void checkBookName(Book book) {
        if(book.getName() == null || book.getName().isEmpty()){
            throw new RuntimeException("书名不可为空");
        }
    }

    @Override
    @Transactional
    public boolean updateBook(Book book) {
        if(book.getBookId() == null || book.getBookId() == 0){
            throw new RuntimeException("id不能为空");
        }
        try{
            book.setUpdateTime(new Date());
           int effectedNum =  bookDao.updateBook(book);
           if(effectedNum <= 0){
               throw new RuntimeException("数据更新失败");
           }
        }catch (Exception e){
            throw new RuntimeException("数据更新失败:"+e.getMessage());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBook(Integer bookId) {
        try{
            int effectedNum = bookDao.deleteBookById(bookId);
            if(effectedNum <= 0){
                throw new RuntimeException("删除失败");
            }
        }catch (Exception e){
            throw new RuntimeException("删除失败");
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteBooks(List<Integer> bookIds) {
        try{
           int effectedNum =  bookDao.deleteBooks(bookIds);
           if(effectedNum <= 0){
               throw new RuntimeException("删除失败");
           }
        }catch (Exception e){
            throw new RuntimeException("数据删除失败");
        }
        return true;
    }
}
