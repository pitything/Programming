package Algorithm.LeetCode.leetcode.editor.cn;//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
//
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1339 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


// import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution114 {
    /**
     * 先序遍历（非递归）
     * 思路：这题是先序遍历的变形，先序遍历每个节点，将节点保存在新节点的右子节点即可。
     *      要注意的是：返回值为void，意思就是要将root节点内容改变，即改变左右子节点。
     * 复杂度：时间：O(n) 空间：O(n)
     * @param root
     */
    public void flatten(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tempRoot = root;
        TreeNode res = new TreeNode(0);
        TreeNode cur = res;
        while(!stack.isEmpty() || tempRoot != null){
            while(tempRoot != null){
                cur.left = null;
                cur.right = new TreeNode(tempRoot.val);
                cur = cur.right;
                stack.push(tempRoot);
                tempRoot = tempRoot.left;
            }
            tempRoot = stack.pop();
            tempRoot = tempRoot.right;
        }
        root.val = res.right.val;
        root.right = res.right.right;
        root.left = null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
