package 搜索;

import java.util.Scanner;

public class 解救小哈 {
    static int m;//（m,n）为几行几列
    static int n;
    static int p;//（p，q)为终点
    static int q;
    static int min=9999;
    static int [][]a=new int [51][51];//存放地图
    static int [][]b=new int [51][51];//存放路径

    public static void dfs(int x,int y,int step)
    {
        int [][]next={{0,1},{1,0},{0,-1},{-1,0}};
        if(x==p&y==q)
        {
            System.out.println("step"+step);


            if(step<min)
                min=step;

            return;
        }
        for(int k=0;k<4;k++)
        {
            int tx=x+next[k][0];
            int ty=y+next[k][1];
            if(tx<0||tx>=m||ty<0||ty>=n)
                continue;
            if(a[tx][ty]==0&b[tx][ty]==0)
            {
                b[tx][ty]=1;
                dfs(tx,ty,step+1);
                b[tx][ty]=0;
            }
        }
        return;
    }
    public static void main(String[] args){
         n = 4;
        m = 5;

        a[0][2]=1;
        a[2][2]=1;
        a[3][1]=1;
        a[4][3]=1;

        System.out.println();
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++)
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }

        int startX = 0;
        int startY = 0;
        p = 3;
        q = 2;
        b[startX][startY] = 1; //标记开始
        dfs(startX,startY,0);
        System.out.println(min);
    }
}
