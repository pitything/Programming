package Algorithm.LeetCode.leetcode.editor.cn;//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
//
// Related Topics 数组 动态规划 👍 2351 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution198 {

    public int rob(int[] nums) {
        return rob1(nums);
        // return rob2(nums);
    }

    /**
     * 动态规划
     * 思路：定义一个长度为nums.length的dp数组，表示第i户人家能偷到的总金额，
     *      dp[0]=nums[0], dp[1]=max(nums[0],nums[1])
     *      第i户能偷到的：max(第i-1户偷到的， 第i-2户偷到的+第i户)
     *      状态转移方程：dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * 复杂度：时间：O(n) 空间：O(n)
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int res = dp[1];
        for(int i = 2; i < len; i++){
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 优化：减少了dp[]的使用，降低了空间复杂度
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];
        int n1 = nums[0];
        int n2 = Math.max(nums[0], nums[1]);
        int temp;
        for(int i = 2; i < len; i++){
            temp = Math.max(n2, nums[i] + n1);
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
