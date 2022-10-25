package Algorithm.LeetCode.leetcode.editor.cn;//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
//
// 
//
// Related Topics 数组 数学 矩阵 👍 1459 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution48 {
    public void rotate(int[][] matrix) {
        rotate1(matrix);
    }

    /**
     * 数学方法：先将矩阵上下翻转，在沿着右上左下对角线翻转
     * @param matrix
     */
    private void rotate1(int[][] matrix) {
        int len = matrix.length;
        // 上下翻转
        for(int i = 0; i < len / 2; i++){
            for(int j = 0; j < len; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] = temp;
            }
        }
        // 右上左下对角线翻转
        for (int i = 0; i < len; i++){
            for(int j = 0; j < i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 左上右下对角线翻转
        // for(int i = 0; i < len - 1; i ++){
        //     for(int j = 0; j < len - i - 1; j ++){
        //         int temp = matrix[i][j];
        //         matrix[i][j] = matrix[len - 1 - j][len - 1 - i];
        //         matrix[len - 1 - j][len - 1 - i] = temp;
        //     }
        // }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
