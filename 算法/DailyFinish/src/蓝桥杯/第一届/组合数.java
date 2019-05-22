package 蓝桥杯.第一届;

public class 组合数 {
    /**
     *  从4个人中选2个人参加活动，一共有6种选法。
     *
     *  从n个人中选m个人参加活动，一共有多少种选法？
     *
     *  解：
     *      如果挑选的人比总人数还大 直接排除
     *      排列组合的性质 ：f(m,n)=f(m-1,n)+f(m-1,n-1)
     * */
    public static int f(int n, int m){
        if(m>n)
            return 0;
        if(m==0){
            return 1;
        }
        return f(n-1, m-1) + f(n-1, m);
    }
    public static void main(String[] args){
        System.out.println(f(4,2));
    }
}
