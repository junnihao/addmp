package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
public class ExcelUtil {
    /**
     * 用户信息导出类
     * @param response 响应
     * @param fileName 文件名
     * @param columnList 每列的标题名
     * @param dataList 需要导出的数据
     * @param headers 每列写入的值对应的属性名
     */
    public static <T> void createReport(HttpServletResponse response, String fileName, List<String> columnList, List<T> dataList, List<String> headers){
        //声明输出流
        OutputStream os = null;
        //设置响应头
        setResponseHeader(response,fileName);
        try {
            //获取输出流
            os = response.getOutputStream();
            HSSFWorkbook wb = new HSSFWorkbook();

            Map<String, CellStyle> styles = createStyles(wb);

            //获取该工作区的第一个sheet
            Sheet sheet1 = wb.createSheet(fileName);
            int excelRow = 1;
            //创建标题行
            Row titleRow = sheet1.createRow(excelRow++);
            for(int i = 0;i<columnList.size();i++){
                //创建该行下的每一列，并写入标题数据
                Cell cell = titleRow.createCell(i);
                cell.setCellValue(columnList.get(i));
                cell.setCellStyle(styles.get("header"));

                //设置自适应列宽
                sheet1.autoSizeColumn(i);
                sheet1.setColumnWidth(i,sheet1.getColumnWidth(i)*11/10);
            }
            //设置内容行
            if(dataList!=null && dataList.size()>0){
                for (T dto : dataList) {
                    Row dataRow = sheet1.createRow(excelRow++);
                    Field[] fields = dto.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        int m = 0;
                        for (String header : headers) {
                            if (field.getName().equals(header)) {
                                Cell cell = dataRow.createCell(m);
                                //获取get方法
                                // cell.setCellValue(invokeGet(dto, header)==null?null:invokeGet(dto, header).toString());
                                cell.setCellStyle(styles.get("data"));
                                inputValue(cell,invokeGet(dto, header)) ;
                                break;
                            }
                            m++;
                        }
                    }
                }
            }
            //将整理好的excel数据写入流中
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 关闭输出流
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将值填充到单元格中
     * @param cell
     * @param val
     */
    private static void inputValue(Cell cell, Object val){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if(val==null){
            cell.setCellValue("");
            return;
        }
        Class clazz= val.getClass();
        // log.info("clazz = " + clazz) ;
        if(String.class.isAssignableFrom(clazz)){
            cell.setCellValue((String)val);
        }else if(Double.class.isAssignableFrom(clazz)){
            cell.setCellValue((Double)val);
        }else if(Date.class.isAssignableFrom(clazz)){
            String formatDate = simpleDateFormat.format(((Date) val));
            cell.setCellValue(formatDate);
        }else if(Boolean.class.isAssignableFrom(clazz)){
            cell.setCellValue((Boolean)val);
        }else if(Calendar.class.isAssignableFrom(clazz)){
            String formatDate = simpleDateFormat.format(((Calendar) val).getTime());
            cell.setCellValue(formatDate);
        }else if(RichTextString.class.isAssignableFrom(clazz)){
            cell.setCellValue((RichTextString)val);
        }else if(Float.class.isAssignableFrom(clazz)){
            DecimalFormat format = new DecimalFormat("#0.000") ;
            cell.setCellValue(format.format(val));
        }else if(Byte.class.isAssignableFrom(clazz)){
            cell.setCellValue((Byte)val);
        }else if(Short.class.isAssignableFrom(clazz)){
            cell.setCellValue((Short)val);
        }else if(Integer.class.isAssignableFrom(clazz)){
            cell.setCellValue((Integer)val);
        }else if(Long.class.isAssignableFrom(clazz)){
            cell.setCellValue((Long)val);
        }else if(BigDecimal.class.isAssignableFrom(clazz)){
            cell.setCellValue(val.toString());
        }
    }

    /**
     * 创建表格样式
     *
     * @param wb 工作薄对象
     * @return 样式列表
     */
    public static Map<String, CellStyle> createStyles(Workbook wb)
    {
        //内容样式
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style = wb.createCellStyle();
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //边框样式及颜色
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        //字体
        Font dataFont = wb.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 10);
        style.setFont(dataFont);
        styles.put("data", style);

        //标题样式
        style = wb.createCellStyle();
        style.cloneStyleFrom(styles.get("data"));
        //填充颜色及样式
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //字体
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        //字体加粗
        headerFont.setBold(true);
        //字体颜色
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        return styles;
    }


    /**
     *设置浏览器下载响应头
     */
    private static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // invokeSet(response,"contentType","application/octet-stream;charset=UTF-8");
            response.setHeader("contentType", "application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xls");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * java反射bean的get
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 执行get方法
     * @param o
     * @param fieldName
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * java反射bean的set方法
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行set方法
     * @param o
     * @param fieldName
     * @param value
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[] { value });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
