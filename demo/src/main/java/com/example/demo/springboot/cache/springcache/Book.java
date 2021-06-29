package com.example.demo.springboot.cache.springcache;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String name ;
    private String vdesc ;

    public Book(){ }

    public Book(String title, String name ,String vdesc) {
        this.title = title;
        this.name = name ;
        this.vdesc = vdesc ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }
}
