package 蓝桥杯;

import java.util.Scanner;

public class 数独游戏 {
    static int[][] qipan = null;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        qipan = new int[9][9];
        //9*9的格子
        for(int i=0; i<9;i++){
            String line = sc.nextLine();
            for(int j=0; j<9; j++){
                qipan[i][j] = line.charAt(j)-'0';
            }
        }
        play(0,0);
    }
    /*向行、列填入元素*/
    public static void play(int x, int y){
        if(x==9){/*终止条件*/
            print(qipan);
            return;
        }
        if(qipan[x][y]==0){
            for(int value=1; value<=9;value++){//平行关系
                if(checkg(x,y,value)!=0 && checkl(x,y,value)!=0){ //判断
                    qipan[x][y] = value;
                    //输入下一个空格数字
                    if(y<8)
                        play(x,y+1);
                    else if(x<=8 && y==8)
                        play(x+1,0);
                    qipan[x][y] = 0;
                }
            }
        }else{
            if(y<8)
                play(x,y+1);
            else if(x<=8 && y==8)
                play(x+1,0);
        }
    }
    /**
     *  棋盘的行和列不能有重复的元素
     * */
    public static int checkl(int x, int y, int value){
        for(int i=0;i<9;i++)
        {
            if(qipan[x][i]==value||qipan[i][y]==value)
                return 0;
        }
        return 1;
    }
    /**
     * 3*3判断是否重复
     * */
    public static int checkg(int x,int y,int value)
    {
        if(x>=0&&x<=2)  x=0;
        if(x>=3&&x<=5)  x=3;
        if(x>=6&&x<=8)  x=6;

        if(y>=0&& y<=2) y=0;
        if(y>=3&& y<=5) y=3;
        if(y>=6&& y<=8) y=6;

        for(int i=x;i<x+3;i++) {
            for(int j=y;j<y+3;j++) {
                //	System.out.println(i+" "+j);
                if(qipan[i][j]==value) {//    //有重复数字
                    return 0;
                }
            }
        }
        return 1;   //无重复数字
    }

    /*打印*/
    public static void print(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
