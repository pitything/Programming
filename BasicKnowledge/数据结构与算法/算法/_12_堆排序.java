import java.util.Arrays;

public class _12_堆排序 {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println("排序后 = " + Arrays.toString(arr));
    }

    public static void heapSort(int arr[]) {
        int temp;

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 传入0，因为是位置0的元素变化了，所有从0开始调整
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能： 完成 将 以 i 对应的非叶子节点的树调整成大顶堆
     * 举例 int arr[]={ 4 , 6 , 8 , 5 , 9 };=>i= 1 =>adjustHeap=> 得到 { 4 , 9 , 8 , 5 , 6 }
     * 如果我们再次调用 adjustHeap 传入的是 i= 0 =>得到 { 4 , 9 , 8 , 5 , 6 }=>{ 9 , 6 , 8 , 5 , 4 }
     *
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        // left 是 i节点 的左子节点
        int left = i * 2 + 1;
        for (int k = left; k < length; k = k * 2 + 1) {
            // 如果有右节点，且左子节点的值小于右子节点的值，则将k 指向右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点，把较大的值赋给父节点
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//!!!i 指向 k,继续循环比较
            } else {
                break;
            }
        }
        //当for 循环结束后，我们已经将以i 为父节点的树的最大值，放在了 最顶(局部)
        arr[i] = temp;//将temp值放到调整后的位置
    }
}