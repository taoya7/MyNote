package 数据结构.树.tree1;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> {
    /**
     * 结点
     */
    private class Node{
        public E e;
        public Node left;
        public Node right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;
    public BST(){
        root = null;
        size=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmppty(){
        return size==0;
    }
    /*添加元素*/
    public void add(E e){
        root = add(root, e);
    }
    //向以node为根的二分搜索树中插入元素E
    private Node add(Node node, E e){
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);//
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }
    /*查询元素*/
    public boolean contains(E e){
        return contains(root, e);
    }
    private boolean contains(Node node, E e){
        if(node == null)//终止条件
            return false;
        if(e.compareTo(node.e) == 0)
            return true; //找到了
        else if(e.compareTo(node.e) <0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }
    /*前序遍历*/
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);//访问左
        preOrder(node.right);//访问右
    }


    /*中序遍历*/
    public void midOrder(){
        midOrder(root);
    }
    public void midOrder(Node node){
        if(node == null)
            return;
        midOrder(node.left);
        System.out.println(node.e);
        midOrder(node.right);
    }
    /*后序遍历*/
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }
    /*寻找最大值与最小值*/
    public E findMax(){
        if(size==0)
            throw new IllegalArgumentException("Is Empty!");
        return findMax(root).e;
    }
    private Node findMax(Node node){
        if(node.right == null)
            return node;
        return findMax(node.right);
    }
    public E findMin(){
        if(size==0)
            throw new IllegalArgumentException("Is Empty!");
        return findMin(root).e;
    }
    private Node findMin(Node node){
        if(node.left == null)
            return node;
        return findMin(node.left);
    }
    // 删除最大值与最小值
    //删除以node为根的二分搜索树中的最大节点 返回删除节点后新的二分搜索树的根
    public E removeMax(){
        E res = findMax();
        root = removeMax(root);
        return res;
    }
    private Node removeMax(Node node){ //传入结点
        if(node.right == null){//已经递归到底
            Node leftNode = node.left; //接住结点的左树
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    private Node removeMin(Node node){ //传入结点
        if(node.left == null){//已经递归到底
            Node rightNode = node.right; //接住结点的左树
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /*删除元素*/
    public void remove(E e){
        root = remove(root,e);
    }
    private Node remove(Node node,E e){
        if(node == null)
            return null; //没找着
        if(e.compareTo(node.e) <0){
            node.left = remove(node.left , e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else{
            // e == node.e
            if(node.left == null){ //如果删除节点左子树为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right == null){//如果删除节点右子树为空
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //如果删除节点左右均不为空
            Node successor = findMin(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        BSTString(root,0,res);
        return res.toString();
    }
//    生成以node为根结点 深度为depth的二叉树的字符串
    private void BSTString(Node node, int depth, StringBuilder res){
        if(node == null){
            res.append(BSTString(depth)+"null\n");
            return;
        }
        res.append(BSTString(depth) + node.e + "\n");
        BSTString(node.left, depth+1, res);
        BSTString(node.right, depth+1, res);
    }
    private String BSTString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0; i<depth; i++)
            res.append("--");
        return res.toString();
    }
}
