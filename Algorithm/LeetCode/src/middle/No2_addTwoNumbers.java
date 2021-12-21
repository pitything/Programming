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
import java.util.List;

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
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 解释：9999999 + 9999 = 807
 *
 * @Author haoliang.jiang
 * @Date 2021/12/15 2:11 上午
 */
public class No2_addTwoNumbers {

    public static void main(String[] args){
//        int[] arr1 = {2,4,3};
//        int[] arr2 = {5,6,4};
        int[] arr1 = {9,9,9,9,9,9,9};
        int[] arr2 = {9,9,9,9};
        ListNode l1 = getNodeByArray(arr1);
        ListNode l2 = getNodeByArray(arr2);
        addTwoNumbers1(l1.next.next, l2.next.next);
    }

    /**
     * @description 两个整数数组逆序相加
     * @param l1
     * @param l2
     * @return int
     * @Author haoliang.jiang
     * @Date 2021/12/15 2:26 上午
     * @Update
     */
    private static ListNode addTwoNumbers1(ListNode l1, ListNode l2){
        ListNode result = new ListNode();
        ListNode node;
        node = result;
        int sum;
        int ext = 0;
        while(l1 != null || l2 != null){
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + ext;
            // 不用sum / 10，因为除法效率低
            ext = sum > 9 ? 1 : 0;
            sum = sum % 10;
            node.next = new ListNode(sum);
            node = node.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if(ext == 1){
            node.next = new ListNode(1);
        }

        return result.next;
    }

    /**
     * 通过int[] 转换为 ListNode
     * @param array int数组
     * @return
     */
    private static ListNode getNodeByArray(int[] array){
        ListNode node = new ListNode();
        ListNode node1 = new ListNode();
        ListNode node2;

        node.next = node1;
        for (int i = 0; i < array.length; i++) {
            node2 = new ListNode();
            node2.val = array[i];
            node1.next = node2;
            node1 = node2;
        }
        return node;
    }

    static class ListNode{
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
