package 动态规划;

public class 背包01 {
    static int n;
    static int c;
    static int[] value;
    static int[] weight;

    public static void main(String[] args){
        weight = new int[]{3,5,1,2,2};
        value = new int[]{4,5,2,1,3};
        c = 8;
        n = weight.length;
        int [][] dp = new int [n][c];

        for(int i=0; i<n;i++){
            for(int j=1; j<=c; j++){
                if(i==0){
                    dp[i][j-1] = (weight[i] <= j? value[i]:0);
                }else{
                    int topValue = dp[i-1][j-1];
                    int thisValue = (weight[i] <= j ? // 当前商品的价值 + 剩余空间的价值
                            (j - weight[i] > 0 ? value[i] + dp[i - 1][j - weight[i]] : value[i]) : topValue);
                    dp[i][j-1] = Math.max(topValue, thisValue);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<c; j++){
                System.out.printf("%6d", dp[i][j]);
            }
            System.out.println();
        }
    }
}
