package 查找;

public class 二分查找 {
    public static int binart_search(int[] a, int key){
        int low = 0;
        int high = a.length-1;

        int res=-1;
        while(low<=high){
//            int mid = (low+high)/2;
            int mid = low + (high-low)/2;
            if(key<a[mid]){
                high=mid-1;
            }else if(key>a[mid]){
                low=mid+1;
            }else{
                res = mid;
                break;
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7,8};
        int i = binart_search(arr, 5);
        System.out.println(i);
    }
}
