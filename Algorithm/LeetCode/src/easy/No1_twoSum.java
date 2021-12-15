/******************************************************************
 * 文件名称: easy.No1_twoNumSum
 * 系统名称:
 * 模块名称:
 * 软件版权:
 * 功能说明:
 * 系统版本:
 * 开发人员: haoliang.jiang
 * 开发时间: 2021/12/15 1:24 上午
 * 修改记录:
 * 程序版本             修改日期                修改人员                        修改单号                               修改说明
 *******************************************************************/
package easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Title: 1. 两数之和
 *
 * Description:
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * Example:
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * @Author haoliang.jiang
 * @Date 2021/12/15 1:30 上午
 */
public class No1_twoSum {
    public static void main (String[] args){
        int[] indexs;
        int[] nums = {2,7,11,15};
        int target = 9;

        indexs = getTwoSum1(nums, target);
        System.out.println(Arrays.toString(indexs));

        indexs = getTwoSum2(nums, target);
        System.out.println(Arrays.toString(indexs));
    }

    /**
     * @description 使用双重for循环解题
     * @param nums 输入数组
     * @param target 目标值
     * @return 位置数组
     * @Author haoliang.jiang
     * @Date 2021/12/15 1:34 上午
     * @Update
     */
    private static int[] getTwoSum1(int[] nums, int target){
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * @description 使用哈希表解题
     * @param nums 输入数组
     * @param target 目标值
     * @return 位置数组
     * @Author haoliang.jiang
     * @Date 2021/12/15 1:51 上午
     * @Update
     */
    private static int[] getTwoSum2(int[] nums, int target){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(hashMap.containsKey(nums[i])){
                return new int[]{hashMap.get(nums[i]),i};
            }else{
                /** 将差值作为key，下标作为value，放入HashMap中 */
                hashMap.put(target - nums[i], i);
            }
        }
        return null;
    }
}
