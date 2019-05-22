package 动态规划;

import java.util.Arrays;
import java.util.Scanner;

public class 数塔 {
    static int maxN = 100;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); //层数

        int f[][] = new int[maxN][maxN];
        int dp[][] = new int[maxN][maxN];

        //输入数据
        for(int i=1; i<=n; i++){
            for(int j=1; j<=i; j++){
                f[i][j] = input.nextInt();
            }
        }

        //边界值
        for(int j=1; j<=n; j++){
            dp[n][j] = f[n][j];
        }

        for(int i=n-1; i>=1; i--){
            for(int j=1; j<=i; j++){
                /*状态转移方程*/
                dp[i][j] = Math.max(dp[i+1][j],dp[i+1][j+1]) +f[i][j];
            }
        }

        for(int i=1; i<=n; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[1][1]);


    }
}

