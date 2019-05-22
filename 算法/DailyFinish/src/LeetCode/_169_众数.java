package LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

public class _169_众数 {
    public static void f1(int[] arr){
        /**
         * 使用Map映射
         * */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int num:arr){

            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }
        System.out.println(map);

    }
    public static void f2(int[] arr){
        /**
         *  排序
         * */
        Arrays.sort(arr);
        System.out.println(arr[arr.length/2]);
    }
    public static void main(String[] args){
        int[] arr = {2,2,1,1,1,2,2};
        f2(arr);
    }
}
