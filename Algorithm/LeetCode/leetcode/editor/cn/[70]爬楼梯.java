package Algorithm.LeetCode.leetcode.editor.cn;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 2706 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution70 {
    public int climbStairs(int n) {
        return climbStairs2(n);
    }

    /**
     * 动态规划
     * 思路：因为一次只能爬 1个 或者 2个 楼梯，那么 第n个 楼梯，只能从 第n-1 或者 第n-2 的位置爬上来
     *      得出转移方程：res[n] = res[n-1] + res[n-2]，其中res[1] = 1， res[2] = 2;
     * 复杂度：时间：O(n)；空间：O(n)
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        if(n == 1) return 1;

        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 2;
        for(int i = 3; i < n + 1; i++){
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[n];
    }

    /**
     * 动态规划(优化)
     * 思路：在法1的基础上进行优化，将空间复杂度降为O(1)。设置包含3个数字的滑动数组，
     *      每增加一个台阶，就将该台阶的值 设置为 前两个台阶 的加和，并且向左滑动
     * 复杂度：时间：O(n)  空间：O(1)
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int a = 0, b = 1, c = 1;
        for(int i = 0; i < n - 1; i++){
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
