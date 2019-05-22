package 数据结构.队列;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Demo1 {
    public static void main(String[] args){
        ArrayBlockingQueue<Integer> qu = new ArrayBlockingQueue<Integer>(5);

        qu.add(1);
        qu.add(2);
        qu.add(3);
        qu.add(4);

        qu.poll();

        System.out.println(qu);

        qu.poll();

        System.out.println(qu);

        qu.poll();

        System.out.println(qu);

        qu.remove();

        System.out.println(qu);


    }
}
