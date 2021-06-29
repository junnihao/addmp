package com.example.demo.springboot.controllers;

import com.example.demo.springboot.cache.springcache.Book;
import com.example.demo.springboot.cache.springcache.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/springCache")
public class SpringCacheBookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "/findBookByTitle", method = RequestMethod.GET)
    public List<Book> getBooksByTitle() {
        return bookService.getBooksByTitle("child") ;
    }


}
