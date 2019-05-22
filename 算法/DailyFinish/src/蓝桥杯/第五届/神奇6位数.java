package 蓝桥杯.第五届;

import java.util.Arrays;

public class 神奇6位数 {
    /**
     *
     标题：神奇6位数

     有一个6位的正整数，它有个很神奇的性质：

     分别用2 3 4 5 6去乘它，得到的仍然是6位数，并且乘积中所包含的数字与这个6位数完全一样！只不过是它们的顺序重新排列了而已。

     请计算出这个6位数。

     这是一个整数，请通过浏览器提交答案，不要填写任何多余的内容（比如说明性的文字）
     * */
    /*排序*/
    public static int f1(int n){
        char[] chars = Integer.toString(n).toCharArray();
        Arrays.sort(chars);
        return Integer.parseInt(String.valueOf(chars));
    }
    public static void main(String[] args){
        for(int i=100000; i<=999999; i++){
            if(f1(i*2) == f1(i*3) && f1(i*4)==f1(i*6) && f1(i*3)==f1(i*5)){
                System.out.println(i);
            }
        }

    }

}
