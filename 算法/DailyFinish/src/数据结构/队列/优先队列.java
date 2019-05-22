package 数据结构.队列;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 优先队列 {
    public static void main(String[] args){
        PriorityQueue<Integer> qu = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 2;
            }
        });



    }
}
