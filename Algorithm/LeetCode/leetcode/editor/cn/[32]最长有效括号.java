package Algorithm.LeetCode.leetcode.editor.cn;//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
//
// Related Topics 栈 字符串 动态规划 👍 1949 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution32 {
    public static void main(String[] args) {
        String s = 	"(()((((((()((()())))((((())((()(((((((()(()((()()()(()(()())(()(()()((((())))()(((()())))(()()))()(()()()()(((((())(()))))((()))())))()((((((()))())))()(()))(())))((((()())(((((()()())(((((())(()())(()))))()(()()))()))))))())))(((())(()(()()))(()))()(((())))())((((()(((()))))))()(()(()))()()(()()))))))))((()))))))(())((()((()))()))((((((()())))))(()((())((((()))))(()(()()()()(()))()()(()(()))(()()(((((((()())(())(()())((())())()(()())((())()())())(()())))())))(())())())(())((()())(((()()))()))()()))()(()(())((((((((())))()((())((()((((((((((()))))(()(((((())(()(()())())))((())())))))()))(()((()()))((()((())()()()((()(())())((())())(()()(((())))))())()()(()))()())(()(()((())))((((()()(())))())(())(()(()(())())())(()()())()(())())))(()()(((())))((()()(((())()()(()())((((()()(()())(()((((()(()()(()(()(((()((()())(()()))(()((((()(((((()))))()()))(((()((((((()(()()()()())()))(()(())))))((()(((()())())))(((()()))(()(()(((((((()()))(()(())))())()(())())(())(()))(())(()))()()(()()())))))()))()((())(((()((((((((())()()))())))((()())(";
        s = "())";
        System.out.println(new Solution32().longestValidParentheses(s));
    }

    public int longestValidParentheses(String s) {
        // return longestValidParentheses1(s);
        return longestValidParentheses2(s);
    }

    /**
     * 暴力解法（超时）
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        int len = s.length();
        int maxLen = 0;
        for(int i = 0; i < len; i++){
            for(int j = i; j < len; j++){
                if(isValid(s.substring(i, j + 1)) && (j + 1 - i) > maxLen){
                    maxLen = j + 1 - i;
                }
            }
        }
        return maxLen;
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack();
        char c;
        for(int i = 0; i < s.length(); i++){
            c = s.charAt(i);
            if(c == '(') stack.push(')');
            else{
                if(stack.isEmpty() || stack.pop() != c) return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 动态规划
     * 假设遍历到第i个字符，存在两种情况：
     * 1.s[i] == '('，不能构成有效子串，为0
     * 2.s[i] == ')'，又存在两种情况
     *      2.1. s[i - 1]为'('，那么dp[i] = dp[i - 2] + 2
     *      2.2. s[i - 1]为')'，根据dp[i - 1]考虑，
     *          如果s[i - 1 - dp[i - 1]]为'('，dp[i] = dp[i - 1] + 2，
     *              特例："()(())"这种情况，dp[i] = dp[i−1] + 2 + dp[i−2-dp[i−1]]
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int maxLen = 0;
        int len = s.length();
        int dp[] = new int[len];
        for(int i = 1; i < len; i++){
            if(s.charAt(i) == ')'){
                if(s.charAt(i - 1) == '(') {
                    dp[i] = 2 + ((i - 2) >= 0 ? dp[i - 2] : 0);
                } else if(s.charAt(i - 1) == ')' &&
                        i - 1 - dp[i - 1] >= 0 &&
                        s.charAt(i - 1 - dp[i - 1]) == '('){
                    dp[i] = dp[i - 1] + 2 + (i - 2 - dp[i - 1] > 0 ? dp[i - 2 - dp[i - 1]] : 0);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
