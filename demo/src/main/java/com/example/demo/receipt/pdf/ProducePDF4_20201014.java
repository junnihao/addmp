package com.example.demo.receipt.pdf;


import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class ProducePDF4_20201014 {

    Document document = null;// 建立一个Document对象
    private static Font headFont;
    private static Font keyFont;
    private static Font tailFont;
    private static Font textfont_H;
    private static Font textfont_B;

    static {
        BaseFont bfChinese_H;
        try {
            /**
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版
             */
            bfChinese_H = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

            headFont = new Font(bfChinese_H, 10, Font.NORMAL);
            keyFont = new Font(bfChinese_H, 13, Font.BOLD);
            tailFont = new Font(bfChinese_H, 10, Font.BOLD);
            textfont_H = new Font(bfChinese_H, 10, Font.NORMAL);
            textfont_B = new Font(bfChinese_H, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面属性
     *
     * @param file
     */
    public ProducePDF4_20201014(File file) {

        //自定义纸张
        Rectangle rectPageSize = new Rectangle(485, 250);

        // 定义A4页面大小
        //Rectangle rectPageSize = new Rectangle(PageSize.A4);
        rectPageSize = rectPageSize.rotate();// 加上这句可以实现页面的横置
        document = new Document(rectPageSize, 10, 10, 10, 40);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建段落
     *
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value, Font font, int align) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value, font));
        paragraph.setAlignment(align);
        return paragraph;
    }


    public void generatePDF() throws Exception {
        //何炳英
        //页头信息
        //document.add(createParagraph("西安" + i, headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("永祥药房连锁塘头村分店", keyFont, Element.ALIGN_CENTER));
        document.add(createParagraph(" ", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("员工编号: 000001", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("-  -  -  -  -  -  -  -  -  -  - -  -  -  -  -  -  -  -  -  -  -  -", keyFont, Element.ALIGN_CENTER));
        document.add(createParagraph("名称                    数量                  单价                      收费", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("草药                                                                    7", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("北沙参                     15g                     0.15                        2.25", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("麦冬                        15g                     0.25                        3.75", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("天花粉                    10g                     0.15                        1.50", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("玉竹                        12g                     0.25                        3.00", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("百合                        12g                     0.25                        3.00", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("桑叶                        12g                     0.06                        0.72", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("白扁豆                    12g                     0.06                        0.72", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("甘草                        7g                      0.10                        0.70", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("地骨皮                    12g                     0.3                        3.60", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("桑白皮                    10g                     0.15                        1.80", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("乌梅                        10g                     0.08                        0.80", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("浮小麦                    15g                     0.06                        0.90", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("知母                        9g                     0.08                        0.75", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("黄芩                        10g                     0.15                        1.50", headFont, Element.ALIGN_LEFT));

        document.add(createParagraph("-  -  -  -  -  -  -  -  -  -  - -  -  -  -  -  -  -  -  -  -  -  -", keyFont, Element.ALIGN_CENTER));
        document.add(createParagraph("金额合计: 175.00", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("实收: 200.00", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("找零: 25.00", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("-  -  -  -  -  -  -  -  -  -  - -  -  -  -  -  -  -  -  -  -  -  -", keyFont, Element.ALIGN_CENTER));
        document.add(createParagraph("打印时间: 2016  10  14  19 : 21 : 21", headFont, Element.ALIGN_LEFT));
        document.add(createParagraph("谢谢惠顾,祝您早日康复", tailFont, Element.ALIGN_CENTER));
        document.close();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("d:/INVOICE/马鹏_20161014.pdf");
        file.createNewFile();
        new ProducePDF4_20201014(file).generatePDF();
        System.out.println("PDF生产完成");
    }
}
