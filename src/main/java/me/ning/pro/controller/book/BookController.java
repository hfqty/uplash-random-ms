package me.ning.pro.controller.book;

import me.ning.pro.common.dto.OutputDTO;
import me.ning.pro.entity.book.Book;
import me.ning.pro.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BookController {


    @Autowired
    private BookService bookService;

    @RequestMapping("")
    public OutputDTO<Map<String,Object>> allBooks(){
        OutputDTO<Map<String,Object>> outputDTO = new OutputDTO<>();
        try {
            List<Book> books = bookService.queryAll();
            if (books.isEmpty()) {
                outputDTO.setMsg("没有书籍!");
                outputDTO.setCode("-1");
            } else{
                Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("books", books);
            outputDTO.setData(resultMap);
        }
        }catch (Exception e){
            outputDTO.setCode("-1");
            outputDTO.setMsg("没有书籍!");
        }
        return outputDTO;
    }

    @GetMapping("/{id}")
    public OutputDTO<Map<String,Object>> queryBookById(@PathVariable(name = "id") Integer bookId){
        OutputDTO<Map<String,Object>> outputDTO = new OutputDTO<>();
            Book book = bookService.queryBookById(bookId);
            if(book != null) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put(book.getName(),book);
                outputDTO.setData(resultMap);
            }
        return outputDTO;
    }
}
