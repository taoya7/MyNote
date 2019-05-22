package 数据结构.树.Trie;

public class Test {
    public static void main(String[] args){
        Trie trie = new Trie();
        trie.add("panda");
        trie.add("pan");
        System.out.println(trie.search("pan"));
    }
}
