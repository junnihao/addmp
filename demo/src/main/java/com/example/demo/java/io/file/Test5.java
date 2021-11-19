package com.example.demo.java.io.file;

import java.util.*;
import java.util.stream.Collectors;

public class Test5 {
    static HashMap<String, Object> tmpMap = null;
    public static List<HashMap<String, Object>> merge(List<HashMap<String, Object>> list1, List<HashMap<String, Object>> list2,Map emptyMap, String key) {
        List<HashMap<String, Object>> list3 = new ArrayList<HashMap<String, Object>>();
        Optional.of(list1).orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEach(o1 ->
        {
            Optional.of(list2).orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEach(o2 -> {
                        Iterator<Map.Entry<String, Object>> iterator2 = o2.entrySet().iterator();
                        if(o1.get(key) == o2.get(key))
                        {
                            tmpMap = new HashMap<String, Object>();
                            while (iterator2.hasNext()){
                                Map.Entry<String, Object> next = iterator2.next();
                                tmpMap.put(next.getKey(),     next.getValue());
                            }
                        }
                    }
            );
            if(tmpMap==null){
                Iterator<Map.Entry<String, Object>> iterator1 = o1.entrySet().iterator();
                HashMap<String, Object> map = new HashMap<String, Object>();
                while (iterator1.hasNext()) {
                    Map.Entry<String, Object> next = iterator1.next();
                    map.put(next.getKey(), next.getValue());
                }
                Iterator<Map.Entry<String, Object>> iterator2 = emptyMap.entrySet().iterator();
                while (iterator2.hasNext()){
                    Map.Entry<String, Object> next = iterator2.next();
                    map.put(next.getKey(),     next.getValue());
                }
                list3.add(map);
            }else{
                Iterator<Map.Entry<String, Object>> iteratorx = o1.entrySet().iterator();
                HashMap<String, Object> map = new HashMap<String, Object>();
                while (iteratorx.hasNext()) {
                    Map.Entry<String, Object> next = iteratorx.next();
                    map.put(next.getKey(), next.getValue());
                }
                Iterator<Map.Entry<String, Object>> iterator2 = tmpMap.entrySet().iterator();
                while (iterator2.hasNext()){
                    Map.Entry<String, Object> next = iterator2.next();
                    map.put(next.getKey(),     next.getValue());
                }
                list3.add(map);
            }
            tmpMap = null ;
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
        List<String> idList = new ArrayList<>(Arrays.asList("姓名", "性别"));
        List<String> nameList = new ArrayList<>(Arrays.asList("杰克", "男"));
        Map<String,String> listMap = idList.stream().collect(Collectors.toMap(key->key, key->nameList.get(idList.indexOf(key))));
        System.out.println(listMap.toString());


        List<HashMap<String, Object>> listm1 = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> listm2 = new ArrayList<HashMap<String, Object>>();
        Map mapleft1 = new HashMap<String, Object>();
        mapleft1.put("ID", 1);
        mapleft1.put("SID", 1);
        mapleft1.put("LEFTSEQ", "1");
        mapleft1.put("LEFT1", "XXXX");
        mapleft1.put("LEFT2", "11111-DD");
        listm1.add((HashMap<String, Object>) mapleft1);

        Map mapleft2 = new HashMap<String, Object>();
        mapleft2.put("ID", 2);
        mapleft2.put("SID", 2);
        mapleft2.put("LEFTSEQ", "2");
        mapleft2.put("LEFT1", "XXXX");
        mapleft2.put("LEFT2", "2222-DD");
        listm1.add((HashMap<String, Object>) mapleft2);

        Map mapleft3 = new HashMap<String, Object>();
        mapleft3.put("ID", 3);
        mapleft3.put("SID", 3);
        mapleft3.put("LEFTSEQ", "3");
        mapleft3.put("LEFT1", "XXXX");
        mapleft3.put("LEFT2", "333-DD");
        listm1.add((HashMap<String, Object>) mapleft3);


        Map mapright1 = new HashMap<String, Object>();
        mapright1.put("ID", 1);
        mapright1.put("SID", 1);
        mapright1.put("RIGHTSEQ", "5");
        mapright1.put("RIGHT1", "XXXX");
        mapright1.put("RIGHT2", "11111-DD");
        listm2.add((HashMap<String, Object>) mapright1);

        Map mapright2 = new HashMap<String, Object>();
        mapright2.put("ID", 2);
        mapright1.put("SID", 2);
        mapright2.put("RIGHTSEQ", "1");
        mapright2.put("RIGHT1", "XXXX");
        mapright2.put("RIGHT2", "11111-DD");
        listm2.add((HashMap<String, Object>) mapright2);

        Map emptyMap = new HashMap<String, Object>();
        mapright2.put("ID", null);
        mapright1.put("SID", null);
        emptyMap.put("RIGHTSEQ", null);
        emptyMap.put("RIGHT1", null);
        emptyMap.put("RIGHT2", null);


        Map map1 = new HashMap<String, Object>();

        map1.put("IDX", "11111-ID");
        map1.put("dd", "2222222222dd");
        map1.put("FLAG", "YYYYYY");



        // List list = getNewList(list1,"FLAG");
        List<HashMap<String, Object>> result = merge(listm1,listm2,emptyMap,"ID");

        Collections.sort(result , Comparator.comparingInt(u -> (Integer) u.get("ID") ));
        //Collections.sort(result , Comparator.comparingInt(u -> (Integer) u.get("SID") ));

        for(HashMap<String, Object> m:result){
            System.out.println(m);
        }
        //System.out.println( merge(listm1,listm2,emptyMap ,"ID"));


       /* List<String> idList = new ArrayList<>(Arrays.asList("姓名", "性别"));
        List<String> nameList = new ArrayList<>(Arrays.asList("杰克", "男"));
                Map<String,String> listMap = idList.stream().collect(Collectors.toMap(key->key, key->nameList.get(idList.indexOf(key))));
        System.out.println(listMap.toString());*/
    }
}
