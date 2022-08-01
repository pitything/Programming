package 数据结构;

public class _26_红黑树 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr2 = {10, 12, 8, 9, 7, 6};
        int[] arr3 = {11, 7, 16, 3, 9, 26, 18, 17};
//        int[] arr3 = {4, 2, 3};
//        int[] arr3 = {10, 11, 7, 6, 8, 9};
        // 创建二叉排序树
        //      4
        //   3     6
        //       5   7
        //            8
        RedBlackTreeNode root = new RedBlackTreeNode(arr[0]);
        //      10
        //   8     12
        //  7  9
        // 6
        RedBlackTreeNode root2 = new RedBlackTreeNode(arr2[0]);
        //      10
        //   7     11
        //  6 8
        //     9
        RedBlackTreeNode root3 = new RedBlackTreeNode(arr3[0]);
        for (int i = 1; i < arr.length; i++) {
//            addNode(root, new RedBlackTreeNode(arr[i]));
        }
        for (int i = 1; i < arr2.length; i++) {
//            addNode(root2, new RedBlackTreeNode(arr2[i]));
        }
        for (int i = 1; i < arr3.length; i++) {
            addNode(root3, new RedBlackTreeNode(arr3[i]));
        }
    }

    /**
     * 新增节点
     *
     * @param root
     * @param addNode
     */
    public static void addNode(RedBlackTreeNode root, RedBlackTreeNode addNode) {
        if (root.val >= addNode.val) {
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
     * 左旋转
     * @param p
     */
    public static void leftRotate(RedBlackTreeNode p) {
        // 先记录p的右节点
        RedBlackTreeNode rightChild = p.right;

        // 1. p的右子树设置为右节点的左子树
        p.right = rightChild.left;
        // 左子树不为空，需要更新父节点
        if (rightChild.left != null) {
            rightChild.left.parent = p;
        }

        // 2. p的右节点的左子树设置为p
        rightChild.left = p;
        p.parent = rightChild;

        // 3. p的父节点的儿子 指向 p的右节点
        rightChild.parent = p.parent;
        // 父节点指向右儿子
        if (p.parent == null) {
            return;
        } else if (p == p.parent.left) {
            p.parent.left = rightChild;
        } else {
            p.parent.right = rightChild;
        }
    }

    /**
     * 右旋转
     * @param p
     */
    public static void rightRotate(RedBlackTreeNode p) {
        // 记录p的左儿子
        RedBlackTreeNode leftChild = p.left;
        // 1. p的左子树设置为左节点的右子树
        p.left = leftChild.right;
        // 右子树不为空，需要更新父节点
        if (leftChild.right != null) {
            leftChild.right.parent = p;
        }

        // 2. p的左节点的右子树设置为p
        leftChild.right = p;
        p.parent = leftChild;

        // 3. p的父节点的儿子 指向 p的左节点
        leftChild.parent = p.parent;
        // 父节点指向左儿子
        if (p.parent == null) {
            return;
        } else if (p.parent.left == p) {
            p.parent.left = leftChild;
        } else {
            p.parent.right = leftChild;
        }
    }

    public static int getTreeHeight(RedBlackTreeNode root){
        if (root == null) return 0;
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

class RedBlackTreeNode {
    public int val;
    public RedBlackTreeNode left;
    public RedBlackTreeNode right;
    // 记录节点颜色的color属性，暂定true表示红色
    public boolean color;
    // 为了方便迭代插入，所需的parent属性
    public RedBlackTreeNode parent;

    // 一些构造函数，根据实际需求构建
    public RedBlackTreeNode() {
    }
    public RedBlackTreeNode(int val) {
        this.val = val;
    }
}