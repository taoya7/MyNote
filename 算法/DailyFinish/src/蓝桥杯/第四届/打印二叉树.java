package 蓝桥杯.第四届;

public class 打印二叉树 {
    /**
     *  打印二叉树
     * */
    public static void main(String[] args){
        BST<Integer> bst = new BST<>();
        int[] arr = {10,8,5,7,12,4};
        for(int num:arr){
            bst.add(num);
        }
        System.out.println(bst);
    }
}

class BST<E extends Comparable<E>>{
    /*结点*/
    private class Node{
        public E e;
        public Node left;
        public Node right;
        public Node(E e){ //构造参数
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }
    Node root; //根结点
    public int size;
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }

    /*添加结点*/
    //向以node为根结点添加元素
    public void add(E e){
        root = add(root, e);
    }
    private Node add(Node node, E e){
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){//如果传进来的数据比node大 - 右
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        BSTString(root,0,res);
        return res.toString();
    }
    private void BSTString(Node node, int depth, StringBuilder res){
        if(node==null){
            return;
        }
        res.append(BSTString(depth) + node.e + "\n");
        BSTString(node.left, depth+1, res);
        BSTString(node.right, depth+1,res);
    }
    private String BSTString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<depth; i++){
            res.append("--");
        }
        return res.toString();
    }
}
