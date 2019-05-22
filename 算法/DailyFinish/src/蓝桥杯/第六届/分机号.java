package 蓝桥杯.第六届;

import org.junit.Test;

import java.util.HashSet;
import java.util.TreeSet;

public class 分机号 {
    @Test
    public void f(){
        /**
         * 标题：分机号
         *
         * X老板脾气古怪，他们公司的电话分机号都是3位数，老板规定，所有号码必须是降序排列，且不能有重复的数位。比如：
         *
         * 751,520,321 都满足要求，而，
         * 766,918,201 就不符合要求。
         *
         * 现在请你计算一下，按照这样的规定，一共有多少个可用的3位分机号码？
         *
         * 请直接提交该数字，不要填写任何多余的内容。
         * */
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=9; i>=0; i--){
            for(int j=0; j<i; j++){
                for(int k=0; k<j; k++){
                    int n = i*100+j*10+k;
                    set.add(n);
                }
            }
        }
        System.out.println(set);
        System.out.println(set.size());
    }
}
