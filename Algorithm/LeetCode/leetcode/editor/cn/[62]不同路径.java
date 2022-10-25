package Algorithm.LeetCode.leetcode.editor.cn;//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
//
// 问总共有多少条不同的路径？ 
//
// 
//
// 示例 1： 
// 
// 
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2： 
//
// 
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
// 
//
// 示例 3： 
//
// 
//输入：m = 7, n = 3
//输出：28
// 
//
// 示例 4： 
//
// 
//输入：m = 3, n = 3
//输出：6 
//
// 
//
// 提示： 
//
// 
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 10⁹ 
// 
//
// Related Topics 数学 动态规划 组合数学 👍 1590 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution62 {
    public static void main(String[] args) {
        int m = 51, n = 9;
        System.out.println(new Solution62().uniquePaths(m, n));
    }
    public int uniquePaths(int m, int n) {
        return uniquePaths2(m, n);
    }

    /**
     * 回溯（超时）
     * 思路：传入m * n的二维数组，从左上走到右下，能向下走 m-1 步，向右左 n-1 步；
     *      利用递归回溯，当步数都用完了，代表走到了终点，路线值+1。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        return getCount(m - 1, n - 1, 0);
    }

    public int getCount(int m, int n, int count){
        if(m == 0 && n == 0) {
            return ++count;
        }
        if(m != 0){
            count = getCount(m - 1, n, count);
        }
        if(n != 0){
            count = getCount(m, n - 1, count);
        }
        return count;
    }

    /**
     * 动态规划
     * 思路：到达[m,n]位置，只能从左边或者上面来
     *      如果从左边来，那么只要知道[m, n-1]位置有多少种路线
     *      如果从上面来，那么只要知道[m-1, n]位置有多少种路线，
     *      加和即可得到[m, n]的路线，因此得出转移方程
     *      [m, n] = [m-1, n] + [m, n-1]
     * @param m
     * @param n
     * @return
     */
    private int uniquePaths2(int m, int n) {
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++){
            res[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            res[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
