package 牛客网;

import java.util.*;


public class 孩子们的游戏 {
    public static int f1(int n, int m){
        List<Integer> students = new LinkedList();
        for(int i=0; i<n; i++)
            students.add(i);
        System.out.println(students);
        int index= 0;

        while (students.size()>1){
           int tmp = (index+m-1)%students.size();
            students.remove(tmp);
            index = tmp;
            System.out.print(tmp+"\t"+students + "\n");
        }
        return students.get(0);
    }
    public static void main(String[] args){
        /**
         * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
         * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先
         * ,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数
         * 。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并
         * 且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直
         * 到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额
         * 有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是
         * 从0到n-1)
         *
         *   0 1 2 3 4 5 (2)
         *  1, 0 2 3 4 5
         *  2, 0 3 4 5
         *  3, 0 4 5
         *  4, 0 5
         *  5, 0
         *  0\


         * */
        System.out.println(f1(6,2));
    }
}
