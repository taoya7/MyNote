package 蓝桥杯.第四届;

import java.util.Arrays;

public class 猜灯谜 {

    public static void main(String[] args){
        /**
         *
         标题：猜灯谜

         A 村的元宵节灯会上有一迷题：

         请猜谜 * 请猜谜 = 请边赏灯边猜

         小明想，一定是每个汉字代表一个数字，不同的汉字代表不同的数字。

         请你用计算机按小明的思路算一下，然后提交“请猜谜”三个字所代表的整数即可。

         请严格按照格式，通过浏览器提交答案。
         注意：只提交一个3位的整数，不要写其它附加内容，比如：说明性的文字。
         * */

       for(int a=1; a<=9; a++)
           for(int b=0; b<=9; b++)
               for(int c=0; c<=9; c++)
                   for(int d=0; d<=9; d++)
                       for(int e=0; e<=9; e++)
                           for(int f=0; f<=9; f++){
                               if((a*100+b*10+c)*(a*100+b*10+c)==(a*100000+d*10000+e*1000+f*100+d*10+b)){
                                   System.out.println(a+""+b+""+c);
                               }
                           }

    }
}
