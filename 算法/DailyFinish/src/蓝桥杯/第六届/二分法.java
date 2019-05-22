package 蓝桥杯.第六届;

import org.junit.Test;

public class 二分法 {

    /**
     * 二分查找法十分常用，适用于在有序的队列中搜索。
     * 下面的程序在有序整数数组中搜索，找不到会返回-1
     * */
    public int find(int[] arr, int k){
        int lo = 0;
        int hi = arr.length-1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            /*判断mid*/
            if(k<arr[mid]){
                hi = mid-1;
            }else if(k>arr[mid]){
                lo = mid+1;
            }else{
                return mid;
            }
        }
        return  -1;
    }

    @Test
    public void f(){
        int[] arr = {1,4,5,8,11,13,14,15,22,27,35,46,48,49,49,51,60,62,62,62,71,74,88};
        System.out.println(find(arr,48));
    }
}
