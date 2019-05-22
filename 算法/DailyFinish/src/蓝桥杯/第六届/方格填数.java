package 蓝桥杯.第六届;

import java.util.Arrays;

public class 方格填数 {
    static int count = 0;
    /*全排列*/
    public static void qp(int[] arr, int k){
        if(k==arr.length-1){
            if(f(arr)){
                count++;
            }
            return;
        }
        for(int i=k; i<arr.length; i++){
            int temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
            qp(arr, k+1);
            temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
    }
    //Main
    /**
     *  右边大于左边
     *  下边大于上边
     * */
    public static boolean f(int[] data){
        int n=0;
        int[][] localArr = new int[2][5];
        for(int i=0; i<localArr.length; i++){
            for(int j=0; j<localArr[0].length; j++){
                 localArr[i][j]= data[n];
                 n++;
            }
        }
        return true;

    }
    public static void main(String[] args){
        /**
         * 在2行5列的格子中填入1到10的数字。
         * 要求：
         * 相邻的格子中的数，右边的大于左边的，下边的大于上边的
         * >请你计算一共有多少种可能的方案
         * */
        int[] arr = {1,2,3,4,5,6,7,8,9,10};//数据

        qp(arr,0);
        System.out.println(count);
    }
}
