package Algorithm.LeetCode.leetcode.editor.cn;//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
//
// Related Topics 递归 链表 👍 2597 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import Algorithm.LeetCode.leetcode.editor.cn.utils.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution21 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        new Solution21().mergeTwoLists(list1, list2);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return mergeTwoLists1(list1, list2);
    }
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        ListNode cur = res;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                cur.next = list1;
                cur = cur.next;
                list1 = list1.next;
            }else if(list1.val > list2.val){
                cur.next = list2;
                cur = cur.next;
                list2 = list2.next;
            }
        }
        if(list1 == null) {
            cur.next = list2;
        }
        if(list2 == null) {
            cur.next = list1;
        }
        return res.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
