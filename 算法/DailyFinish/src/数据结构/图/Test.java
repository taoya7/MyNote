package 数据结构.图;

import java.util.Scanner;

public class Test {
    static int[][] data = null;
    static int sum = 0;// 记录长度
    static int[] flag = null;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int m = sc.nextInt();
        data = new int[n+1][m+1];
        /*初始化二维数组*/
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m;j++){
                if(i==j)
                    data[i][j] = 0;
                else
                    data[i][j] = -1;//无穷
            }
        }
        /*读入顶点之间的边*/
        for(int i=1; i<=m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            data[a][b] = 1;
            data[b][a] = 1;//因为是无向图 所以data[a][b] = data[b][a]
        }
        print(data);
        flag[1] = 1;
        dfs(1);
    }
    /*DFS*/
    public static void dfs(int cur){//cur是当前顶点编号
        System.out.println(cur);
        sum++;
        if(sum==data[0].length)
            return;
        for(int i=1; i<=data[0].length; i++){
            if(data[cur][i]==1 && flag[i]==0){
                flag[i] =1;
                dfs(i);
            }
        }
        return;

   }
    /*打印*/
    static void print(int [][] arr){
        for(int i=1; i<arr[0].length; i++){
            for(int j=1; j<arr.length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
