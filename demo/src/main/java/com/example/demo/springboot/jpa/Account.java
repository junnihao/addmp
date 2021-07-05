package com.example.demo.springboot.jpa;


import javax.persistence.*;
//@Entity
@Table(name="DEMO_ACCOUNT")
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
