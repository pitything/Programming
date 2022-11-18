package Algorithm.LeetCode.leetcode.editor.cn;//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例 1： 
// 
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCCED"
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"SEE"
//输出：true
// 
//
// 示例 3： 
// 
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = 
//"ABCB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
//
// Related Topics 数组 回溯 矩阵 👍 1470 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution79 {
    boolean isExist = false;

    /**
     * 回溯
     * 思路：1.从[0,0]开始，遍历board数组每一个元素，作为word的起始位置，进行回溯
     *      2.如果越界、已经存在、已经访问过、不相等的情况，则返回回溯函数
     *      3.如果匹配到了word的最后一个元素，且最后一个元素能匹配上，则说明存在
     *      4.如果能匹配上，但是还没到word的最后一个元素，则分别向上下左右方向进行回溯遍历
     * 复杂度：时间：O() 空间：O()
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if(board == null) return false;
        int row = board.length, col = board[0].length;
        // 是否被访问过
        boolean[][] isVisited = new boolean[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                // 1.从[0,0]开始，遍历board数组每一个元素，作为word的起始位置，进行回溯
                backtrack(i, j , 0, board, word, isVisited);
            }
        }
        return isExist;
    }

    private void backtrack(int i, int j, int k, char[][] board, String word, boolean[][] isVisited) {
        // 2.如果越界、已经存在、已经访问过、不相等的情况，则返回回溯函数
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length
            || isExist || isVisited[i][j] || word.charAt(k) != board[i][j]) {
            return;
        }
        // 3.如果匹配到了word的最后一个元素，且最后一个元素能匹配上，则说明存在
        if(k == word.length() - 1){
            isExist = true;
            return;
        }
        // 4.如果能匹配上，但是还没到word的最后一个元素，则分别向上下左右方向进行回溯遍历
        isVisited[i][j] = true;
        backtrack(i + 1, j, k + 1, board, word, isVisited);
        backtrack(i - 1, j, k + 1, board, word, isVisited);
        backtrack(i, j + 1, k + 1, board, word, isVisited);
        backtrack(i, j - 1, k + 1, board, word, isVisited);
        isVisited[i][j] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
