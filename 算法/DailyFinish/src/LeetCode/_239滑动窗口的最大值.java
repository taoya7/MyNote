package LeetCode;

import java.util.*;

public class _239滑动窗口的最大值 {
    /*双循环*/
    public static void f1(int[] arr, int k){
        ArrayList res = new ArrayList();
        for(int i=0; i<=arr.length-k; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j=i; j<i+3; j++){
                list.add(arr[j]);
            }
            Integer max = Collections.max(list);
            res.add(max);
        }
        System.out.println(res);
    }
    /*队列的方法*/
    public static int[] f2(int[] arr, int k){
       if(arr==null || arr.length<k || k==0)//参数判断
           return new int[0];
       int[] res = new int[arr.length-k+1]; //返回数组的长度 arr.length-k+1
        //双端队列
        Deque<Integer> deque = new LinkedList<>();
        for(int i=0; i<arr.length; i++){
            while(!deque.isEmpty() && arr[deque.getLast()]<arr[i]){//当前元素arr[i] 与队列的最后一个元素对比deque.getLast()
                deque.removeLast();
            }
            deque.addLast(i);
            if(deque.getFirst() == i-k)
                deque.removeFirst();
            if(i>=k-1){
                res[i-k+1] = arr[deque.getFirst()];
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] data = {1,3,-1,-3,5,3,6,7};
        int k = 3; //窗口的大小
        f1(data,3);
        int[] ints = f2(data, 3);
        System.out.println(Arrays.toString(ints));
    }
}
