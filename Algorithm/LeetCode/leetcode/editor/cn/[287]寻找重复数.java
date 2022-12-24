package Algorithm.LeetCode.leetcode.editor.cn;//给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
//
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。 
//
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,4,2,2]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,1,3,4,2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁵ 
// nums.length == n + 1 
// 1 <= nums[i] <= n 
// nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次 
// 
//
// 
//
// 进阶： 
//
// 
// 如何证明 nums 中至少存在一个重复的数字? 
// 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？ 
// 
//
// Related Topics 位运算 数组 双指针 二分查找 👍 1967 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution287 {
    /**
     * 快慢指针
     * 思路：从下标为0开始，依次查找下标为num[0], num[num[0]], num[num[num[0]]]。。。的数，
     *      如果没有出现多次的数，则每一次查找对应的值都不会相等，可以将这个过程看作一个链表的操作
     *      当有一个出现多次的数，意味着可以通过两个下标找到这个数，即该链表节点有两个入度，也就会形成环。
     *      所以问题就转换成找环的起始位置。
     * 复杂度：时间：O(n) 空间：O(1)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        int cur = 0;
        while(cur != slow){
            cur = nums[cur];
            slow = nums[slow];
        }
        return cur;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
