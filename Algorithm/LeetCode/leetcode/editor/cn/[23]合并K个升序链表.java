package Algorithm.LeetCode.leetcode.editor.cn;//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
//
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 2106 👎 0


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
class Solution {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        ListNode[] lists = new ListNode[3];
        lists[0] = list1;
        lists[1] = list2;
        lists[2] = list3;
        System.out.println(new Solution().mergeKLists(lists));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // return new Solution().mergeKLists1(lists);
        return new Solution().mergeKLists2(lists);
    }

    /**
     * 暴力解法
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        for(int i = 1; i < lists.length; i++){
            lists[i] = mergeTwoLists(lists[i - 1], lists[i]);
        }
        return lists[lists.length - 1];
    }

    /**
     * 分治算法
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists){
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        if(lists.length == 2){
            return mergeTwoLists(lists[0],lists[1]);
        }

        int mid = lists.length/2;
        ListNode[] l1 = new ListNode[mid];
        for(int i = 0; i < mid; i++){
            l1[i] = lists[i];
        }

        ListNode[] l2 = new ListNode[lists.length-mid];
        for(int i = mid,j=0; i < lists.length; i++,j++){
            l2[j] = lists[i];
        }

        return mergeTwoLists(mergeKLists(l1),mergeKLists(l2));

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }else{
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }
        }
        if(l1 == null) cur.next = l2;
        if(l2 == null) cur.next = l1;
        return res.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
