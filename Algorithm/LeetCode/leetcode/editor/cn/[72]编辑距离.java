package Algorithm.LeetCode.leetcode.editor.cn;//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 2661 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution72 {
    public static void main(String[] args) {
        System.out.println(new Solution72().minDistance("horse", "ros"));
    }

    /**
     * 动态规划
     * 思路：建立一个dp[i+1][j+1]数组，dp[i][j]表示 word1从0-i 变成 word2从0-j 的最小操作数，
     *      dp[0][0] 表示两个都是空字符串，值为0。
     *      dp[1][0] 就是 dp[0][0]一步操作后可以得到，即 dp[0][0]+1，同理推 dp[i][0] = i
     *      dp[0][1] 就是 dp[0][0]一步操作后可以得到，即 dp[0][0]+1，同理推 dp[0][j] = j
     *      存在两种情况：
     *      1⃣️ word1[i] == word2[j] 那么只要将dp[i-1][j-1]的值 赋给 dp[i][j] 即 dp[i][j] = dp[i-1][j-1]
     *      2⃣️ word1[i] != word2[j] 可以有三种操作，替换、删除、插入
     *          替换：将word1[i] 改成 word2[j]，就满足了第一种情况，dp[i][j] = dp[i-1][j-1] + 1
     *          删除：将word1[i]删除，那么就看 dp[i-1][j]的值， dp[i][j] = dp[i-1][j] + 1
     *          插入：往word1[i]后插入一个，也就是将word2[j]删除，就看dp[i][j-1]的值，dp[i][j] = dp[i][j-1] + 1
     *          总结：选择 替换、删除、插入 操作数最小的作为 dp[i][j]的值
     * 复杂度：时间：O(mn) 空间：O(mn)
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        dp[0][0] = 0;
        for(int i = 1; i < l1 + 1; i ++){
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for(int i = 1; i < l2 + 1; i ++){
            dp[0][i] = dp[0][i - 1] + 1;
        }
        for(int i = 1; i < l1 + 1; i++){
            for(int j = 1; j < l2 + 1; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[l1][l2];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
