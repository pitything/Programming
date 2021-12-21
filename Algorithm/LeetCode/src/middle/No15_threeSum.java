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
package middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Title: 15. 三数之和
 *
 * Description:
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * Example:
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 输入：nums = []
 * 输出：[]
 * 
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * @Author haoliang.jiang
 * @Date 2021/12/21 13:37 下午
 */
public class No15_threeSum {

    public static void main(String[] args){
        int[] nums = {0,0,0};
//        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }

    /**
     * @description 三数之和
     * @param nums
     * @return List<List<Integer>>
     * @Author haoliang.jiang
     * @Date 2021/12/21 10:24 上午
     * @Update
     */
    private static List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        if(nums.length < 3){
//            return result;
//        }
//        Arrays.sort(nums);
//        int sum;
//        List<Integer> list;
//        for (int i = 0; i < nums.length - 2; i++) {
//            sum = nums[i] + nums[i + 1] + nums[i + 2];
//            if(sum == 0){
//                list = new ArrayList<>();
//                list.add(nums[i]);
//                list.add(nums[i + 1]);
//                list.add(nums[i + 2]);
//                result.add(list);
//            }
//        }
//        return result;

        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for(int i = 0; i < len; ++i) {
            if(nums[i] > 0) return lists;

            if(i > 0 && nums[i] == nums[i-1]) continue;

            int curr = nums[i];
            int L = i + 1, R = len - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if(tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L < R && nums[L+1] == nums[L]) ++L;
                    while (L < R && nums[R-1] == nums[R]) --R;
                    ++L;
                    --R;
                } else if(tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }
}
