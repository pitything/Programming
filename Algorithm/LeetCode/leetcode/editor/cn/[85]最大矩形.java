package Algorithm.LeetCode.leetcode.editor.cn;//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 1 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1411 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution85 {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(new Solution85().maximalRectangle(matrix));
    }

    /**
     * 单调栈
     * 思路：解题思路类似于 84题，求最大面积转换成每一层的一维数组的最大面积，如果当前层为0，则一维数组为0，如果当前数组为1，则加上上一层的数量，
     *       如：
     *          [["1","0","1","0","0"],
     *          ["1","0","1","1","1"],
     *          ["1","1","1","1","1"],
     *          ["1","0","0","1","0"]]
     *      可转换成：第一层 ["1","0","1","0","0"]
     *              第二层["2","0","2","1","1"]
     *              第三层["3","1","3","2","2"]
     *              第四层["4","0","0","3","0"]
     *       再利用 84题，单调栈解决问题。
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        int[] nums = new int[col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '1'){
                    nums[j] += 1;
                }else{
                    nums[j] = 0;
                }
            }
            // 获取每一层的一维数组
            res = Math.max(res, getMaxArea(nums));
        }
        return res;
    }

    public int getMaxArea(int[] nums){
        int len = nums.length + 2;
        int[] newNums = new int[len];
        for(int i = 1; i <= len - 2; i++){
            newNums[i] = nums[i - 1];
        }
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && newNums[stack.peek()] > newNums[i]){
                int temp = stack.pop();
                res = Math.max(res, newNums[temp] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
