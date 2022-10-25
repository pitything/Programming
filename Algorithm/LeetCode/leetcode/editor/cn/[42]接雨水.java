package Algorithm.LeetCode.leetcode.editor.cn;//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 3710 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution42 {
    public static void main(String[] args) {
        int[] candidates = new int[]{4,2,0,3,2,5};
        System.out.println(new Solution42().trap(candidates));
    }

    public int trap(int[] height) {
        return trap2(height);
    }

    /**
     * 暴力解法
     * 从i开始，i为【第2根～倒数第2根柱子】即[1, len - 2]
     * i位置能存储的雨水量：Math.min(左边的最高，右边的最高) - i位置的高度
     * @param height
     * @return
     */
    private int trap1(int[] height) {
        int res = 0;
        int maxLeft;
        int maxRight;
        // 从第二根 到 倒数第二根
        for (int i = 1; i < height.length - 1; i++) {
            maxLeft = 0;
            maxRight = 0;
            // 找到左边最高的柱子
            for(int j = i; j >= 0; j--){
                maxLeft = Math.max(height[j], maxLeft);
            }
            // 找到右边最高的柱子
            for(int j = i; j <= height.length - 1; j++){
                maxRight = Math.max(height[j], maxRight);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    /**
     * 动态规划
     * 从i开始，i为【第2根～倒数第2根柱子】即[1, len - 2]
     * 不嵌套循环，找到i左边的最高和右边的最高
     *
     * @param height
     * @return
     */
    private int trap2(int[] height) {
        int res = 0;
        int len = height.length;

        int[] maxLeftArr = new int[len];
        maxLeftArr[0] = height[0];
        int[] maxRightArr = new int[len];
        maxRightArr[len - 1] = height[len - 1];

        // 找出【第2根～倒数第2根柱子】左边最高，放入数组中
        for(int j = 1; j < len - 1; j++){
            maxLeftArr[j] = Math.max(height[j], maxLeftArr[j - 1]);
        }
        // 找出【第2根～倒数第2根柱子】右边最高，放入数组中
        for(int j = len - 2; j >= 1; j--){
            maxRightArr[j] = Math.max(height[j], maxRightArr[j + 1]);
        }

        for (int i = 1; i < len - 1; i++) {
            res += Math.min(maxLeftArr[i], maxRightArr[i]) - height[i];
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
