package com.example.demo.java.io.file;

import java.math.BigDecimal;
import java.util.IdentityHashMap;
import java.util.Map;

public class Test6 {

    public static void main(String[] args){
        Map brandTotalMap = new IdentityHashMap<String, BigDecimal>();
        brandTotalMap.put(new String("test"),1) ;
        brandTotalMap.put(new String("test1"),2) ;
        brandTotalMap.put(new String("test2"),3) ;
        brandTotalMap.put(new String("test"),4) ;
        brandTotalMap.put(new String("test"),5) ;
        brandTotalMap.put(new String("test8"),6) ;

        System.out.println(" brandTotalMap size = "+ brandTotalMap.size()) ;
        System.out.println(" brandTotalMap size = "+ brandTotalMap) ;

        for(Object key:brandTotalMap.keySet()){
            if((key.toString()).equals("test")){
                System.out.println(">  "+brandTotalMap.get(key)) ;
            }
        }
    }
}
