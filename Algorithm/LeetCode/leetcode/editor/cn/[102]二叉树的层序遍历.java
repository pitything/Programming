package Algorithm.LeetCode.leetcode.editor.cn;//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 1503 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

// import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import Algorithm.LeetCode.leetcode.editor.cn.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
class Solution102 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        // levelOrder1(root, 0);
        levelOrder2(root);
        return res;
    }

    /**
     * 递归
     * @param root
     * @param level
     */
    private void levelOrder1(TreeNode root, int level) {
        if(root == null) return;
        if(level >= res.size()){
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            res.add(temp);
        }else{
            res.get(level).add(root.val);
        }
        levelOrder1(root.left, level+1);
        levelOrder1(root.right, level+1);
    }

    /**
     * 广度优先 + 队列
     */
    public void levelOrder2(TreeNode root){
        if(root == null) return ;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.remove();
                temp.add(cur.val);
                if(cur.left != null ) queue.add(cur.left);
                if(cur.right != null ) queue.add(cur.right);
            }
            res.add(temp);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
