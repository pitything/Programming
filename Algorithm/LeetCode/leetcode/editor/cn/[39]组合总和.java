package Algorithm.LeetCode.leetcode.editor.cn;//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都 互不相同 
// 1 <= target <= 500 
// 
//
// Related Topics 数组 回溯 👍 2116 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution39 {
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(new Solution39().combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum2(candidates, target);
        // return combinationSum1(candidates, target);
    }

    /**
     * 回溯算法（未剪枝）
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) return res;
        dfs(candidates, 0, target, new ArrayDeque<>(), res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) return;

        if (target == 0) {
            // 添加一个新的对象
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < candidates.length; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i；
            // 如果可以重复，此时begin应该设置为 i + 1
            // 设置为 i 也排除了重复的结果如：[2, 3, 2], [3, 2, 2]
            dfs(candidates, i, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }

    /**
     * 回溯算法（剪枝）：先对cadidates数组进行排序，如果【target-第i个】小于0，那么i后面的就不用考虑了
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;
        // 排序
        Arrays.sort(candidates);
        dfs2(candidates, target, 0, new Stack<Integer>(), res);
        return res;
    }

    public static void dfs2(int[] candidates, int target, int begin, Stack<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            // 添加一个新的对象
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 剪枝
            if (target - candidates[i] < 0) break;
            path.push(candidates[i]);
            dfs2(candidates, target - candidates[i], i, path, res);
            path.pop();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
