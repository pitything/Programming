package Algorithm.LeetCode.leetcode.editor.cn;//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1806 👎 0


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
class Solution98 {
    List<Integer> res = new ArrayList<>();

    /**
     * 树的中序遍历
     * 思路：将树的每个节点以中序遍历的方式获取，并在过程中判断当前节点的val是否大于前一个节点的val，将结果输出
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        // return inorderTraserval1(root);
        return inorderTraserval2(root);
    }

    /**
     * 使用递归
     * @param root
     * @return
     */
    private boolean inorderTraserval2(TreeNode root) {
        if(root == null) return true;
        if(!inorderTraserval2(root.left)) return false;
        if(!res.isEmpty() && root.val <= res.get(res.size() - 1)) return false;
        res.add(root.val);
        if(!inorderTraserval2(root.right)) return false;
        return true;
    }


    /**
     * 使用栈
     * @param root
     * @return
     */
    private boolean inorderTraserval1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(!res.isEmpty() && root.val <= res.get(res.size() - 1)) return false;
            res.add(root.val);
            root = root.right;
        }
        return true;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
