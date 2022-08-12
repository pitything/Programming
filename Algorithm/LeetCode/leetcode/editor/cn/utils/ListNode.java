package Algorithm.LeetCode.leetcode.editor.cn.utils;

/**
 * @author JonnyOu
 * @date 2022/2/24 16:10
 * @email JonnyOu1012@gmail.com
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}
    public ListNode(int x) { val = x; }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头节点
    public ListNode(int[] arr){
        if(arr == null || arr.length == 0)
            throw new Error("arr can not be empty");

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    //以当前节点为头节点的链表信息字符串 方便查看
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder("[");
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val + ",");
            cur = cur.next;
        }
        res.replace(res.length() - 1, res.length(), "]");
        return res.toString();
    }
}


