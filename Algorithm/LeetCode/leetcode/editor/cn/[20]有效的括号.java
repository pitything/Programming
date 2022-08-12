package Algorithm.LeetCode.leetcode.editor.cn;//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 3450 👎 0


import java.util.HashMap;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution20 {
    public static void main(String[] args) {
        System.out.println(new Solution20().isValid("([])"));
    }

    public boolean isValid(String s) {
        return isValid1(s);
        // return isValid2(s);
        // return isValid3(s);
    }

    public boolean isValid1(String s) {
        int len = s.length();
        if(len % 2 != 0) return false;

        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        // map.put(')', '(');
        // map.put(']', '[');
        // map.put('}', '{');
        Stack<Character> stack = new Stack();
        for (char entity : s.toCharArray()) {
            if(entity == '(' || entity == '[' || entity == '{'){
                stack.push(map.get(entity));
            }else{
                if(stack.empty() || !stack.pop().equals(entity)) return false;
            }
        }
        return stack.empty();
    }

    public boolean isValid2(String s) {
        while(s.contains("()") || s.contains("[]") || s.contains("{}")){
            s = s.replace("()", "")
                    .replace("[]", "")
                    .replace("{}", "");
        }
        return s.length()== 0;
    }

    public boolean isValid3(String s) {
        Stack stack = new Stack();
        for (char entity : s.toCharArray()) {
            if(entity == '(') stack.push(')');
            else if(entity == '[') stack.push(']');
            else if(entity == '{') stack.push('}');
            else {
                if(stack.empty() || !stack.pop().equals(entity)) return false;
            }
        }
        return stack.empty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
