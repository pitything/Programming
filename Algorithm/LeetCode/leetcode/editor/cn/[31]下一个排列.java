package Algorithm.LeetCode.leetcode.editor.cn;//整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
//
// 
// 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。 
// 
//
// 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就
//是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。 
//
// 
// 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 
// 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。 
// 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。 
// 
//
// 给你一个整数数组 nums ，找出 nums 的下一个排列。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 双指针 👍 1859 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution31 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1};
        new Solution31().nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void nextPermutation(int[] nums) {
        nextPermutation1(nums);
    }

    /**
     * 双指针
     * @param nums
     */
    private void nextPermutation1(int[] nums) {
        int len = nums.length;
        if(len == 1) return;

        int left = len - 1;
        int right = len - 1;
        // 从右往左找到非升序的第一个数
        while(left > 0 && nums[left] <= nums[left - 1]){
            left--;
        }
        // 如果数组是从右往左升序的，则将该数组反转，即返回一个最小的数组
        if(left == 0) {
            // Arrays.sort(nums);
            reverse(nums, left, len - 1);
            return;
        }
        // 找到比非升序第一个数大的一个数，交换这两个数的位置
        while(nums[right] <= nums[left - 1]){
            right--;
        }
        int temp = nums[left - 1];
        nums[left - 1] = nums[right];
        nums[right] = temp;
        // 反转(left, len - 1]
        reverse(nums, left, len - 1);
    }

    public void reverse(int[] arr, int beg, int end){
        // 不要再循环里声明对象，占内存
        int temp;
        while(beg < end){
            temp = arr[beg];
            arr[beg] = arr[end];
            arr[end] = temp;
            beg++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
