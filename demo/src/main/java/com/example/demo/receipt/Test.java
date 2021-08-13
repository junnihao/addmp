package com.example.demo.receipt;

/**
 * 菜品对象
 *
 * @author admin
 *
 */
public class Test {
    // 菜品名称
    private String name;
    // 价格
    private double money;
    // 数量
    private Integer num;
    //菜品分类
    private String fenlei;

    public Test() {
        super();
    }
    public Test(String name, double money, Integer num) {
        super();
        this.name = name;
        this.money = money;
        this.num = num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getFenlei() {
        return fenlei;
    }
    public void setFenlei(String fenlei) {
        this.fenlei = fenlei;
    }
}

