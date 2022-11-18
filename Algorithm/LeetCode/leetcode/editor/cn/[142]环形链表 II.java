package Algorithm.LeetCode.leetcode.editor.cn;//给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。 
//
// 不允许修改 链表。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
//
// 
//
// 进阶：你是否可以使用 O(1) 空间解决此题？ 
//
// Related Topics 哈希表 链表 双指针 👍 1853 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import Algorithm.LeetCode.leetcode.editor.cn.utils.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution142 {

    public ListNode detectCycle(ListNode head) {
        // return detectCycle1(head);
        return detectCycle2(head);
    }

    /**
     * 哈希+循环
     * 思路：循环遍历链表，将每个节点放入map中，如果重复了，就说明有环，重复节点即环开始位置
     * 复杂度：时间：O(n) 空间：O(n)
     * @return
     */
    public ListNode detectCycle1(ListNode head){
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while(cur != null){
            if(set.contains(cur)) return cur;
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    /**
     * 数学解法
     * 思路：设置两个指针：fast、slow；fast指针每次移动两格，slow每次移动一格，如果有环，那么会存在slow==fast的情况
     *      当这个情况出现，slow移动到环起始点的距离，就是head移动到环起始点的距离。
     *      定义一个指针从head开始，一直往后移动，slow也往后移动，直到slow==head，则该位置为环起始点
     * 复杂度：时间：空间：
     * @return
     */
    public ListNode detectCycle2(ListNode head){
        if(head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while(slow != null && fast != null){
            slow = slow.next;
            fast = (fast.next == null) ? null : fast.next.next;
            if(slow == fast){
                ListNode cur = head;
                while(cur != slow){
                    slow = slow.next;
                    cur = cur.next;
                }
                return cur;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
