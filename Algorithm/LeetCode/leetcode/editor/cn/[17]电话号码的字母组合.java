package Algorithm.LeetCode.leetcode.editor.cn;//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 2046 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution17 {
    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        // return letterCombinations1(digits);
        return letterCombinations2(digits);
    }

    /**
     * 暴力解法
     * @param digits
     * @return
     */
    public List<String> letterCombinations1(String digits) {
        HashMap<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");

        // 返回结果
        List<String> res = new ArrayList<String>(){{add("");}};
        // 辅助list，用于存放每次添加后的结果
        List<String> list = new ArrayList<String>(){{add("");}};
        for(int i = 0; i < digits.length(); i++){
            // 当前数字对应的字母
            String temp = map.get(digits.charAt(i) + "");
            for(int j = 0; j < temp.length(); j++ ){
                for (String entity : res) {
                    list.add(entity + temp.charAt(j));
                }
            }
            res.addAll(list);
        }
        // 将结果中长度不达标，或者空字符串去除
        res = res.stream().filter(i -> i.length() == digits.length() && !i.equals("")).sorted().collect(Collectors.toList());
        return res;
    }

    /**
     * 回溯
     * @param digits
     * @return
     */
    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations2(String digits) {
        if(digits == null || digits.length() == 0) return res;
        getList(digits, 0, "");
        return res;
    }

    private void getList(String digits, int index, String s) {
        // 终止条件，到最后一个数字
        if(index == digits.length()) {
            res.add(s);
            return;
        }
        for(int i = 0; i < letterMap[digits.charAt(index) - '0'].length(); i++){
            getList(digits, index + 1, s + letterMap[digits.charAt(index) - '0'].charAt(i));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
