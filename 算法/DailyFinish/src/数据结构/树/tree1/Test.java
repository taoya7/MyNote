package 数据结构.树.tree1;

public class Test {
    public static void main(String[] args){
        BST<Integer> tree = new BST<>();

        int[] arr = {10,8,5,7,12,4};
        for (int num : arr){
            tree.add(num);
        }


        System.out.println(tree);

    }
}
