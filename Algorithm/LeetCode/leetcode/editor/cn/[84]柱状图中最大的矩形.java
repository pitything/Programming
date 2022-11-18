package Algorithm.LeetCode.leetcode.editor.cn;//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=10⁵ 
// 0 <= heights[i] <= 10⁴ 
// 
//
// Related Topics 栈 数组 单调栈 👍 2223 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution84 {
    public static void main(String[] args) {
        // System.out.println(new Solution().largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(new Solution84().largestRectangleArea(new int[]{4,2,0,3,2,4,3,4}));
        // System.out.println(new Solution().largestRectangleArea(new int[]{4,2,0,3,2,4,3,4}));
    }

    public int largestRectangleArea(int[] heights) {
        // return largestRectangleArea3(heights);
        int len = heights.length + 2;
        int[] newHeight = new int[len];
        for(int i = 1; i <= len - 2; i++){
            newHeight[i] = heights[i - 1];
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++) {
            while (!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]){
                int k = stack.pop();
                res = Math.max(res, newHeight[k] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 暴力法（超时）
     * 思路：1.求矩形最大面积，就是用 每根柱子的高度 * (右边小于该柱子高度的对应柱子的下标 - 左边小于该柱子高度的下标 - 1）
     *      2.使用暴力穷举法，遍历每一根柱子，分别向左向右找小于当前柱子高度的最近的柱子下标
     * 复杂度：时间：O(n^2) 空间：O(1)
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        int max = 0;
        for(int i = 0; i < len; i++){
            int left = i;
            int right = i;
            // 右边小于该柱子高度的对应柱子的下标
            while(left >= 0 && heights[left] >= heights[i]) left--;
            // 左边小于该柱子高度的下标
            while(right < len && heights[right] >= heights[i]) right++;
            max = Math.max(max, heights[i] * (right - left - 1));
        }
        return max;
    }

    /**
     * 动态规划
     * 思路：1.在暴力算法的基础上进行优化
     *      2.设置lefts, rights两个数组，用于存储第i个柱子左右两边小于当前柱子高度的最近的柱子下标
     *      3.lefts[0]为-1，向右遍历，
     *      4.如果第 n 个柱子比左边的小，则第 n 个柱子可以使用第 n - 1 个柱子的left值，直到找到比第 n 个柱子小的柱子下标
     *      5.如果第 n 个柱子比左边的大，则第 n 个柱子的left值 为 n - 1
     *      7.rights[len - 1] 为len，向左遍历同理
     * 复杂度：时间：O() 空间：O()
     */
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        int len = heights.length;
        int[] lefts = new int[len];
        int[] rights = new int[len];
        lefts[0] = -1;
        rights[len - 1] = len;
        for(int i = 1; i < len ; i++){
            int before = i - 1;
            while(before >= 0 && heights[i] <= heights[before]){
                before = lefts[before];
            }
            lefts[i] = before;
        }
        for(int i = len - 2; i >= 0 ; i--){
            int after = i + 1;
            while(after < len && heights[i] <= heights[after]){
                after = rights[after];
            }
            rights[i] = after;
        }
        for(int i = 0; i < len; i++){
            res = Math.max(res, heights[i] * (rights[i] - lefts[i] - 1));
        }
        return res;
    }

    /**
     * 单调栈
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length + 2;
        int[] newHeight = new int[len];
        for(int i = 1; i < len - 1; i++){
            newHeight[i] = heights[i - 1];
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && newHeight[stack.peek()] > newHeight[i]){
                int h = newHeight[stack.pop()];
                res = Math.max(res, h * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return res;
    }

    public Integer f(Integer a, Boolean flag){
        return flag ? a : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
