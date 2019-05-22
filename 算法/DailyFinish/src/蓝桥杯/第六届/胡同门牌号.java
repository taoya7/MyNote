package 蓝桥杯.第六届;

public class 胡同门牌号 {
    /**
     * 小明家住在一条胡同里。胡同里的门牌号都是连续的正整数，由于历史原因，最小的号码并不是从1开始排的。
     * 有一天小明突然发现了有趣的事情：
     * 如果除去小明家不算，胡同里的其它门牌号加起来，刚好是100！
     * 并且，小明家的门牌号刚好等于胡同里其它住户的个数
     *
     * >>> 解
     *  sum-小明家=100
     *  小明家=cnt-1
     * */
    public static void main(String[] args){
        int sum; //门牌总和；
        int cnt;//用户总和；

        //枚举
        for(int i=2; i<=100; i++){
            sum=0; cnt=0; //初始化总数
            for(int j=i; j<=100; j++){
                cnt++;
                sum+=j;
                if(sum-cnt==99 && cnt-1>i){
                    System.out.println(cnt-1);//小明 = cnt-1
                }
            }
        }
    }
}
