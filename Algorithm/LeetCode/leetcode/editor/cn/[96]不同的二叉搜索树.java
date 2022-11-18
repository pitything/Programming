package Algorithm.LeetCode.leetcode.editor.cn;//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1988 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution96 {
    public static void main(String[] args) {
        System.out.println(new Solution96().numTrees(2));
    }

    /**
     * 数学解法：卡特兰数
     * 思路：设有n个数，res(n) 表示 n个节点总的排列数，f(n) 表示 以n为根节点的排列数
     *      因此：res(n) = f(1)+f(2)+f(3)+...+f(n)，
     *      容易得知，f(i) 的值为左边 i-1 个节点的排列数 乘以 右边 n-i 个节点的排列数
     *      因此：f(i) = f(i-1) * f(n-i)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i < n + 1; i++){
            for(int j = 1; j < i + 1; j++){
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
