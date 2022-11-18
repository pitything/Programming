package Algorithm.LeetCode.leetcode.editor.cn;//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// Related Topics 并查集 数组 哈希表 👍 1468 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution128 {

    /**
     * 循环 + 哈希表
     * 思路：将数组存入set中，循环遍历数组每个元素，利用hash表查找比nums[i]小的元素是否存在，如果存在，就跳过nums[i]，
     *      因为之后的如果遍历到nums[i]-1时，还会重复遍历nums[i]；如果不存在，则循环找nums[i]+1是否存在，直到不存在则跳出循环，
     *      得到以nums[i]的为开始的最大长度。
     * 复杂度：时间：O(n) 空间：O(n)
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        int maxLen = 1;
        for(int i = 0; i < nums.length; i++){
            int curVal = nums[i];
            int curLen = 0;
            if(!set.contains(curVal - 1)){
                curLen ++;
                while(set.contains(curVal + 1)){
                    curLen++;
                    curVal++;
                }
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    /**
     * 动态规划
     * 思路：定义一个dp数组，dp[i]用于存放s中前i字符是否存在于
     * 复杂度：时间：O() 空间：O()
     * @param nums
     * @return
     */
}
//leetcode submit region end(Prohibit modification and deletion)
