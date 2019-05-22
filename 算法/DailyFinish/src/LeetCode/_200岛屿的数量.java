package LeetCode;


import java.util.Arrays;

public class _200岛屿的数量 {
    static int [][] next= {{0,1},{1,0},{0,-1},{-1,0}};//走路决策
    public static int numIslands(char[][] grid) {
        if(grid==null || grid.length==0 || grid[0]==null || grid[0].length==0)
            return 0;
        int count = 0;
        //遍历行数与列数
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                int isIsland = dfs(grid,i,j)==true?1:0;
                count+=isIsland;
            }
        }
        return count;
    }
    /*DFS*/
    public static boolean dfs(char[][] arr, int x, int y){
        if(x<0||x>=arr.length || y<0 || y>=arr[0].length || arr[x][y]==0){
            return false;
        }
        arr[x][y] = 0;
        for(int[] dir:next){
            dfs(arr,x+dir[0],y+dir[1]);
        }
        return true;
    }
    public static void main(String[] args){
        char[][] data = new char[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1},
        };

//        int i = numIslands(data);
//        System.out.println(i);
        int [][] wowo= {{0,1},{1,0},{0,-1},{-1,0}};

        for(int[] a:wowo){
            System.out.println(Arrays.toString(a));
        }

    }
}
