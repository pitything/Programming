package Algorithm.LeetCode.leetcode.editor.cn;//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 2803 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    public static void main(String[] args) {
        System.out.println(new Solution22().generateParenthesis(3));
    }
    public List<String> generateParenthesis(int n) {
        return generateParenthesis1(n);
    }

    /**
     * 回溯算法
     * @param n
     * @return
     */
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        getRes(n, n, "", res);
        return res;
    }

    private void getRes(int left, int right, String s, List<String> res) {
        if(left == 0 && right == 0){
            res.add(s);
            return;
        }
        if(left > 0) getRes(left - 1, right, s + "(", res);
        if(right > left) getRes(left, right - 1, s + ")", res);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
