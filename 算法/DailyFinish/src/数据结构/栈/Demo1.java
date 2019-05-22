package 数据结构.栈;

import java.util.Stack;

public class Demo1 {
    public static void main(String[] args){
        Stack<Object> stack = new Stack<>();
        stack.push("沉迷");
        stack.push("学习");
        stack.push("无法");
        stack.push("自拔");

        System.out.println(stack.size());
        System.out.println(stack);
    }
}
