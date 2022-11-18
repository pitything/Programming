package Algorithm.LeetCode.leetcode.editor.cn;//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1980 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution200 {
    /**
     * dfs
     * 思路：采用深度优先遍历，如果越界，或者不为'1'（即为海或者已经遍历过），则跳出递归，否则向上下左右方向分别递归，直到程序最后，
     *      岛屿数量加一
     * 复杂度：时间：O(mn) 空间：O(mn)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                res += dfs(grid, i, j);
            }
        }
        return res;
    }

    public int dfs(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;
        if(grid[i][j] != '1') return 0;
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
