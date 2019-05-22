package 蓝桥杯.第四届;

public class 连续奇数和 {
    public static void f(){

    }
    public static void main(String[] args){
        /**
         *   小明看到一本书上写着：任何数字的立方都可以表示为连续奇数的和。
         *    请你帮助小明写出 111 的立方之连续奇数和表示法的起始数字
         *
         *    :这是一个等差数列
         *    1 3 5 7 9
         *    sn =
         * */
        int tar = 111*111*111;
        for(int i=1; i<=20000; i+=2){
            long sum = 0;
            for(int j=i; j<=200000; j+=2){
                sum+= j;
                if(sum==tar){
                    System.out.println(i);
                    return;
                }else if(sum > tar){
                    break;
                }
            }
        }
    }
}
