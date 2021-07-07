package com.example.demo.springboot.controllers;

import com.example.demo.springboot.cache.springcache.Book;
import com.example.demo.springboot.cache.springcache.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/springCache")
public class SpringCacheBookController {
    @Autowired
    BookService bookService;

    @Autowired
    ConcurrentMapCacheManager cacheManager;

    /*@Autowired
    RedisCacheManager cacheManager;*/

    //@Cacheable(cacheNames = "books",key = "1")
    @RequestMapping(value = "/findBookByTitle", method = RequestMethod.GET)
    public List<Book> getBooksByTitle() {
        return bookService.getBooksByTitle("child") ;
    }

    @GetMapping("/getCache")
    public Object getCache(){
        Cache demoCache = cacheManager.getCache("books");
        System.out.println(demoCache.getName());
        //System.out.println(demoCache.get("child", Book.class));
        //System.out.println(demoCache.get(Book.class));
        //System.out.println(demoCache.getNativeCache());
        return demoCache.getNativeCache();
    }
}
