package org.crazyit.booksys.controller;

import java.util.HashMap;
import java.util.Map;

import org.crazyit.booksys.exception.BookException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
    @ExceptionHandler(BookException.class)
    @ResponseBody
    public Object exp(Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("exception", ex.getMessage());
        return map;
    }
}
