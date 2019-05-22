package 蓝桥杯.第六届;
import java.util.Scanner;

public class 穿越雷区 {
    /*下次的路径*/
    public static int[][] next = {
            {0,1},
            {1,0},
            {0,-1},
            {-1,0}
    };
    static int min = Integer.MAX_VALUE;
    static int count = 0;
    static char[][] modelArr = null; //Flag
    //DFS
    /**
     * 参数1：map
     * 参数2：x
     * 参数3：y
     * */
    public static void dfs(char[][] localArr, int x, int y){
        //枚举四种走法
        for(int i=0; i<next.length; i++){
            int dx = x+next[i][0];
            int dy = y+next[i][1];
            if(dx>=0 && dx<localArr[0].length && dy>=0 && dy<localArr.length){//确保不会越界
                if(localArr[x][y] == 'B'){
                    iprint(localArr);
                    if(count<min)
                        min = count;
                    count--;
                    System.out.println(count+"\n");
                    return;
                }
                if(canWalk(localArr,x,y,dx,dy)){
                    count++;
                    localArr[x][y] = '0';
                    dfs(localArr,dx,dy);
                    localArr[x][y] = modelArr[x][y];
                }
            }
        }
        count--;

    }

    //是否可以通过呢
    public static boolean canWalk(char[][] arr, int x1, int y1, int x2, int y2){
        return  (arr[x1][y1] !=arr[x2][y2]) && (arr[x2][y2]!='0');//当前位置不可以等于下一个位置的值 下一个位置不可以是已访问过的
    }
    //打印输出
    public static void iprint(char[][] a){
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int line = Integer.parseInt(sc.nextLine());
        int x=0,y=0; //当前坐标
        modelArr = new char[line][line];
        char[][] newArr = new char[line][line]; //输入Map
        for(int i=0; i<line; i++){
            char[] chArr = sc.nextLine().toCharArray();
            for(int j=0; j<chArr.length; j+=2){//跳过空格
                int ndx = j/2;
                char ch = chArr[j];
                modelArr[i][ndx] = newArr[i][ndx] = ch;
                if(ch=='A'){ //确立起始位置
                    x = i;
                    y = ndx;
                }
            }
        }
        /*End*/
        iprint(modelArr);
        System.out.println("Start:>>>\n");
        dfs(newArr,x,y);
    }
}

