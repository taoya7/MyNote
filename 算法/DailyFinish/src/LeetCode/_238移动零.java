package LeetCode;

import java.util.Arrays;

public class _238移动零 {
    /**
     *  设立一个临时变量k 遍历数组将非0的移动到arr[k]位置 然后k++再将其他的位置标0
     * */
    public static void f(int[] arr){
        int k=0;
        for(int i=0; i<arr.length; i++){
           if(arr[i]!=0){
               arr[k++] = arr[i];
           }
        }
        for(int i=k; i<arr.length; i++){
            arr[i] = 0;
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void main(String[] args){
        /**
         *  给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
         * */
        int[] arr = {0,1,0,3,12};
        f(arr);

    }
}
