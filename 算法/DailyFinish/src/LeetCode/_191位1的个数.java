package LeetCode;

public class _191位1的个数 {
    public static void f(int n){
        System.out.println(Integer.toString(n,2));
        int cnt = 0;
        while(n>0){
            cnt++;
            n = n&(n-1);
        }
        System.out.println(cnt);
    }
    public static void main(String[] args){
        /**
         *
         *  返回其二进制表达式中数字位数为 ‘1’ 的个数
         *
         *  8   ->  1000
         *  8-1 ->  0111
         *  8&(8-1) 1000
         * */
        f(588);

    }
}
