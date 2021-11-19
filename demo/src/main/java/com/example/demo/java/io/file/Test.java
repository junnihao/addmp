package com.example.demo.java.io.file;

import java.util.*;
//https://blog.csdn.net/liuerchong/article/details/108667658
public class Test {
    public static List<HashMap<String, Object>> merge(ArrayList<HashMap<String, Object>>
                                                               list1, ArrayList<HashMap<String, Object>> list2, String key) {
        List<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();
        Optional.ofNullable(list1).
                orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEach(o1 ->
        { Optional.ofNullable(list2).orElse(new ArrayList<HashMap<String, Object>>()).
                parallelStream().forEach(o2 -> {
            if (o1.get(key) == o2.get(key) )
            { Iterator<Map.Entry<String, Object>> iterator1 = o1.entrySet().iterator();
                Iterator<Map.Entry<String, Object>> iterator2 = o2.entrySet().iterator();
                HashMap<String, Object> map = new HashMap<String, Object>();
                while (iterator1.hasNext()) {
                    Map.Entry<String, Object> next = iterator1.next();

                    map.put(next.getKey(), next.getValue());
                }
                while (iterator2.hasNext()){
                    Map.Entry<String, Object> next = iterator2.next();
                    map.put(next.getKey(),     next.getValue());
                }
                list3.add(map); } });
        });
        return list3;
    }

    public static  List<HashMap<String, Object>> getNewList(List<HashMap<String, Object>>    list1,String key) {

        List<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();

        Optional.ofNullable(list1).
                orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEach(o1 -> {

            Iterator<Map.Entry<String, Object>> iterator1 = o1.entrySet().iterator();
            HashMap<String, Object> map = new HashMap<String, Object>();
            while (iterator1.hasNext()) {
                Map.Entry<String, Object> next = iterator1.next();
                if(next.getKey()=="FLAG") {

                    map.put(next.getKey(), next.getValue());
                }
                else {

                    map.put(next.getKey(), next.getValue());
                }
            }
            list3.add(map);

        });
        //System.out.println(list3);
        return list3;
    }
    public static void main(String[] args) {

        List<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> list2 = new ArrayList<HashMap<String, Object>>();

        ArrayList<HashMap<String, Object>> listm1 = new ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> listm2 = new ArrayList<HashMap<String, Object>>();
        Map map11 = new HashMap<String, Object>();
        map11.put("FLAGXX", "XXXX");
        map11.put("ID", "11111-ID");
        map11.put("ddXX", "11111-DD");

        Map map1 = new HashMap<String, Object>();

        map1.put("ID", "11111-ID");
        map1.put("dd", "2222222222dd");
        map1.put("FLAG", "YYYYYY");
        list1.add((HashMap<String, Object>) map11);
        list1.add((HashMap<String, Object>) map1);

        listm1.add((HashMap<String, Object>) map11);
        listm2.add((HashMap<String, Object>) map1);

        // List list = getNewList(list1,"FLAG");
         merge(listm1,listm2,"ID");
        System.out.println( merge(listm1,listm2,"ID"));
    }
}
