package 数据结构.Set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class 使用 {
    public static int un(String[] words){
        TreeSet<Object> tree = new TreeSet<>();
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        for(String word:words){
            StringBuilder bs = new StringBuilder();
            for(int j=0; j<word.length(); j++){
                bs.append(codes[word.charAt(j)-'a']);
            }
            tree.add(bs.toString());
        }
        return  tree.size();
    }
    public static void main(String[] args){
        HashSet<Object> bs = new HashSet<>();
        bs.add(8);
        bs.add(6);
        System.out.println(bs);
    }
}
