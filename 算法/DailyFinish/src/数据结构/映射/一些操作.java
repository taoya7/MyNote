package 数据结构.映射;

import java.util.*;

public class 一些操作 {
    public static void  main(String[] args){
        TreeMap<Character, Integer> map = new TreeMap<>();


        map.put('a',700);
        map.put('b', 400);
        map.put('c', 800);


        ArrayList<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });


        System.out.println(map);
    }
}
