package Algorithm.LeetCode.leetcode.editor.cn;//给定一个包含红色、白色和蓝色、共 n 个元素的数组
// nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 必须在不使用库的sort函数的情况下解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
//
// Related Topics 数组 双指针 排序 👍 1445 👎 0


import java.util.Arrays;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution75 {
    public void sortColors(int[] nums) {
        nums = bubbleSort(nums);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{ 1 , 2 , 3 , 6};
        // int[] nums = new int[]{-43 , 51 , 7 , 8, 1 , 2 , 3 , 6};
        // System.out.println(Arrays.toString(bubbleSort2(nums)));
        // System.out.println(Arrays.toString(selectSort2(nums)));
        // System.out.println(Arrays.toString(insSort2(nums)));
        // System.out.println(Arrays.toString(shellSort2(nums)));
        // System.out.println(Arrays.toString(quickSort2(nums, 0, nums.length - 1)));
        // System.out.println(Arrays.toString(mergeSort2(nums, 0, nums.length - 1, new int[nums.length])));
        // System.out.println(Arrays.toString(radixSort2(nums)));
        // System.out.println(Arrays.toString(heapSort2(nums)));
    }

    /**
     * 冒泡排序
     */
    private static int[] bubbleSort(int[] nums){
        boolean exchanged = false;
        int temp;
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = 0; j < nums.length - 1 - i; j++){
                if(nums[j] > nums[j + 1]){
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    exchanged = true;
                }
            }
            if(!exchanged) break;
            exchanged = false;
        }
        return nums;
    }

    /**
     * 选择排序
     */
    private static int[] selectSort(int[] nums){
        int min, minIndex;
        for(int i = 0; i < nums.length; i++){
            min = nums[i];
            minIndex = i;
            for(int j = 1 + i; j < nums.length; j++){
                if(nums[j] < min){
                    min = nums[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                nums[i] = min;
                nums[minIndex] = nums[i];
            }
        }
        return nums;
    }

    /**
     * 插入排序
     */
    private static int[] insSort(int[] nums){
        int insVal, insIndex;
        for(int i = 1; i < nums.length; i++){
            insIndex = i - 1;
            insVal = nums[i];
            while(insIndex >= 0 && nums[insIndex] > insVal){
                nums[insIndex + 1] = nums[insIndex];
                insIndex--;
            }
            if(insIndex + 1 != i){
                nums[insIndex + 1] = insVal;
            }
        }
        return nums;
    }

    /**
     * 希尔排序
     */
    private static int[] shellSort(int[] nums){
        for(int gap = nums.length / 2; gap > 0; gap /= 2){
            int insval, insIndex;
            for(int i = gap; i < nums.length; i++){
                insval = nums[i];
                insIndex = i - gap;
                while(insIndex >= 0 && nums[insIndex] > insval){
                    nums[insIndex + gap] = nums[insIndex];
                    insIndex -= gap;
                }
                if(insIndex + gap != i){
                    nums[insIndex + gap] = insval;
                }
            }
        }
        return nums;
    }

    /**
     * 快速排序
     */
    private static int[] quickSort(int[] nums, int left, int right){
        int mid = left;
        int r = right;
        int pivot = nums[mid];
        while(mid < r){
            while(mid < r && nums[r] >= pivot) r--;
            nums[mid] = nums[r];
            while(mid < r && pivot > nums[mid]) mid++;
            nums[r] = nums[mid];
        }
        nums[mid] = pivot;
        if(mid > left) quickSort(nums, left, mid - 1);
        if(mid < right) quickSort(nums, mid + 1, right);
        return nums;
    }

    /**
     * 归并排序
     */
    private static int[] mergeSort(int[] nums, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid, temp);
            mergeSort(nums, mid + 1, right, temp);
            merge(nums, left, mid, right, temp);
        }
        return nums;
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int l = left;
        int rl = mid + 1;
        int t = 0;
        while(l <= mid && rl <= right){
            temp[t++] = (nums[l] <= nums[rl]) ? nums[l++] : nums[rl++];
        }
        while (l <= mid) temp[t++] = nums[l++];
        while(rl <= right) temp[t++] = nums[rl++];
        t = 0;
        while(left <= right) nums[left++] = temp[t++];
    }

    /**
     * 基数排序，优化了负数的情况
     */
    private static int[] radixSort(int[] nums) {
        int maxNum = nums[0];
        int minNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }
        if (minNum < 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] -= minNum;
            }
            maxNum -= minNum;
        }

        int maxlen = (maxNum + "").length();

        int[][] radix = new int[10][nums.length];
        int[] numCount = new int[10];
        for (int i = 0, n = 1; i < maxlen; i++, n *= 10) {
            for (int j = 0; j < nums.length; j++) {
                int remain = nums[j] / n % 10;
                radix[remain][numCount[remain]] = nums[j];
                numCount[remain]++;
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < numCount[j]; k++) {
                    nums[index++] = radix[j][k];
                }
                numCount[j] = 0;
            }
        }
        if (minNum < 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] += minNum;
            }
        }
        return nums;
    }

    /**
     * 堆排序
     */
    private static int[] heapSort(int[] nums){
        int len = nums.length;
        for(int i = len /2 - 1; i >= 0; i--){
            adjust2MaxHeap(nums, i, len);
        }
        int temp;
        for(int i  = 0; i < len - 1; i++){
            temp = nums[0];
            nums[0] = nums[len - 1 - i];
            nums[len - 1 - i] = temp;
            adjust2MaxHeap(nums, 0, len - 1 - i);
        }
        return nums;
    }

    private static void adjust2MaxHeap(int[] nums, int index, int len){
        int temp = nums[index];
        int left = index * 2 + 1;
        for(int i = left; i < len; i = i * 2 + 1){
            if((i + 1) < len && nums[i + 1] > nums[i]) i++;
            if(temp < nums[i]){
               nums[index] = nums[i];
               index = i;
            }else{
                break;
            }
        }
        nums[index] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
