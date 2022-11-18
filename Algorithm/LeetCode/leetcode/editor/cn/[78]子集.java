package Algorithm.LeetCode.leetcode.editor.cn;//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
//
// Related Topics 位运算 数组 回溯 👍 1842 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution78 {
    public static void main(String[] args) {
        System.out.println(new Solution78().subsets(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        // return subsets1(nums);
        return subsets2(nums);
    }

    /**
     * 动态规划
     * 思路：1.先加入空list到res中
     *      2.遍历nums，将nums中每个整数都追加到 res 中存在的子集中。
     *      3.举例：nums为[1,2] --> [[]] --> [[],[1]] --> [[],[1],[2],[1,2]]
     * 复杂度：时间：O() 空间：O()
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 添加一个空集
        res.add(new ArrayList<>());
        for(int i = 0; i < nums.length; i++){
            // 当前子集数
            int len = res.size();
            // 将res中每个子集都追加 nums[i]
            for(int j = 0; j < len; j++){
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, res, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, int i, List<List<Integer>> res, ArrayList<Integer> temp) {
        res.add(new ArrayList(temp));
        for(int k = i; k < nums.length; k++){
            temp.add(nums[k]);
            backtrack(nums, k + 1, res, temp);
            // 将最后一个也就是nums[k] 删除
            temp.remove(temp.size() - 1);
            // 上面这行代码可以改成如下，注意 list.remove(Object) 和remove(int)区别
            temp.remove(new Integer(nums[i]));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
