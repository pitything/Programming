package Algorithm.LeetCode.leetcode.editor.cn;//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
//
// Related Topics 递归 字符串 动态规划 👍 3132 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10 {
    public static void main(String[] args) {
        // System.out.println(new Solution10().isMatch("a", "."));
        // System.out.println(new Solution10().isMatch("a", "."));
        System.out.println(new Solution10().isMatch("aaa", "a*"));
        // System.out.println(new Solution10().isMatch("aaa", "ab*a*c*a"));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j] 表示 s的前i个字符 是否可以和 p的前j个字符 匹配
        boolean dp[][] = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // i从0开始，为了匹配当s为""，p为"" 或者 .* 的情况
        for(int i = 0; i < m + 1; i++){
            // j从1开始，因为当p为""时，只有dp[0][0]能匹配，dp[1 ~ len1 + 1][0]必定为false
            for(int j = 1; j < n + 1; j++){
                // 如果p的第j个字符不是*
                if(p.charAt(j - 1) != '*'){
                    // 如果匹配上了，则看上一个字符是否匹配，dp[i][j]能否匹配由dp[i-1][j-1]决定
                    if(match(s, p , i, j)) dp[i][j] = dp[i - 1][j - 1];
                }else{
                    // * 匹配了0次，直接丢掉*和*之前的一个字符，总共两个字符
                    dp[i][j] = dp[i][j - 2];
                    // 如果s[i - 1] == p[j - 2]相等，可以尝试匹配s[i - 1]多次，边匹配边把s[i - 1]扔掉，s串回退，等待进行下一次匹配
                    if(match(s, p , i, j - 1))  dp[i][j] |= dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    public boolean match(String s, String p, int i, int j){
        if(i == 0) return false;
        if(p.charAt(j - 1) == '.') return true;
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
