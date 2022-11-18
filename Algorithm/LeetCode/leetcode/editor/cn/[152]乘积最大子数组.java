package Algorithm.LeetCode.leetcode.editor.cn;//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//
// 测试用例的答案是一个 32-位 整数。 
//
// 子数组 是数组的连续子序列。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数 
// 
//
// Related Topics 数组 动态规划 👍 1850 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution152 {
    /**
     * 动态规划
     * 思路：分别计算出以数组第i个元素结尾的子序列乘积的最大和最小值，那么第i+1个元素的最大和最小值有三种可能
     *      如果nums[i-1]为正数，那么结果可能是它本身，或者是乘以最大值
     *      如果nums[i-1]为负数，那么结果可能是乘以最小值
     *      保证了如果 第i个 数为负数，且第i+1个数为负数，乘积反而更大
     *      举例：-6，2，-3  ---》   nums[2] = 36
     * 复杂度：时间：O(n) 空间：O(n)
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] max = new int[len];
        int[] min = new int[len];
        max[0] = nums[0];
        min[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < len; i++){
            max[i] = Math.max(nums[i], Math.max(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            min[i] = Math.min(nums[i], Math.min(nums[i] * max[i - 1], nums[i] * min[i - 1]));
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
