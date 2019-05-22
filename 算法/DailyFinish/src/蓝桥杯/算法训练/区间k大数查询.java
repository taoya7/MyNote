package 蓝桥杯.算法训练;

import java.util.*;

public class 区间k大数查询 {
    /**
     *  问题描述
     * 给定一个序列，每次询问序列中第l个数到第r个数中第K大的数是哪个。
     *
     * 输入格式
     * 第一行包含一个数n，表示序列长度。
     *
     * 第二行包含n个正整数，表示给定的序列。
     *
     * 第三个包含一个正整数m，表示询问个数。
     *
     * 接下来m行，每行三个数l,r,K，表示询问序列从左往右第l个数到第r个数中，从大往小第K大的数是哪个。序列元素从1开始标号。
     *
     * 输出格式
     * 总共输出m行，每行一个数，表示询问的答案。
     * 样例输入
     * 5
     * 1 2 3 4 5
     * 2
     * 1 5 2
     * 2 3 2
     * 样例输出
     * 4
     * 2
     * 数据规模与约定
     * 对于30%的数据，n,m<=100；
     *
     * 对于100%的数据，n,m<=1000；
     *
     * 保证k<=(r-l+1)，序列中的数<=10de6次方。
     * */
    /*数组寻第K最大值*/
    public static int getMax(int[] arr, int left, int right, int key){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=left; i<=right; i++){
            list.add(arr[i]);
        }
        Collections.sort(list);//排序
        System.out.println(list);
        return list.get(key-1);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n+1];
        for(int i=1; i<arr.length; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(arr));//打印数组

        int m = sc.nextInt(); //询问个数
        ArrayList res = new ArrayList();//保存结果
        for(int j=1; j<=m; j++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            int key = sc.nextInt();
            int tmp = getMax(arr, start, end, key);
            res.add(tmp);
        }

        System.out.println(res);



    }
}
