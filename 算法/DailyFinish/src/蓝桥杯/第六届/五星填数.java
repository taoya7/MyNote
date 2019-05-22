package 蓝桥杯.第六届;

import org.junit.Test;

import java.util.Arrays;

public class 五星填数 {
    /**
     *  标题：五星填数
     *
     * 如【图1.png】的五星图案节点填上数字：1~12，除去7和11。
     * 要求每条直线上数字和相等。
     *
     * 如图就是恰当的填法。
     *
     * 请你利用计算机搜索所有可能的填法有多少种。
     * 注意：旋转或镜像后相同的算同一种填法。
     *
     * 请提交表示方案数目的整数，不要填写任何其它内容。
     * */
    /*全排列*/
    public void Af(int[] arr, int k){
        if(k == arr.length-1){
            count(arr);
            return;
        }
        for(int i=k; i<arr.length; i++){
            int tmp = arr[k];
            arr[k] = arr[i];
            arr[i] = tmp;
            Af(arr,k+1);
            tmp = arr[k];
            arr[k] = arr[i];
            arr[i] = tmp;
        }
    }
    static int sum=0;
    public void count(int[] a){
        /*所有可能出现的情况*/
        int r1 =a[0] + a[2]+ a[5] + a[8];
        int r2 = a[0] +a[3]+a[6]+a[9];
        int r3 = a[1] +a[2]+a[3]+a[4];
        int r4 = a[1] +a[5]+a[7]+a[9];
        int r5 = a[4] +a[6]+a[7]+a[8];

        if(r1==r2 && r2==r3 && r3==r4 && r4==r5){
            sum++;
            System.out.println(Arrays.toString(a));
        }
    }
    @Test
    public void f(){
        int arr[] = {1,2,3,4,5,6,8,9,10,12};
        Af(arr,0);
        System.out.println(sum/10);
    }
}
