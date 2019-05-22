package 动态规划;

public class 找硬币 {
    /*递归*/
    static int f1(int x){
        if(x==0)
            return 0;
        int res = Integer.MAX_VALUE;
        if(x>=2){
            res = Math.min(f1(x-2)+1, res);
        }
        if(x>=5){
            res = Math.min(f1(x-5)+1,res);
        }
        if(x>=7){
            res = Math.min(f1(x-7)+1, res);
        }
        return res;
    }
    public static void main(String[] args){
        /**
         * f(27) = min( f(27-2), f(27-5), f(27-7) )
         * */
        int i = f1(27);
        System.out.println(i);
    }
}

