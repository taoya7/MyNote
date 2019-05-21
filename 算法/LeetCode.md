### 200-岛屿的个数

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。

```
Input
11000
11000
00100
00011


Output
3
```

```java
//伪代码
遍历二维数组
    dfs(arr,x,y)
    如果为0不处理
    如果为1
        标记当前位置0访问下一个是0还是1

//范围
x y的范围 x>0 y>0 x<=arr.length y<=arr[0].length
```

```java
public class Main {
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

        int i = numIslands(data);
        System.out.println(i);
    }
}
```

简单版本

```java
public int numIslands(char[][] grid) {
    int count = 0;
    //遍历二维数组
    for(int i = 0 ; i < grid.length ; i ++){
        for(int j = 0 ; j < grid[0].length ; j++){
            if(grid[i][j] == '1'){
                //遇到1就把与它相邻的1变为0 count++
                count ++;
                dfs(grid,i,j);               
            }
        }
    }
    return count;
}
public static void dfs(char[][] grid , int i , int j){
    if(i >=0 && i < grid.length && j >= 0 && j < grid[0].length){
        if(grid[i][j] == '1'){
            grid[i][j] = '0';
            dfs(grid,i,j+1);
            dfs(grid,i,j-1);
            dfs(grid,i+1,j);
            dfs(grid,i-1,j);                
        }
    }
}
```