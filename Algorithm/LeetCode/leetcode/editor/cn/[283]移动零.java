package Algorithm.LeetCode.leetcode.editor.cn;//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 请注意 ，必须在不复制数组的情况下原地对数组进行操作。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1,0,3,12]
//输出: [1,3,12,0,0]
// 
//
// 示例 2: 
//
// 
//输入: nums = [0]
//输出: [0] 
//
// 
//
// 提示: 
// 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能尽量减少完成的操作次数吗？ 
//
// Related Topics 数组 双指针 👍 1801 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution283 {

    public void moveZeroes(int[] nums) {
        // moveZeroes1(nums);
        moveZeroes1(nums);
    }

    /**
     * 思路：遍历数组，将不为0的数字往前移，后面的就是为0的数字
     * 复杂度：时间：O(n) 空间：O(1)
     * @param nums
     */
    private void moveZeroes1(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[j++] = nums[i];
            }
        }
        for(; j < nums.length; j++){
            nums[j] = 0;
        }
    }

    /**
     * 复杂度：时间：O() 空间：O()
     * @param nums
     */
    private void moveZeroes2(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
