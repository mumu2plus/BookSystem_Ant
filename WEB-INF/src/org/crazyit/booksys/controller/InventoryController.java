package org.crazyit.booksys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.crazyit.booksys.domain.Inventory;
import org.crazyit.booksys.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InventoryController extends BaseController {
    // 依赖注入Service组件
    @Resource(name="bookService")
    private BookService bookService;

    @ResponseBody
    @GetMapping(value = "/getAllInventories")
    public Object getAll() {
        return bookService.getAllInventories();
    }

    @ResponseBody
    @GetMapping(value = "/getItemsById")
    public Object getItemsById(Integer inventoryId) {
        return bookService.getItemsByInventoryId(inventoryId);
    }

    @ResponseBody
    @PostMapping(value = "/addInventory")
    public Object add(@ModelAttribute Inventory inventory,
                      int[] amounts, Integer[] bookIds) {
        Integer id = bookService.addInventory(inventory,
                amounts, bookIds);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", id);
        return map;
    }
}
