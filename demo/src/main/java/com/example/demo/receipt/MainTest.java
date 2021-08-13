package com.example.demo.receipt;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 * 打印测试类
 *
 * @author admin
 *
 */
public class MainTest {

    public static void main(String[] args) {
        //这里是测试用的数据，自己定义就好了
        List<Test> testList = new ArrayList<Test>();
        Test t1 = new Test("千层纸", 23.00, 2);
        Test t2 = new Test("熟地", 23.00, 4);
        Test t3 = new Test("生地", 24.00, 3);
        testList.add(t1);
        testList.add(t2);
        testList.add(t3);

        // 设置小票模型菜品集合
        Prient.setTestList(testList);
        // 定义纸张高度
        int height = 200 + (testList.size() * 20) + 160;
        // 通俗理解就是书、文档
        Book book = new Book();
        // 打印格式
        PageFormat pf = new PageFormat();
        // 原点在纸张的左上方，x 指向右方，y 指向下方。
        pf.setOrientation(PageFormat.PORTRAIT);
        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        // 页面宽度 页面高度
        p.setSize(260, height);
        // x轴 y轴 宽度 高度
        p.setImageableArea(0, 0, 260, height);
        pf.setPaper(p);
        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new Prient(), pf);
        // 指定打印机打印（printerName打印机名称）
        HashAttributeSet hs = new HashAttributeSet();
        String printerName = "EPSON TM-T88IV ReceiptSC4";
        hs.add(new PrinterName(printerName, null));
        PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);
        if (pss.length == 0) {
            System.out.println("无法找到打印机:" + printerName);
            return;
        }
        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        // 写入书
        job.setPageable(book);
        try {
            Integer random6 = (int) ((Math.random() * 9 + 1) * 100000);
            System.out.println("Z"+System.currentTimeMillis()+random6.toString());
            // 添加指定的打印机
            job.setPrintService(pss[0]);
            // 打印执行
            job.print();
        } catch (PrinterException e) {
            System.out.println("================打印出现异常");
        }
    }
}
