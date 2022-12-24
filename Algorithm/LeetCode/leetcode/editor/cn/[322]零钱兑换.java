package Algorithm.LeetCode.leetcode.editor.cn;//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 👍 2203 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution322 {
    public static void main(String[] args) {
        System.out.println(new Solution322().coinChange(new int[]{2}, 3));
    }

    /**
     * 动态规划
     * 思路：定义一个dp数组，dp[i]表示金额i的最少硬币数量，可以得到状态转移方程
     *      dp[i] = min(dp[i], dp[i - coins[j]])
     *      第i个金额的最少硬币数量为，（i-每个面额的硬币 的最小数量） + 1
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int len = amount + 1;
        int[] dp = new int[len];
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j = coins.length - 1; j >= 0; j--){
                if(i >= coins[j]){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]]);
                }
            }
            if(dp[i] != Integer.MAX_VALUE) dp[i]++;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
