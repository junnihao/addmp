package com.example.demo.springboot.cache.springcache;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BookRepository {
        @Cacheable("books")
        @Select("select book_title as title, book_name as name, book_desc as vdesc from demo_book where book_title = #{title}")
        List<Book> findBookByTitle(@Param("title") String title);
}
