package Algorithm.LeetCode.leetcode.editor.cn;//整数数组 nums 按升序排列，数组中的值 互不相同 。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[
//k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2
//,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。 
//
// 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。 
//
// 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：nums = [1], target = 0
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5000 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 2259 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution33 {
    public static void main(String[] args) {
        int[] arr = new int[]{5,1,3};
        int target = 2;
        // int[] arr = new int[]{8,1,2,3,4,5,6,7};
        // int target = 6;
        System.out.println(new Solution33().search(arr, target));
    }
    public int search(int[] nums, int target) {
        int res = -1;
        int mid;
        int left = 0;
        int right = nums.length - 1;
        int midVal;
        int leftVal;
        int rightVal;
        while(left <= right){
            mid = (left + right) / 2;
            midVal = nums[mid];
            leftVal = nums[left];
            rightVal = nums[right];

            if(leftVal == target) return left;
            if(rightVal == target) return right;
            if(midVal == target) return mid;

            // 如果是有序数组，如：123456
            if(leftVal < rightVal){
                if(midVal > target) right = mid - 1;
                else left = mid + 1;
            }else if(leftVal < midVal){
                // 如果是逆转的数组，如：234561
                if(target < leftVal || target > midVal) {
                    left = mid + 1;
                } else if(leftVal < target && target < midVal ) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                // 如果是逆转的数组，如：612345
                if(target < midVal || target > leftVal) {
                    right = mid - 1;
                } else if(midVal < target && target < leftVal) {
                    left = mid + 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
