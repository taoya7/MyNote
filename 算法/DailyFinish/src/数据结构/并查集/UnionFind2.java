package 数据结构.并查集;

public class UnionFind2 {
    int [] parent;
    int[] sz;
    public UnionFind2(int size){
        parent = new int[size];
        for(int i=0; i<parent.length; i++){//初始化 使每个节点指向自己
            parent[i] = i;
            sz[i] = 1;
        }
    }
    //返回长度
    public int getSize(){
        return parent.length;
    };
    int find(int p){
        while(p!=parent[p]){ //寻找根节点
            p = parent[p];
        }
        return p;
    }
    //查看元素p与q是否一个集合
    boolean isConnected(int p,int q){
        return find(p) == find(q);
    }
    //合并操作
    void unionElements(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;
//        parent[pRoot] = parent[qRoot];
        if(sz[pRoot]<sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
