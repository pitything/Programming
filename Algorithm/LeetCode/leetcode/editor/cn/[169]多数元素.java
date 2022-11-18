package Algorithm.LeetCode.leetcode.editor.cn;//给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//提示：
//
// 
// n == nums.length 
// 1 <= n <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
//
// Related Topics 数组 哈希表 分治 计数 排序 👍 1619 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution169 {
    /**
     * Boyer-Moore 投票算法
     * 思路：遍历数组中的每一个元素，如果下一个元素和当前元素相等，则投票数加1，否则投票数-1，最后留下的肯定是大于一半的众数
     * 复杂度：时间：O(n) 空间：O(1)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = nums[0];
        for(int i = 0; i < nums.length; i++){
            if(count == 0){
                res = nums[i];
            }
            count += (nums[i] == res) ? 1 : -1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
