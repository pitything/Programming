package Algorithm.LeetCode.leetcode.editor.cn;//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 2162 👎 0


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
class Solution19 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        new Solution19().removeNthFromEnd(head, 2);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // return removeNthFromEnd1(head, n);
        return removeNthFromEnd2(head, n);
    }

    /**
     * 暴力解法
     * @param head
     * @param n
     * @return
     */
    private ListNode removeNthFromEnd1(ListNode head, int n) {
        // 节点个数
        int count = 0;
        ListNode cur = head;
        while(cur != null) {
            count ++;
            cur = cur.next;
        }
        cur = head;
        ListNode pre = new ListNode(0);
        int num = count - n;
        if(num == 0) return head.next;
        while(num > 0){
            num--;
            pre = cur;
            cur = cur.next;
        }
        pre.next = cur.next;
        return head;
    }

    /**
     * 双指针(快慢指针)
     * @param head
     * @param n
     * @return
     */
    private ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode left = head;// 慢指针
        ListNode right = head;// 快指针
        // 先将快指针右移n个node
        for(int i = 0; i < n; i++){
            right = right.next;
        }
        // 如果移除第一个node
        if(right == null) return head.next;
        // 将left设置为倒数n+1个节点
        while(right.next != null){
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
