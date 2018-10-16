package org.crazyit.booksys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.crazyit.booksys.domain.Category;
import org.crazyit.booksys.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController extends BaseController {
    @Resource(name = "bookService")
    private BookService bookService;

    @ResponseBody
    @GetMapping(value = "/getAllCategories")
    public Object getAll() {
        return bookService.getAllCategories();
    }

    @ResponseBody
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public Object add(Category category) {
        Integer id = bookService.addCategory(category);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", id);
        return map;
    }

    // @ResponseBody会将集合数据转换为JSON格式返回客户端
    @ResponseBody
    @PostMapping(value = "/updateCategory")
    public Object update(Category category){
        bookService.updateCategory(category);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", 1);
        return map;
    }
}
