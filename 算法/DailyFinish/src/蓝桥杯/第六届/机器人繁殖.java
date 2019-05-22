package 蓝桥杯.第六届;

import org.junit.Test;

import java.util.Scanner;

public class 机器人繁殖 {
    /**
     *  X星系的机器人可以自动复制自己。它们用1年的时间可以复制出2个自己，然后就失去复制能力。
     * 每年X星系都会选出1个新出生的机器人发往太空。也就是说，如果X星系原有机器人5个，
     * 1年后总数是：5 + 9 = 14
     * 2年后总数是：5 + 9 + 17 = 31
     *
     * 如果已经探测经过n年后的机器人总数s，你能算出最初有多少机器人吗？
     *
     * 数据格式：
     *
     * 输入一行两个数字n和s，用空格分开，含义如上。n不大于100，s位数不超过50位。
     *
     * 要求输出一行，一个整数，表示最初有机器人多少个。
     *
     * 例如：
     * 用户输入：
     * 2 31
     *
     * 则程序应该输出：
     * 5
     *
     * 再例如：
     * 用户输入：
     * 97 2218388550399401452619230609499
     *
     * 则程序应该输出：
     * 8
     * */

    /**
     *  输入N S
     *  年---数量
     *
     *  给定数量计算年剩余
     * */
    public int test(int year, int n){
        int sum = n;
        int k=0;
        int rem = n;
        for(int i=1; i<=year; i++){
            int tmp = rem*2-1;
            rem = tmp;
            sum += tmp;
        }
        return sum;
    }
    @Test
    public void f(){
        int n = 2;
        int s = 31;
        //枚举机器人
        for(int i=1; i<=999; i++){
            int w = test(n, i);
            if(w == s){
                System.out.println(i);
                return;
            }
        }
    }
}
