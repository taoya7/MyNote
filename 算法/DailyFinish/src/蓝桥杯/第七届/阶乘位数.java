package 蓝桥杯.第七届;

import java.math.BigInteger;

public class 阶乘位数 {
    /*求阶乘*/
    public static int f(int n){
        if(n==0){
            return 1;
        }
        return n*f(n-1);
    }
    public static void main(String[] args){
        /**
         *  9的阶乘 362880
         *  1011000100110000000
         * */
        BigInteger n = new BigInteger("1");
        for(int i=1; i<=9999; i++){
            n = n.multiply(new BigInteger(new Integer(i).toString()));
        }
        String s = n.toString(2);  //以2进制输出
        System.out.println(s.length());

    }
}
