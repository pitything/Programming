package 算法;

import java.util.*;

public class _16_KMP算法 {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "hello";
        String str2 = "ll";
//        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "ABCDABD";
//        String str2 = "acaacac";
//        String str2 = "AAABAAAA";
        System.out.println(strStr(str1, str2));
        System.out.println(str1.indexOf(str2));
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);

        int[] next = kmpNext(str2);// [0, 0, 0, 0, 1, 2, 0]
        System.out.println("next = " + Arrays.toString(next));

        index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
    }

    public static int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        int j = 0;
        for(int i = 1; i < needle.length(); i++){
            while(j > 0 && needle.charAt(j) != needle.charAt(i)){
                j = next[j - 1];
            }
            if(needle.charAt(j) == needle.charAt(i)){
                next[i] = ++j;
            }
        }

        j = 0;
        for(int i = 0; i < haystack.length(); i++){
            while(j > 0 && needle.charAt(j) != haystack.charAt(i)){
                j = next[j - 1];
            }
            if(needle.charAt(j) == haystack.charAt(i)){
                j++;
            }
            return i - j + 1;
        }
        return -1;
    }

    /**
     * 暴力匹配算法实现
     * @param str1 父字符串
     * @param str2 子字符串
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        int i = 0;//i索引指向s1
        int j = 0;//j索引指向s2
        while (i < str1.length() && j < str2.length()) {// 保证匹配时，不越界
            if (str1.charAt(i) == str2.charAt(j)) {//匹配ok
                i++;
                j++;
            } else {
                //如果匹配不成功，则i 前移j-1个位置，重新对比，i -= (j-1)，j = 0 。
                i -= (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        return (j == str2.length()) ? (i - j) : -1;
    }

    /**
     * 获取到一个字符串(子串)的部分匹配值：ABCDABD -> [0, 0, 0, 0, 1, 2, 0]
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        int j = 0;// j：匹配字符个数；前缀末尾
        for (int i = 1; i < dest.length(); i++) {// i：后缀末尾
            // 如果前几个字符匹配（j>0），第i个字符不匹配，就将第（前一个字符的匹配个数-1）位置的字符 的匹配个数来重置j
            // 上述目的：将j置为0；或者如果出现AABAAA这种情况，相当于把第2个A的匹配个数赋给了最后一个A
            // 如 acaacac -> [0, 0, 1, 1, 2, 3, 2]：求最后一个c的next值，
            // acaacac
            // acac
            // 此时j为3，意味着c之前已经有3个匹配字符，即 acaacac 前3个字符与 子串 acac 的前3个字符相等
            // 如果第3个字符a的next值为0，说明前3个字符不存在更小的对称子串，要重新开始匹配；next值=0
            // 如果第3个字符a的next值不为0，如例为1，说明 aca 字符串还存在长度为1的对称子串 a，
            // 用c和第1个后面的字符串匹配，相当于把后缀切小了，由acac变成了ac，如果匹配上，就++，即j=1+1=2；匹配不上继续上一步操作直至next值=0
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            // 如果匹配，就将个数+1
            if (dest.charAt(j) == dest.charAt(i)) {
                next[i] = ++j;
            }
        }
        return next;
    }

    /**
     * kmp搜索算法
     * @return 如果是 -1 就是没有匹配到，否则返回第一个匹配的位置
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表, 是子串对应的部分匹配表
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        int j = 0;
        //遍历
        for (int i = 0; i < str1.length(); i++) {
            //需要处理 str1 .charAt(i) ！=str2 .charAt(j), 去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
}