package Algorithm.LeetCode.leetcode.editor.cn;//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 5539 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public static void main(String[] args) {
        System.out.println(new Solution5().longestPalindrome("aaaa"));
        System.out.println(new Solution5().longestPalindrome("babad"));
        System.out.println(new Solution5().longestPalindrome("cbbd"));
    }
    public String longestPalindrome(String s) {
        return dpSolution(s);
    }

    // 使用动态规划
    public String dpSolution(String s){
        int len = s.length();
        // dp[i][j]表示 s[i]到s[j] 是否为回文
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();
        int left = 0;// 回文子串左边
        int right = 0;// 回文子串右边
        int maxLen = 0;// 回文子串最大长度
        // i从 len - 1 开始，j从i开始，保证了dp[][]的右上和左下分割线为true，后续 dp[i + 1][j - 1] 可以用
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                // 如果chars[i] == chars[j]，有3个情况，设置dp[i][j]为true，否则默认为false
                // 1. i==j；同一个位置字符，
                // 2. j - i == 1，相邻两个字符
                // 3. dp[i + 1][j - 1] 为回文子串，即s[i + 1]到s[j - 1]为回文
                if(chars[i] == chars[j]){
                    dp[i][j] = (j == i || j - i == 1 || dp[i + 1][j - 1]);
                }
                if(dp[i][j] && maxLen < (j - i + 1)){
                    maxLen = j - i + 1;
                    left = i;
                    right = j + 1;
                }
            }
        }
        return s.substring(left, right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
