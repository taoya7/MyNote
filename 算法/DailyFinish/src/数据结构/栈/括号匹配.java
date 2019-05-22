package 数据结构.栈;

import java.util.Stack;

public class 括号匹配 {
    public static boolean f(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch=='(' || ch=='[' || ch=='{')
                stack.push(ch);
            else{
                if(stack.isEmpty())
                    return false;
                char topC = stack.pop();
                if(ch==')' && topC != '(')
                    return false;
                if(ch==']' && topC != '[')
                    return false;
                if(ch=='}' && topC !='{')
                    return false;
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args){

    }
}
