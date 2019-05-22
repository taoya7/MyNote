package 数据结构.树.线段树;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    public SegmentTree(E[] arr){
        this.data =(E[]) new Object[arr.length];
        for(int i=0; i<arr.length; i++)
            data[i] = arr[i];

        tree = (E[])new Object[4*arr.length];

        buildSegmentTree(0,0,data.length-1);
    }
    //在treeIndex位置创建区间[l,r] 线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l+(r-l) /2; // (L+R)/2 <==> (R-L)/2+L
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

    }

    public int getSize(){
        return data.length;
    }
    public E get(int index){
        return data[index];
    }

    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }
}
