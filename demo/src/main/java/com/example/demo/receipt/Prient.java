package com.example.demo.receipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板
 * @author admin
 *
 */
public class Prient implements Printable {
    // 菜品集合
    public static List<Test> testList = new ArrayList<Test>();

    // 设置小票打印
    public int print(Graphics g, PageFormat pf, int page)
            throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        // 设置颜色
        g2d.setColor(Color.BLACK);
        //模式  字体   字体大小
        g2d.setFont(new Font("Default", Font.PLAIN, 16));
        // 参数1：显示内容 参数2：横向偏移 参数3：纵向偏移
        g2d.drawString("药品清单", 100, 50);
        g2d.drawString("------------------------------------------------", 40, 70);
        g2d.setFont(new Font("Default", Font.PLAIN, 12));
        g2d.drawString("点餐员：自定义", 40, 90);
        g2d.drawString("电话：自定义", 40, 110);
        g2d.drawString("用餐时间：自定义", 40, 130);
        g2d.drawString("用餐地址：打印测试", 40, 150);
        g2d.setFont(new Font("Default", Font.PLAIN, 16));
        g2d.drawString("------------------------------------------------", 40, 170);
        g2d.drawString("菜品             单价             小计", 40, 190);
        g2d.setFont(new Font("Default", Font.PLAIN, 12));
        int H1 = 190;
        double zmoney = 0;
        int count = 0;
        for (Test test : testList) {
            count = count + 1;
            H1 = H1 + 20;
            zmoney = test.getMoney() * test.getNum() + zmoney;
            g2d.drawString(count + "." + test.getName() + "(" + test.getNum()
                    + "份)     ￥" + test.getMoney()+"     ￥"+test.getMoney()*test.getNum(), 40, H1);
        }
        g2d.setFont(new Font("Default", Font.PLAIN, 16));
        g2d.drawString("------------------------------------------------", 40, H1 + 20);
        g2d.setFont(new Font("Default", Font.PLAIN, 12));
        g2d.drawString("合计：￥" + zmoney, 40, H1 + 40);
        g2d.drawString("优惠金额：￥" + 20, 40, H1 + 60);
        g2d.drawString("应收：￥" + (zmoney-20), 40, H1 + 80);
        g2d.drawString("实收：￥" + zmoney, 40, H1 + 100);
        g2d.drawString("找零：￥" + 20, 40, H1 + 120);
        g2d.drawString("收银员：" + "打印测试", 40, H1 + 140);
        g2d.drawString("谢谢惠顾，欢迎下次光临！", 80, H1 + 160);
        return PAGE_EXISTS;
    }
    public static List<Test> getTestList() {
        return testList;
    }
    public static void setTestList(List<Test> testList) {
        Prient.testList = testList;
    }
}


