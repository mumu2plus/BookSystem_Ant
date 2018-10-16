package org.crazyit.booksys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.crazyit.booksys.domain.Book;
import org.crazyit.booksys.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController extends BaseController{
    @RequestMapping(value = "/{formName}", method = RequestMethod.GET)
    public String common(@PathVariable String formName){
        return formName;
    }

    @Resource(name = "bookService")
    private BookService bookService;

    @ResponseBody
    @GetMapping(value = "/getAllBooks")
    public Object getAll() {
        return bookService.getAllBooks();
    }

    @ResponseBody
    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public Object add(@ModelAttribute Book book, Integer categoryId){
        Integer id = bookService.addBook(book, categoryId);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", id);
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/updateBook")
    public Object update(@ModelAttribute Book book, Integer categoryId) {
        bookService.updateBook(book, categoryId);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", 1);
        return map;
    }
}
