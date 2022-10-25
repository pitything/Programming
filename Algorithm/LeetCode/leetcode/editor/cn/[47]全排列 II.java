package Algorithm.LeetCode.leetcode.editor.cn;//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1222 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int times, Stack<Integer> path, boolean[] used, List<List<Integer>> res) {
        if(times == nums.length ){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            // 如果是已经使用过了 或者
            // 当前元素和上一个元素相等且上一个元素没有使用过（说明是回溯过来的，不是顺序过来的）则跳过
            if(used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            path.push(nums[i]);
            dfs(nums, times + 1, path, used, res);
            used[i] = false;
            path.pop();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
