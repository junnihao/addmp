package com.example.demo.springboot.cache.springcache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByTitle(String title){
         log.info("data from database..............." ) ;
         return bookRepository.findBookByTitle(title) ;
    }
}
