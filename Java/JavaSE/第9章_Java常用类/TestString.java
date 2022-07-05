import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /** String对象的创建 */
        System.out.println("-----------String对象的创建-------------");
        String str1 = "str1"; // str1
        String str2 = new String(); // 空
        String str3 = new String("str3"); // str3
        String str4 = new String(new char[]{'s', 't', 'r', '4'}, 2, 1); // r
        String str5 = new String(new byte[]{'s', 't', 'r', '5'}, 0, 3); // str
        String str6 = new String(new StringBuilder("str6")); // str6
        String str7 = new String(new StringBuilder("str7")); // str7

        /** String类常用方法 */
        System.out.println("-----------String类常用方法-------------");
        String testStr = " testStr|12|3 ";
        System.out.println(testStr.length()); // 返回字符串的长度：14
        System.out.println(testStr.charAt(3)); // 返回某索引处的字符：s
        System.out.println(testStr.isEmpty()); // 判断是否是空字符串：false
        System.out.println(testStr.toLowerCase()); // 所有字符转换为小写：  teststr|12|3
        System.out.println(testStr.toUpperCase()); // 所有字符转换为大写：  TESTSTR|12|3
        System.out.println(testStr.trim()); // 忽略前导空白和尾部空白：testStr|12|3
        System.out.println(testStr.equals("test")); // 比较字符串的内容是否相同：false
        System.out.println(testStr.equalsIgnoreCase(" testSTR|12|3 ")); // 同equals，忽略大小写：true
        System.out.println(testStr.concat("aaa")); // 将指定字符串连接到此字符串的结尾： testStr|12|3 aaa
        System.out.println(testStr.compareTo(" test")); // 比较两个字符串的大小：9
        System.out.println(testStr.substring(2)); // 从beginIndex开始截取到最后：estStr|12|3
        System.out.println(testStr.substring(3, 5)); // 从beginIndex开始截取到endIndex(不包含)：st
        System.out.println(testStr.endsWith("est ")); // 是否以指定的后缀结束：false
        System.out.println(testStr.startsWith(" te")); // 是否以指定的前缀开始：true
        System.out.println(testStr.startsWith("te", 1)); // 从指定索引开始的子字符串是否以指定前缀开始：true
        System.out.println(testStr.contains("est")); // 是否包含指定字符串：true
        // indexOf和lastIndexOf方法如果未找到都是返回-1
        System.out.println(testStr.indexOf("t")); // 第一次出现处的索引：1
        System.out.println(testStr.indexOf("t", 1)); // 从指定的索引开始往右,第一次出现处的索引：1
        System.out.println(testStr.lastIndexOf("t")); // 最右边出现处的索引：6
        System.out.println(testStr.lastIndexOf("t", 5)); // 从指定的索引开始往左，第一次出现处的索引，：4
        System.out.println(testStr.replace("t", "T")); // 字符串替换，后者替换前者： TesTSTr|12|3
        System.out.println(testStr.replaceAll("\\d+", "A")); // 使用正则替换： testStr|A|A
        System.out.println(testStr.replaceFirst("\\d+", "A")); // 使用正则替换第一个： testStr|A|3
        System.out.println(testStr.matches("\\d+")); // 字符串是否匹配给定的正则表达式：false
        System.out.println(Arrays.toString(testStr.split("\\|"))); // 正则表达式的拆分字符串：[ testStr, 12, 3 ]
        System.out.println(Arrays.toString(testStr.split("\\|", 2))); // 正则表达式的拆分字符串，不超过limit个，超出部分放在最后一个：[ testStr, 12|3 ]

        /** String与其他类型的转换 */
        System.out.println("-----------String与其他类型的转换------------");
        // String <===> 基础类型、包装类
        System.out.println(Integer.parseInt("123")); // String ===> 基础类型、包装类：123
        System.out.println(String.valueOf(123)); // String <=== 基础类型、包装类：123
        // String <===> 字符数组
        System.out.println("123".toCharArray()); // String ===> 字符数组：123
        char[] temp = new char[]{'4', '5', '6'};
        "123".getChars(1, 2, temp, 1); // String ===> 字符数组：426
        System.out.println(temp);
        System.out.println(new String(temp)); // String <=== 字符数组：426
        // String <===> 字节数组
        System.out.println(Arrays.toString("123".getBytes())); // String <=== 字节数组：[49, 50, 51]
        byte[] byteArray = new byte[]{'4', '5', '6'};
        System.out.println(new String(byteArray)); // String <=== 字节数组：456
        System.out.println(new String("中国".getBytes("UTF-8"), "GBK"));// 乱码：涓浗

        /** StringBuffer对象的创建 */
        System.out.println("--------------StringBuffer对象的创建-------------");
        StringBuffer stringBuffer1 = new StringBuffer(); // 默认为初始容量16的char[]
        StringBuffer stringBuffer2 = new StringBuffer(10); // 容量为10的char[]
        StringBuffer stringBuffer3 = new StringBuffer("10"); // 容量为2+16，内容为"10"
        /** StringBuffer常用方法 */
        System.out.println(stringBuffer1.append("12332123")); // 末尾追加：12332123
        System.out.println(stringBuffer1.delete(2, 3)); // 删除 [2,3)之间的数据：1232123
        System.out.println(stringBuffer1.replace(4, 5, "aa")); // 替换[4,5)：1232aa23
        System.out.println(stringBuffer1.insert(0, "ccc")); // 指定位置传入：ccc1232aa23
        System.out.println(stringBuffer1.reverse()); // 反转：32aa2321ccc
        System.out.println(stringBuffer1.length()); // 长度：11
        System.out.println(stringBuffer1.substring(3, 8)); // 截取[3,8)，返回String，不会改变本身：a2321
        System.out.println(stringBuffer1.indexOf("a")); // 第一个a的位置：2
        System.out.println(stringBuffer1.charAt(1)); // 下标为1的字符：2
        stringBuffer1.setCharAt(2, 'F'); // 下标为2设置为F：32Fa2321ccc
        System.out.println(stringBuffer1);
    }
}