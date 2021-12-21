/******************************************************************
 * 文件名称: middle.No2_addTwoNumbers
 * 系统名称:
 * 模块名称:
 * 软件版权:
 * 功能说明:
 * 系统版本:
 * 开发人员: haoliang.jiang
 * 开发时间: 2021/12/15 2:10 上午
 * 修改记录:
 * 程序版本             修改日期                修改人员                        修改单号                               修改说明
 *******************************************************************/
package middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: 3.无重复字符的最长子串
 *
 * Description:
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * Example:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 *
 * 输入: s = ""
 * 输出: 0
 *
 * @Author haoliang.jiang
 * @Date 2021/12/21 10:24 上午
 */
public class No3_maxUniqueString {

    public static void main(String[] args){
//        String str1 = "abcabcbb";
//        System.out.println(lengthOfLongestSubstring(str1));
//        String str2 = "bbbbb";
//        System.out.println(lengthOfLongestSubstring(str2));
//        String str3 = "pwwkew";
//        System.out.println(lengthOfLongestSubstring(str3));
//        String str4 = "";
//        System.out.println(lengthOfLongestSubstring(str4));
        String str5 = "abba";
        System.out.println(lengthOfLongestSubstring(str5));
    }

    /**
     * @description 无重复字符的最长子串长度
     * @param s
     * @return int
     * @Author haoliang.jiang
     * @Date 2021/12/21 10:24 上午
     * @Update
     */
    private static int lengthOfLongestSubstring(String s){
        int maxLength = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i))){
                start = Math.max(start, map.get(s.charAt(i)));
            }
            // 这里加1，是为了abc这种没有重复字符的String，防止 i - start = 2 - 0 = 2 出现
            maxLength = Math.max(maxLength, i - start + 1);
            // 这里加1，是因为上面加了1
            map.put(s.charAt(i), i + 1);
        }
        return maxLength;
    }
}
