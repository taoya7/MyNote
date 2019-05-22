package LeetCode;

public class _121股票买卖的最佳时机 {
    public static int f(int[] prices){
        if(prices.length<=1){//元素判断
            return 0;
        }
        int buy=prices[0], sell=0;//边界
        for(int i=1; i<prices.length; i++){
            sell = Math.max(sell,prices[i]-buy);
            buy = Math.min(buy,prices[i]);
        }
        return sell;
    }
    public static void main(String[] args){
        /*
        *   7 1 5 3 6 4
        *   >> 5
        *
        * */
        int[] arr = {7,1,5,3,6,4};
        int f1 = f(arr);
        System.out.println(f1);
    }
}
