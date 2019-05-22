package 数据结构.树.Trie;


import java.util.TreeMap;

public class Trie {
    private class Node{
        public boolean isWord; //是否找到了
        public TreeMap<Character,Node> next;
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie(){ //构造函数
        root = new Node();
        size=0;
    }

    public int getSize(){
        return this.size;
    }
    //添加一个单词word
    public void add(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){//使用循环的方式添加映射
            char c =word.charAt(i);
            if(cur.next.get(c)==null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){//如果单词没有存在
            cur.isWord = true;
            size++;
        }
    }

    //查询单词word
    public boolean contains(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c)==null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;//Important
    }

    //匹配单词word
    public boolean search(String word){
        return match(root,word,0);
    }
    /*三个参数节点|单词|单词指针*/
    private boolean match(Node node, String word, int index){
        if(index==word.length())
            return node.isWord;

        char c = word.charAt(index); //获取当前指针
        if(c!='.'){
            if(node.next.get(c) == null) //如果不是.看下一个是不是空 不是空接着下一个节点
                return false;
            return match(node.next.get(c),word,index+1);
        }else{
            for(char nextChar:node.next.keySet())
                if(match(node.next.get(nextChar),word,index+1))
                    return true;
            return false;
        }
    }


}
