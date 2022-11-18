package Algorithm.LeetCode.leetcode.editor.cn;//给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
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
//输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
//
// Related Topics 栈 树 深度优先搜索 二叉树 👍 1613 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
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
class Solution94 {
    public static List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        res = new ArrayList<>();
        inorderTraversal2(root);
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(14);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(8);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(10);

        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(12);
        root.right.right.left = new TreeNode(13);

        new Solution94().post(root); // [7, 8, 3, 9, 10, 4, 14, 11, 12, 5, 13, 6, 2, 1]
        System.out.println(res);
    }

    public void post(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while(root != null || !stack.isEmpty()){
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if(root.right == null || root.right == pre){
                res.add(root.val);
                pre = stack.pop();
                root = null;
            }else{
                root = root.right;
            }
        }



    }

    /**
     * 前序遍历（递归）
     */
    public void preorderTraversal1(TreeNode root){
        if(null == root) return ;
        res.add(root.val);
        preorderTraversal1(root.left);
        preorderTraversal1(root.right);
    }

    /**
     * 中序遍历（递归）
     */
    public void inorderTraversal1(TreeNode root){
        if(null == root) return;
        inorderTraversal1(root.left);
        res.add(root.val);
        inorderTraversal1(root.right);
    }

    /**
     * 后续遍历（递归）
     */
    public void postorderTraversal(TreeNode root){
        if(null == root) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        res.add(root.val);
    }

    /**
     * 前序遍历（非递归）
     */
    public void preinorderTraversal2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(null != root){
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }

    /**
     * 中序遍历（非递归）
     */
    public void inorderTraversal2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(null != root){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
    }

    /**
     * 后序遍历（非递归）
     */
    public void postorderTraversal2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if(root.right == null || root.right == pre){
                res.add(root.val);
                pre = stack.pop();
                root = null;
            }else{
                root = root.right;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
