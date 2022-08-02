package 算法;

public class _4_希尔排序 {
    // 交换法
    public static void shellSort(int[] arr) {
        int temp;
        int count = 0;

        /**
        // 第一次排序
        for(int i = 5; i < arr.length; i++){
            for(int j = i - 5; j >= 0; j -= 5){
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第一轮 = " + Arrays.toString(arr));

        // 第二次排序
        for(int i = 2; i < arr.length; i++){
            for(int j = i - 2; j >= 0; j -= 2){
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
                System.out.println("希尔排序第二轮 = " + Arrays.toString(arr));
            }
        }
        System.out.println("希尔排序第二轮 = " + Arrays.toString(arr));

        // 第三次排序
        for(int i = 1; i < arr.length; i++){
            for(int j = i - 1; j >= 0; j -= 1){
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第三轮 = " + Arrays.toString(arr));
         */

        // 总结上述规律的如下代码
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组 arr.length / gap 有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }else {
                        break;
                    }
//                    System.out.println("希尔排序第" + (++count) + "轮 = " + Arrays.toString(arr));
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮 = " + Arrays.toString(arr));
        }
    }

    //优化： 交换法->移位法
    public static void shellSort2(int[] arr) {
        // 增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int idx = i - gap;
                int val = arr[i];
                while(idx >= 0 && arr[idx] > val){
                    arr[idx + gap] = arr[idx];
                    idx -= gap;
                }
                arr[idx + gap] = val;
            }
        }
    }
}