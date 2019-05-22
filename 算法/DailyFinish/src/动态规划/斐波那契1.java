package 动态规划;

public class 斐波那契1 {
    /*递归写法*/
    public static int f(int n){
        if(n==1 || n==2)
            return 1;
        else
            return f(n-1)+ f(n-2);
    }

    /*记录写法*/
    static int[] dp  = new int[100];
    public static int f1(int n){
        if(n == 1 || n==2)
            return 1;
        if(dp[n] != 0)
            return dp[n];

        else{
            int value = f1(n-1) + f1(n-2);
            dp[n] = value;
            return dp[n];
        }

    }
    public static void main(String[] args){
        System.out.println(f1(12)); // 1 1 2 3 5 8 13 21
    }
}
