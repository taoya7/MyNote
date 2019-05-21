# Trie

Trie是一个树形结构。是一种专门处理字符串匹配的数据结构，用来解决字符串集合中快速查找字符串的问题。


Trie也成为前缀树

Trie查询每条条目的事件复杂度与字典中一共多少条数目无关。

时间复杂度O(w),w为查询单词的长度。

**Trie特点**

1. 根节点没有任何字符
2. 根节点开始到某一结点路径上经过的字符连接起来就是搜索的字符串


### 构造

每个节点有若干指向下个节点的指针

```java
class Node{
    char c;
    Map<char,Node> next;
}
```

### 插入元素

![](https://bucket-1257126549.cos.ap-guangzhou.myqcloud.com/20181230162445.gif)


从跟节点开始，将单词的每个字母逐一插入 Trie树。插入前先看字母对应的节点是否存在，存在则共享该节点，不存在则创建对应的节点。

```java
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
```

### 查询操作

从根节点开始遍历，如果下一个节点不包含直接返回false，需要注意的是最后的返回值不是直接返回true而是`cur.isWord`，因为比如pan、panda但是只添加了panda如果返回值为true那么pan也会搜索成功。所以应该返回Node的isWord属性

```java
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
```

### 简单模式匹配

p.. = pan .可以代表任何一个字母

```java
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
```



```java
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
```