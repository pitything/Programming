package Algorithm.LeetCode.leetcode.editor.cn;//给你一个二叉树的根节点 root ， 检查它是否轴对称。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 2185 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import java.util.LinkedList;

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
class Solution101 {

    public boolean isSymmetric(TreeNode root) {
        // return dfs(root.left, root.right);
        return isSymmetric2(root);
    }

    /**
     * 递归
     * @param left
     * @param right
     * @return
     */
    private boolean dfs(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    /**
     * 使用队列
     */
    public boolean isSymmetric2(TreeNode root){
        if(root == null || (root.left == null && root.right == null)) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()){
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
