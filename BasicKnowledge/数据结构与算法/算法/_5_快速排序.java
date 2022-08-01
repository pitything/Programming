package 算法;

public class _5_快速排序 {
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//pivot 中轴值
        int temp;//临时变量，作为交换时使用

        //while循环的目的是：让比pivot值小放到左边，比pivot值大放到右边
        while (l < r) {
            // 注意：此处不写=号，如果写了=，会有问题
            // 如：[1, 4, 2, 5, 3, 7, 23]：1.使得l为5，r为4，导致break，没有达到左小右大的目的；2.如果数组中的中轴值最小或最大，导致数组l++，r--数组越界
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) l++;
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) r--;

            //如果l>=r说明pivot 的左右两的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) break;

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完后，必然是满足左小于等于，右大于等于条件，不必在参与下次循环判断；
            // 如果不自增自减，左右都是pivot值，会出现死循环，如：[3,4,5,5,6,7]
            l++;
            r--;
        }

        // 如果 l==r, 必须l++,r--, 否则为出现栈溢出：java.lang.StackOverflowError
        // 如：[1,2,3]，r==l==1,
        if (l == r) {
            l++;
            r--;
        }

        //向左递归
        if (left < r) quickSort(arr, left, r);
        //向右递归
        if (right > l) quickSort(arr, l, right);
    }
}