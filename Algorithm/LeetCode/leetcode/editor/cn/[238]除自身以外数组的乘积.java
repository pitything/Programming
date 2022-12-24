package Algorithm.LeetCode.leetcode.editor.cn;//给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
//
// 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。 
//
// 请不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4]
//输出: [24,12,8,6]
// 
//
// 示例 2: 
//
// 
//输入: nums = [-1,1,0,-3,3]
//输出: [0,0,9,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁵ 
// -30 <= nums[i] <= 30 
// 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内 
// 
//
// 
//
// 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。） 
//
// Related Topics 数组 前缀和 👍 1313 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution238 {
    /**
     * 思路：先将每个数左边的乘积计算出来，然后计算右边的乘积，再将左右相乘
     * 复杂度：时间：O() 空间：O()
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        int left = 1;
        for(int i = 1; i < len; i++){
            left *= nums[i - 1];
            res[i] = left;
        }

        int right = 1;
        for(int i = len - 2; i >= 0; i--){
            right *= nums[i + 1];
            res[i] *= right;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution238().productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
