package 数据结构.Set;

import org.junit.Test;

import java.util.*;

public class 去重 {
    @Test
    public void f(){
        HashSet set = new HashSet();
        set.add(1);
        set.add(1);

        System.out.println(set);
    }
    @Test
    public void f1(){
        ArrayList<Integer> list = new ArrayList<>();
        int[] nums = {1,3144,2,4531,4};
        for(int num:nums){
            list.add(num);
        }
        Collections.swap(list,0,1);
        System.out.println();
        System.out.println(list);
    }

    @Test
    public void f2(){

    }
}
