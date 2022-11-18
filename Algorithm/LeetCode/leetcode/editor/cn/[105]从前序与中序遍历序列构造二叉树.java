package Algorithm.LeetCode.leetcode.editor.cn;//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1788 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import java.util.Arrays;

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
class Solution105 {
    /**
     * 递归
     * 思路：先序遍历第一个值即是根节点，遍历中序遍历的数组，找到根节点在中序遍历数组的位置，左边即左子节点，右边即右子节点
     *      分别传入左右的先序和中序数组进行递归调用，找到子节点的子节点，直到左右两边的数组长度为0为止
     * 复杂度：时间：O(n) 空间：O(n)
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for(int i = 0; i < inorder.length; i++){
            if(inorder[i] == preorder[0]) {
                int[] left_pre = Arrays.copyOfRange(preorder, 1, i + 1);
                int[] left_in = Arrays.copyOfRange(inorder, 0, i);
                int[] right_pre = Arrays.copyOfRange(preorder, i + 1, preorder.length);
                int[] right_in = Arrays.copyOfRange(inorder, i + 1, preorder.length);
                root.left = buildTree(left_pre, left_in);
                root.right = buildTree(right_pre, right_in);
            }
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
