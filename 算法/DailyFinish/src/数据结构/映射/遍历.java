package 数据结构.映射;

import java.util.Map;
import java.util.TreeMap;

public class 遍历 {

    public static void main(String[] args){
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("a",1);
        map.put("b",2);

        System.out.println(map);

        for(Map.Entry<String,Integer>entry:map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
