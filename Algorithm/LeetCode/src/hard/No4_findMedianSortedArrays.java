/******************************************************************
 * 文件名称: middle.No2_addTwoNumbers
 * 系统名称:
 * 模块名称:
 * 软件版权:
 * 功能说明:
 * 系统版本:
 * 开发人员: haoliang.jiang
 * 开发时间: 2021/12/15 2:10 上午
 * 修改记录:
 * 程序版本             修改日期                修改人员                        修改单号                               修改说明
 *******************************************************************/
package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: 4. 寻找两个正序数组的中位数
 *
 * Description:
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * Example:
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 * @Author haoliang.jiang
 * @Date 2021/12/21 13:37 下午
 */
public class No4_findMedianSortedArrays {

    public static void main(String[] args){
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
//        System.out.println(findMedianSortedArrays1(nums1, nums2));
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }

    /**
     * @description 寻找两个正序数组的中位数
     * @param nums1
     * @param nums2
     * @return int
     * @Author haoliang.jiang
     * @Date 2021/12/21 10:24 上午
     * @Update
     */
    private static double findMedianSortedArrays1(int[] A, int[] B){
//        int midLocal = (nums1.length + nums2.length) / 2;
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /**
     * @description 寻找两个正序数组的中位数，时间复杂度为O(m+n)
     * @param nums1
     * @param nums2
     * @return int
     * @Author haoliang.jiang
     * @Date 2021/12/21 10:24 上午
     * @Update
     */
    private static double findMedianSortedArrays2(int[] nums1, int[] nums2){
        int length = nums1.length + nums2.length;
        int[] newArray = new int[length];
        /** 先合并两个数组 */
        for (int i = 0; i < nums1.length; i++) {
            newArray[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            newArray[i + nums1.length] = nums2[i];
        }
        /** 排序 */
        Arrays.sort(newArray);
        if(length % 2 == 0){
            return (newArray[length / 2] + newArray[(length / 2) - 1]) / 2.0;
        }else{
            return newArray[length / 2];
        }
    }
}
