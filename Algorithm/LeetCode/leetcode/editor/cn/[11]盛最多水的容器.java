package Algorithm.LeetCode.leetcode.editor.cn;//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 返回容器可以储存的最大水量。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 双指针 👍 3711 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution11 {
    public static void main(String[] args) {
        System.out.println(new Solution11().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int minHeight;
        // 最大面积
        int max = 0;
        while(left < right){
            // 对比当前面积 和 新面积大小
            minHeight = Math.min(height[left], height[right]);
            max = Math.max(max, (right - left) * minHeight);
            // 往右找，找到比当前柱子长的柱子
            while(left < right && height[left] <= minHeight) {
                ++left;
            }
            // 往左找，找到比当前柱子长的柱子
            while(left < right && height[right] <= minHeight) {
                --right;
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
