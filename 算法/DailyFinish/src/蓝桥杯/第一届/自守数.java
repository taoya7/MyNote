package 蓝桥杯.第一届;

public class 自守数 {
    /**
     *  自守数
     *  5*5 = 25
     *  76*76 = 5776
     *  625*625 = 390625
     *
     *
     *  25
     *  k=5 m=125 m%10==k
     *  k=2 m=50 m%10==k
     *
     *
     * */
    public static void zishou(){
        for(int i=1; i<20*1000*1000; i++){
            int tmp = i;
            int m = 0;
            for(;;){
                if(tmp == 0){
                    System.out.println(i);
                    break;
                }
                int k = tmp%10;
                m += k*i;
                if(m%10 != k) //处理不相等
                    break;

                m = m/10; //舍去一个末位
                tmp = tmp/10;
            }
        }
    }
    public static void main(String[] args){
        zishou();
    }
}
