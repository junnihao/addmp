package com.example.demo.java.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws ParseException {
        System.out.println("20220110000000".length());
        System.out.println("20220110000000".length()>8 ? "20220110000000".substring(0,8) : "20220110000000");

        System.out.println("-->" +("20220110000000").substring(0,8)) ;

        String wheresql ="where 1=1 and a.product=b.product AND PROMID = 'ALL' " ;
        wheresql = wheresql.toUpperCase(Locale.ROOT) ;
        wheresql.indexOf("AND PROMID = 'ALL'",0) ;
        System.out.println( wheresql) ;
        System.out.println( wheresql.indexOf("'ALL'",0) ) ;
        System.out.println( wheresql.substring(0,wheresql.indexOf("AND PROMID = 'ALL'",0) ) +"E") ;

        DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
        // df.parse("2022-01-19") ;
        // System.out.println( new java.sql.Date(df.parse("2022-01-19").getTime())) ;

        System.out.println(valiDateTimeWithLongFormat("1971-00-21"));
    }


    public static boolean valiDateTimeWithLongFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            //pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }
}
