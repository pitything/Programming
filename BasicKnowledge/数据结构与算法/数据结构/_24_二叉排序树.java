package 数据结构;

import java.util.ArrayList;

public class _24_二叉排序树 {
    public HeroNode2 parentNode;
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 12, 5, 4, 1, 9, 6};
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 6};
        // 创建二叉排序树
        HeroNode2 root = new HeroNode2(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            addNode(root, new HeroNode2(arr[i]));
        }
        System.out.println(new _17_二叉树遍历().inorderTraversal(root));
        // 根据id删除节点
        //              7
        //           3      10
        //        1   5    9  12
        //          (4) 6
        delNodeByNo(root, 9);
        System.out.println("删除叶子节点：" + new _17_二叉树遍历().inorderTraversal(root));
        delNodeByNo(root, 10);
        System.out.println("删除有一个子节点的节点：" + new _17_二叉树遍历().inorderTraversal(root));
        delNodeByNo(root, 3);
        System.out.println("删除有两个子节点的节点：" + new _17_二叉树遍历().inorderTraversal(root));
    }

    /**
     * 新增节点
     *
     * @param root
     * @param addNode
     */
    public static void addNode(HeroNode2 root, HeroNode2 addNode) {
        if (root.no >= addNode.no) {
            if (root.left == null) {
                root.left = addNode;
            } else {
                addNode(root.left, addNode);
            }
        } else {
            if (root.right == null) {
                root.right = addNode;
            } else {
                addNode(root.right, addNode);
            }
        }
    }

    /**
     * 根据id删除节点
     *
     * @param root
     * @param no
     */
    public static void delNodeByNo(HeroNode2 root, int no) {
        // 找到需要删除的节点，判断该节点是否有子节点
        HeroNode2 desNode = new _18_二叉树查找().inorderSearch(root, no);
        // 如果没找到，直接返回
        if (desNode == null) return;
        // 查找需要删除节点的父节点
        HeroNode2 parentNode = new _24_二叉排序树().getParentNodeByNo(root, no);
        // 如果没有父节点，说明是根节点，重置根节点
        if (parentNode == null) {
            root.no = 0;
            root.left = null;
            root.right = null;
            return;
        }
        /** 情况1：删除节点为叶子节点：直接父节点的该叶子节点设为null */
        if (desNode.left == null && desNode.right == null) {
            if (desNode.no > parentNode.no) {
                parentNode.right = null;
            } else {
                parentNode.left = null;
            }
            return;
        }

        /** 情况2：删除节点为只有一个子节点：直接将 该节点 用 该节点的子节点 替换 */
        if(desNode.left == null || desNode.right == null){
            if (desNode.no > parentNode.no) {
                parentNode.right = desNode.left == null ? desNode.right : desNode.left;
            } else {
                parentNode.left = desNode.left == null ? desNode.right : desNode.left;
            }
            return;
        }

        /** 情况3：删除节点有两个子节点：直接将 该节点 用 右子树最左 或者 左子树最右节点 替换 */
        if(desNode.left != null && desNode.right != null) {
            HeroNode2 leftest = desNode.right;
            HeroNode2 preNode = null;
            // 找到右子树最左边节点
            while(leftest != null){
                preNode = leftest;
                leftest = leftest.left;
            }
            // 删除右子树最左边节点，并将该节点值放到 no节点位置
            new _19_二叉树删除().preorderDelete(root, preNode.no);
            // 如果右子树最左边节点，有右节点没有左节点，要将右节点上移
            if (desNode.no > parentNode.no) {
                parentNode.right.no = preNode.no;
                parentNode.right.right = (preNode.right != null) ? preNode.right : parentNode.left.right;
            } else {
                parentNode.left.no = preNode.no;
                parentNode.left.right = (preNode.right != null) ? preNode.right : parentNode.left.right;
            }
            return;
        }
    }

    /**
     * 获取指定no的父节点
     * @param root
     * @param no
     * @return
     */
    public HeroNode2 getParentNodeByNo(HeroNode2 root, int no) {
        if (root == null || (root.left == null && root.right == null)) return parentNode;
        if (root.left != null) {
            if (root.left.no == no) {
                parentNode = root;
            } else {
                parentNode = getParentNodeByNo(root.left, no);
            }
        }
        if (root.right != null) {
            if (root.right.no == no) {
                parentNode = root;
            } else {
                parentNode = getParentNodeByNo(root.right, no);
            }
        }
        return parentNode;
    }
}