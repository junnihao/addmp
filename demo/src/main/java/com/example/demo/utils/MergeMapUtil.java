package com.example.demo.utils;

import java.util.*;

public class MergeMapUtil {
    static HashMap<String, Object> tmpMap = null;
    //forEach 是无序的
    public static List<HashMap<String, Object>> merge(List<HashMap<String, Object>> sourceList, List<HashMap<String, Object>> mergeObjectList,Map emptyMap, String key) {
        List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
        Optional.ofNullable(sourceList).orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEachOrdered(o1 ->
        {
            Optional.ofNullable(mergeObjectList).orElse(new ArrayList<HashMap<String, Object>>()).parallelStream().forEachOrdered(o2 -> {
                        Iterator<Map.Entry<String, Object>> iterator2 = o2.entrySet().iterator();
                        System.out.println(o1.get(key) +" --> "+o2.get(key)) ;
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
                resultList.add(map);
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
                resultList.add(map);
            }
            tmpMap = null ;
        });
        return resultList;
    }
    public static void main(String[] args) {
        List<HashMap<String, Object>> listm1 = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> listm2 = new ArrayList<HashMap<String, Object>>();
        Map mapleft1 = new HashMap<String, Object>();
        mapleft1.put("ID", 1);
        mapleft1.put("SID", "1");
        mapleft1.put("LEFTSEQ", "1");
        mapleft1.put("LEFT1", "XXXX");
        mapleft1.put("LEFT2", "11111-DD");
        listm1.add((HashMap<String, Object>) mapleft1);

        Map mapleft2 = new HashMap<String, Object>();
        mapleft2.put("ID", 2);
        mapleft2.put("SID", "2");
        mapleft2.put("LEFTSEQ", "2");
        mapleft2.put("LEFT1", "XXXX");
        mapleft2.put("LEFT2", "2222-DD");
        listm1.add((HashMap<String, Object>) mapleft2);

        Map mapleft3 = new HashMap<String, Object>();
        mapleft3.put("ID", 3);
        mapleft3.put("SID", "3");
        mapleft3.put("LEFTSEQ", "3");
        mapleft3.put("LEFT1", "XXXX");
        mapleft3.put("LEFT2", "333-DD");
        listm1.add((HashMap<String, Object>) mapleft3);


        Map mapright1 = new HashMap<String, Object>();
        mapright1.put("IDX", 1);
        mapright1.put("SID", "1");
        mapright1.put("RIGHTSEQ", "1");
        mapright1.put("RIGHT1", "XXXX");
        mapright1.put("RIGHT2", "R11111-DD");
        listm2.add((HashMap<String, Object>) mapright1);

        Map mapright2 = new HashMap<String, Object>();
        mapright2.put("IDX", 2);
        mapright2.put("SID", "2");
        mapright2.put("RIGHTSEQ", "2");
        mapright2.put("RIGHT1", "YYYYY");
        mapright2.put("RIGHT2", "R22222-DD");
        listm2.add((HashMap<String, Object>) mapright2);

        Map emptyMap = new HashMap<String, Object>();
        emptyMap.put("IDX", null);
        emptyMap.put("SID", null);
        emptyMap.put("RIGHTSEQ", null);
        emptyMap.put("RIGHT1", null);
        emptyMap.put("RIGHT2", null);

        List<HashMap<String, Object>> result = merge(listm1,listm2,emptyMap,"SID");

        // Collections.sort(result , Comparator.comparingInt(u -> (Integer) u.get("ID") ));

        for(HashMap<String, Object> m:result){
            System.out.println(m);
        }

        Map m = new IdentityHashMap();
        m.put(new String("1"),"java");
        m.put(new String("1"),"js");
        m.put(new String("2"), "php");
        System.out.println(m);
    }
}
