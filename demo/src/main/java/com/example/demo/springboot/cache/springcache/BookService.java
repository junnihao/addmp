package com.example.demo.springboot.cache.springcache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByTitle(String title){
         return bookRepository.findBookByTitle(title) ;
    }
}
