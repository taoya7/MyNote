package 分治算法;

public class 最大值 {
    public static void main(String[] args){
        int[] arr = {6,10,32,8,19,20,2,14};
        int max = findMax(arr);
        System.out.println(max);
    }
    public static int findMax(int[] arr){
        int max = Max(arr,0,arr.length-1);
        return max;
    }

    public static int Max(int[] arr, int low, int high){
        if(low==high){
            return arr[low];
        }else{
            int mid = (low+high)/2;
            int leftNum = Max(arr,low,mid);
            int rightNum = Max(arr,mid+1,high);
            return Math.max(leftNum,rightNum);
        }
    }

}



