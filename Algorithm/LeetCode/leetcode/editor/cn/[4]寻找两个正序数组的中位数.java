package Algorithm.LeetCode.leetcode.editor.cn;//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 5712 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public static void main(String[] args) {
        System.out.println(new Solution4().findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5}));
        // System.out.println(new Solution().findMedianSortedArrays(new int[]{0,0,0,0,0}, new int[]{-1,0,0,0,0,0,1}));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        // 通过设置左右mid，巧妙的避免了 (l1 + l2) 奇数和偶数的判断
        int leftMid = (l1 + l2 + 1) / 2;
        int rightMid = (l1 + l2 + 2) / 2;
        return (fineMidVal(nums1, 0, nums2, 0, leftMid) + fineMidVal(nums1, 0, nums2, 0, rightMid)) / 2;
    }

    public double fineMidVal(int[] nums1, int i, int[] nums2, int j, int k){
        // 如果nums1为空
        if(i >= nums1.length) return nums2[j + k - 1];
        if(j >= nums2.length) return nums1[i + k - 1];
        // k为1，说明取第1个，即下标为0的数，用 Math.min()
        if(k == 1) return Math.min(nums1[i], nums2[j]);

        int midVal1 = (i + k / 2 - 1) < nums1.length ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1) < nums2.length ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if(midVal1 < midVal2){
            return fineMidVal(nums1, i + k / 2, nums2, j, k - k / 2);
        }else {
            return fineMidVal(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
