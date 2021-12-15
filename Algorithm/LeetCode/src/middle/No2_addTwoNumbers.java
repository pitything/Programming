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

/**
 * Title: 2.两数相加
 *
 * Description:
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头
 * 注意：逆序相加：超过10进1，不是向左，而是向右。
 *
 * Example:
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807
 *
 * @Author haoliang.jiang
 * @Date 2021/12/15 2:11 上午
 */
public class No2_addTwoNumbers {

    public static void main(String[] args){
        int[] l1 = {2,4,3};
        int[] l2 = {5,6,4};

        addTwoNumbers1(l1, l2);
    }

    /**
     * @description
     * @param l1
     * @param l2
     * @return int
     * @Author haoliang.jiang
     * @Date 2021/12/15 2:26 上午
     * @Update
     */
    private static int addTwoNumbers1(int[] l1, int[] l2){
        int maxLength = Math.max(l1.length, l2.length);
        int[] result = new int[maxLength + 1];
        int num;
        int count;
        for(int i = 0; i < maxLength; i++){
            num = (l1[i] + l2[i]) % 10;
            count = (l1[i] + l2[i]) / 10;
            result[i] = num + count;
        }
        for (int i = 0; i < result.length; i++) {

        }

    }

}
