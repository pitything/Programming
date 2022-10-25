package Algorithm.LeetCode.leetcode.editor.cn;//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2269 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution46 {
    public static void main(String[] args) {
        System.out.println(new Solution46().permute(new int[]{1,2,3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        // 数组元素总数
        int len = nums.length;
        // 结果集合
        List<List<Integer>> res = new ArrayList<>();
        // 当前放入数组的结果
        Stack<Integer> path = new Stack<>();
        // 标记元素是否已经加入到path中
        boolean[] used = new boolean[len];

        dfs(nums, 0, res, used, path);
        return res;
    }

    /**
     * 回溯算法
     * @param nums 原数组
     * @param times 加入个数
     * @param res 结果
     * @param used 标记元素是否使用到
     * @param path 单个结果
     */
    private void dfs(int[] nums, int times, List<List<Integer>> res, boolean[] used, Stack<Integer> path) {
        if(nums.length == times){
            res.add((Stack)path.clone());
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                used[i] = true;
                path.push(nums[i]);
                dfs(nums, times + 1, res, used, path);
                used[i] = false;
                path.pop();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
