package 蓝桥杯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static void main(String[] args){
        ArrayList list = new ArrayList();
        list.add(3);
        list.add(7);
        list.add(1);

        Collections.sort(list);
        System.out.println(list);

    }
}
