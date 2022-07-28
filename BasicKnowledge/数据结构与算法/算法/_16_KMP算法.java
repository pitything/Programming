import java.util.Arrays;

public class _16_KMP算法 {
    public static void main(String[] args) {
        //测试暴力匹配算法
        String str1 = "ABCDABD";
//        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "AAABAAAA";
//        String str2 = "AAABAAAC";
        String str2 = "ABCDABD";
        System.out.println(str1.indexOf(str2));
        int index = violenceMatch(str1, str2);
        System.out.println("index = " + index);

        int[] next = kmpNext(str2);//[ 0 , 1 , 2 , 0 ]
        System.out.println("next = " + Arrays.toString(next));

        index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);
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
            if (str1.charAt(i) == str1.charAt(j)) {//匹配ok
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
     * 获取到一个字符串(子串)的部分匹配值：ABCDABD -> [0, 0, 0, 0, 1, 0, 0]
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        // 第一个字符为0可以确定
        int j = 0;
        for (int i = 1; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            // 此处循环过后有条件导致break：j=0；j = 新加入字符上次出现的位置
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                // 此处的目的是将j重置为第j-1个匹配的字符的匹配长度，如 AABAAA，将最后一个A的j设置为第二个A的j，而不是直接用j=0
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                next[i] = ++j;
            }
        }
        return next;
    }

    /**
     * kmp搜索算法
     * @return 如果是- 1 就是没有匹配到，否则返回第一个匹配的位置
     * @paramstr1 源字符串
     * @paramstr2 子串
     * @paramnext 部分匹配表, 是子串对应的部分匹配表
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
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