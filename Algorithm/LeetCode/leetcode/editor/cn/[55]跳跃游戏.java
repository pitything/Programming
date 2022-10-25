package Algorithm.LeetCode.leetcode.editor.cn;//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2061 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution55 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        // 前i-1 个格子能到达的最远位置
        int preMaxlen = nums[0];
        for(int i = 1; i < len; i++){
            // 如果前i-1个格子不能到达第i个位置，则返回false
            if(preMaxlen < i) {
                return false;
            }else {
                // 如果前i-1个格子可以到达第i个位置，
                // 比较 前i-1个格子可以到达最远位置 和 第i个格子可以到达的最远位置 取大者作为前i个格子可以到达的最远位置
                preMaxlen = Math.max(preMaxlen, nums[i] + i);
                // 优化点：可以到达最后直接返回true
                if(preMaxlen >= len - 1) return true;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
