package com.example.demo.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = -5144055068797033748L;
    private int id ;
    private String userName ;
    private String passwd ;
}
