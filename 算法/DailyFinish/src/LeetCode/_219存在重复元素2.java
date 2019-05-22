package LeetCode;

import java.util.*;

import java.util.Deque;

public class _219存在重复元素2 {
    public static boolean f(int[] arr, int k){
        TreeMap<Integer, Integer> map = new TreeMap<>(); //保存映射
        for(int i=0; i<arr.length; i++){
            if(map.containsKey(arr[i])){
                int n = map.get(arr[i]);
                if(i-n<=k){
                    return true;
                }
            }else{
                map.put(arr[i],i);
            }
        }
        return false;
    }
    public static void main(String[] args){
        /**
         * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j
         * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
         * */

    }
}
