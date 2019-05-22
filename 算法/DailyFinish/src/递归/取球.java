package 递归;

public class 取球 {
    static int n=0;
    public static int  f(int n, int m){

        if(n<m) return 0;
        if(n==m) return 1;
        if(m==0) return 1;

        return f(n-1, m-1) + f(n-1,m);
    }
    public static void main(String[] args){
        int k = f(10,3);
        System.out.println(k);
        System.out.println(n);
    }
}
