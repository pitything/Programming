package 算法;

public class _5_快速排序 {
    public static void quickSort(int[] arr, int left, int right) {
        int mid = left;
        int r = right;
        int pivot = arr[mid];
        while (mid < r) {
            while (mid < r && pivot <= arr[r]) r--;
            //如果值小于 key分界值 交换
            arr[mid] = arr[r];
            while (mid < r && pivot > arr[mid]) mid++;
            //如果值大于key分界值 交换
            arr[r] = arr[mid];
        }
        arr[mid] = pivot;
        // 递归左右部分进行快排
        if (mid > left) quickSort(arr, left, mid - 1);
        if (mid < right) quickSort(arr, mid + 1, right);
    }
}