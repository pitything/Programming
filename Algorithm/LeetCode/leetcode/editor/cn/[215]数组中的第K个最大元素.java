package Algorithm.LeetCode.leetcode.editor.cn;//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1963 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution215 {
    /**
     * 快速排序
     * 思路：快速排序的改编题型，如果插入的位置是k-1，那么则将nums[k - 1]返回
     * 复杂度：时间：O() 空间：O()
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, k, 0, nums.length - 1);
    }

    private int quickSort(int[] nums, int k, int left, int right) {
        int l = left;
        int r = right;
        int pivot = nums[l];
        while(l < r){
            while (l < r && nums[r] <= pivot) r--;
            nums[l] = nums[r];
            while(l < r && nums[l] >= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        if(l == k - 1){
            return nums[l];
        }else if(l > k - 1){
            return quickSort(nums, k, left, l - 1);
        }else {
            return quickSort(nums, k, l + 1, right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
