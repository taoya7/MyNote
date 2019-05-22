package 数据结构.Set;

import java.util.*;

public class 集合求子集 {
    public static void pq(int[] a,int k){
        if(k==a.length-1){
            System.out.println();
            return;
        }
        for(int i=k; i<a.length; i++){
            int tmp=a[k];
            a[k] = a[i];
            a[i] = tmp;
            pq(a,k+1);
            tmp=a[k];
            a[k] = a[i];
            a[i] = tmp;
        }
    }
    static void print(int[] arr){
        int len = 1<<arr.length; //2^n
        LinkedList res = new LinkedList();
        for(int i=0; i<len; i++){
            LinkedList list = new LinkedList();
            int index=i;
            for(int j=0; j<arr.length; j++){
                if((index&1) == 1){
                    list.add(arr[j]);
                }
                index>>=1;
            }
            res.add(list);
        }
        System.out.println(res);
    }

    public static void main(String[] args){
        /**
         *  求一个集合的全部子集
         *
         *  {1,3,4,5}
         * */
        int[] arr = {1,2,3,4};

        print(arr);

    }

}
