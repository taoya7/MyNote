package 蓝桥杯.第四届;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class 好好学习 {
    static int count = 0;
    public void qp(char[] ch, int k){
        if(k==ch.length-1){
            System.out.println(Arrays.toString(ch));
            count++;
            return;
        }
        for(int i=k; i<ch.length; i++){
            char tmp = ch[k];
            ch[k] = ch[i];
            ch[i] = tmp;
            qp(ch,k+1);
            tmp = ch[k];
            ch[k] = ch[i];
            ch[i] = tmp;
        }
    }
    @Test
    public void f(){
        /**
         * 汤姆跟爷爷来中国旅游。一天，他帮助中国的小朋友贴标语。他负责贴的标语是分别写在四块红纸上的四个大字：“好、好、学、习”。但是汤姆不认识汉字，他就想胡乱地贴成一行。
         *
         * 请你替小汤姆算一下，他这样乱贴，恰好贴对的概率是多少？
         * */
        char[] ch = {'好','好','学','习'};
        qp(ch,0);
        System.out.println(count);

    }
}
