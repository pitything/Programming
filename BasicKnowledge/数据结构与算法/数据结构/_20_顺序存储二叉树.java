package 数据结构;

public class _20_顺序存储二叉树 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.print("前序遍历输出：");
        preorderArray(arr, 0);
        System.out.println();
        System.out.print("中序遍历输出：");
        inorderArray(arr, 0);
        System.out.println();
        System.out.print("后序遍历输出：");
        postorderArray(arr, 0);
        System.out.println();
    }

    /**
     * 前序遍历输出
     * @param arr
     * @param index
     */
    public static void preorderArray(int[] arr, int index){
        if(index < 0 || index >= arr.length)  return;
        System.out.print(arr[index] + " ");
        preorderArray(arr, 2 * index + 1);
        preorderArray(arr, 2 * index + 2);
    }

    /**
     * 中序遍历输出
     * @param arr
     * @param index
     */
    public static void inorderArray(int[] arr, int index){
        if(index < 0 || index >= arr.length)  return;
        inorderArray(arr, 2 * index + 1);
        System.out.print(arr[index] + " ");
        inorderArray(arr, 2 * index + 2);
    }

    /**
     * 后序遍历输出
     * @param arr
     * @param index
     */
    public static void postorderArray(int[] arr, int index){
        if(index < 0 || index >= arr.length)  return;
        postorderArray(arr, 2 * index + 1);
        postorderArray(arr, 2 * index + 2);
        System.out.print(arr[index] + " ");
    }
}