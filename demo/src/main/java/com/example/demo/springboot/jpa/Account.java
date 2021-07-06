package com.example.demo.springboot.jpa;

import javax.persistence.*;
// 出现异常： java.lang.IllegalArgumentException: Not an managed type
// 解决方法： View -> Tool Windows -> Database - > 设定数据库连接
@Entity
@Table(name="demo_account")
public class Account {
    @Id
    @GeneratedValue
    private int id ;
    @Column
    private String name ;
    @Column
    private double money;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
