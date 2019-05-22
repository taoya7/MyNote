package LeetCode._77组合;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<List<Integer>> combine(int n, int k) {
        List res = new ArrayList<>();

        List<Integer> subList = new ArrayList<>();
        add(res, subList, 1, n, k);

        return res;
    }
    public static void add(List<List<Integer>> list, List<Integer> subList, int start, int n, int k){//添加数据
        if(k<=0){
            ArrayList<Integer> tmp = new ArrayList<>(subList);
            list.add(tmp);
            return;
        }
        while(start<=n-k+1){
            subList.add(start);
            add(list, subList, start+1, n, k-1);
            subList.remove(subList.size() - 1);
            start+=1;
        }
    }
    public static void main(String[] args){
        /**
         *  给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
         *
         * */
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);
    }
}
