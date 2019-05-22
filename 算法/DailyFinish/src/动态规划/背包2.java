package 动态规划;

import java.util.Arrays;
import java.util.Scanner;

public class 背包2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        /**
         * f[i][v] = max{f[i-1][v], f[i-1][v-w[i]+c[i]] }
         * 横坐标为i
         * 纵坐标为v
         * 只考虑放与不放
         *      如果不放第i件物品 -> 将i-1的物品放到背包里 价值[i-1][v]
         *      如果放第i件物品 -> 将把剩余的物品(i--1)放到剩余的背包v-c[i]里
         *
         * */


        int n=5,v=8; //物品的个数与背包的容量
        int[] weight = new int[n+1];
        int[] money = new int[n+1];
        /*输入*/
        for(int i=1; i<=n; i++){
            weight[i] = sc.nextInt();
        }
        for(int i=1; i<=n; i++){
            money[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][v+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=v; j++){
                if(weight[i] <= j){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-weight[i]]+money[i]);
                }else{
                    dp[i][j] =dp[i-1][j];
                }
            }
        }
        /*Print*/
        for(int i=1; i<=n; i++){
            for(int j=1; j<=v; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
