package Algorithm.LeetCode.leetcode.editor.cn;//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
// 
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
//
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1828 👎 0


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
class Solution148 {
    public ListNode sortList(ListNode head) {
        return sortList1(head, null);
    }

    /**
     * 自顶向下的归并排序
     * 思路：首先通过快慢指针的方式，找到mid节点，然后一层一层合并
     * 复杂度：时间：O(nlogn) 空间：O(logn )
     * @param head
     * @param tail
     * @return
     */
    public ListNode sortList1(ListNode head, ListNode tail){
        if(head == null) return head;
        if(head.next == tail){
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode node1 = sortList1(head, mid);
        ListNode node2 = sortList1(mid, tail);
        return merge1(node1, node2);
    }

    public ListNode merge1(ListNode node1, ListNode node2){
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while(node1 != null && node2 != null){
            if(node1.val <= node2.val){
                cur.next = node1;
                node1 = node1.next;
            }else{
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if(node1 != null) cur.next = node1;
        if(node2 != null) cur.next = node2;
        return res.next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
