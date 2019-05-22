package 数据结构.并查集;

public class UnionFind1 {
    int [] id;
    public UnionFind1(int size){
        id = new int[size];
        for(int i=0; i<id.length; i++){
            id[i] = i;
        }
    }
    //返回长度
    public int getSize(){
        return id.length;
    };
    int find(int p){
        return id[p];
    }
    //查看元素p与q是否一个集合
    boolean isConnected(int p,int q){
        return find(p) == find(q);
    }
    void unionElements(int p, int q){
        int pid = find(p);
        int qid = find(q);
        if(pid == qid)
            return;
        for(int i=0; i<id.length; i++){
            if(id[i] == pid)
                id[i] = qid;
        }
    }
}
