package 数据结构.链表;

public class LinkedList <E>{

    /**
    *   节点组成
    * */

    private class Node{ //组成链表
        public E e;
        public Node next; //下一个节点

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null, null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size= 0;
    }

    /*获取链表元素个数*/
    public int getSize(){
        return size;
    }
    /*是否为空*/
    public boolean isEmpty(){
        return size==0;
    }

    /*插入元素*/
    public void add(int index, E e){
        if(index<0 || index>size)
            throw new IllegalArgumentException("Add Error");

        Node prev = dummyHead;
        for(int i=0; i<index; i++){
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;

    }
    /*链表头添加元素*/
    public void addFirst(E e){
        add(0, e);
        size++;
    }
    /*末尾添加*/
    public void addLast(E e){
        add(size,e);
    }

    /*删除元素*/
    public E  remove(int index){
        Node prev = dummyHead;
        for(int i=0; i<index; i++){
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;

        size--;
        return delNode.e;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        for(Node cur = dummyHead.next ; cur != null ; cur = cur.next)
            res.append(cur + "->");
        res.append("NULL");

        return res.toString();
    }
}
