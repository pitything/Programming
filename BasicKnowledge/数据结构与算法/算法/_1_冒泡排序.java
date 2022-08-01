package 算法;

import java.util.Arrays;

public class _1_冒泡排序 {
    private static int count = 10;

    public static void main(String[] args) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = (int) (Math.random() * count * 100);//生成一个[0, count * 100) 数
        }
        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        long beg = System.currentTimeMillis();

        _1_冒泡排序.bubbleSort(arr);
        _2_选择排序.selectSort(arr);
        _3_插入排序.insertSort(arr);
        _4_希尔排序.shellSort(arr);
        _4_希尔排序.shellSort2(arr);
        _5_快速排序.quickSort(arr, 0 , arr.length - 1);
        _6_归并排序.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        _7_基数排序.radixSort(arr);
        _12_堆排序.heapSort(arr);
        System.out.println("耗时：" + (System.currentTimeMillis() - beg));
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp;// 临时变量
        boolean flag = false;// 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));
            if (!flag) {// 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false;// 重置flag!!!, 进行下次判断
            }
        }
    }
}