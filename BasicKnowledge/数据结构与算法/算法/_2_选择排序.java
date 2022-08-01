package 算法;

public class _2_选择排序 {
    public static void selectSort(int[] arr) {
        int minIndex;// 最小值的位置
        int min;// 最小值
        //选择排序时间复杂度是 O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {// 说明假定的最小值，并不是最小
                    min = arr[j];// 重置min
                    minIndex = j;// 重置minIndex
                }
            }

            // 如果最小位置变了，则交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i + 1) + "轮后~~");
//            System.out.println(Arrays.toString(arr));
        }
    }
}