package LeetCode;

public class _268缺失数字 {
    public static void f(int[] arr){
        int n = arr.length;
        int sum = (n+0)*(n+1)/2;
        System.out.println(sum);
        for(int i=0; i<n; i++){
            sum -= arr[i];
        }

    }
    public static void main(String[] args){
        /**
         *  给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数
         * */
        int[] arr = {9,6,4,2,3,5,7,0,1};
        f(arr);
    }
}
