public class _25_平衡二叉树<T> {
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
        HeroNode2 root = new HeroNode2(arr[0]);
        //      10
        //   8     12
        //  7  9
        // 6
        HeroNode2 root2 = new HeroNode2(arr2[0]);
        //      10
        //   7     11
        //  6 8
        //     9
        HeroNode2 root3 = new HeroNode2(arr3[0]);
        for (int i = 1; i < arr.length; i++) {
//            new _24_二叉排序树().addNode(root, new HeroNode2(arr[i]));
//            fix2BBST(root);
        }
        for (int i = 1; i < arr2.length; i++) {
//            new _24_二叉排序树().addNode(root2, new HeroNode2(arr2[i]));
//            fix2BBST(root2);
        }
        for (int i = 1; i < arr3.length; i++) {
            new _24_二叉排序树().addNode(root3, new HeroNode2(arr3[i]));
            fix2BBST(root3);
        }
        System.out.println(new _17_二叉树遍历().inorderTraversal(root));
        System.out.println(new _17_二叉树遍历().inorderTraversal(root2));
        System.out.println(new _17_二叉树遍历().inorderTraversal(root3));
    }

    /**
     * 修正为平衡二叉树
     * @param root
     */
    public static void fix2BBST(HeroNode2 root){
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);

        if(Math.abs(leftHeight - rightHeight) > 1){
            /** 双旋转 */
            /**
             * 左右旋转（LR）
             * 1. 如果它的左子树的右子树高度大于左子树的左子树的高度
             * 2. 先对当前这个节点的左节点进行左旋转
             * 3. 在对当前节点进行右旋转的操作即可*
             */
            int leftLeftHeight = root.left == null ? 0 : getTreeHeight(root.left.left);
            int leftRightHeight = root.left == null ? 0 : getTreeHeight(root.left.right);
            if(leftLeftHeight < leftRightHeight) {
                leftRotate(root.left);
                rightRotate(root);
                return;
            }
            /**
             * 右左旋转（RL）
             * 1. 如果它的右子树的左子树高度大于右子树的左子树的高度
             * 2. 先对当前这个节点的右节点进行右旋转
             * 3. 在对当前节点进行左旋转的操作即可*
             */
            int rightLeftHeight = root.right == null ? 0 : getTreeHeight(root.right.left);
            int rightRightHeight = root.right == null ? 0 : getTreeHeight(root.right.right);
            if(rightLeftHeight > rightRightHeight) {
                rightRotate(root.right);
                leftRotate(root);
                return;
            }

            if(rightHeight - leftHeight > 1){
                /** 左旋转 */
                //      6
                //   4     7
                //  3 5      8
                leftRotate(root);
                return;
            }

            if(leftHeight - rightHeight > 1){
                /** 右旋转 */
                //      8
                //   7     10
                //  6     9  12
                rightRotate(root);
                return;
            }
        }
    }

    /**
     * 左旋转
     * @param root
     */
    public static void leftRotate(HeroNode2 root) {
        //创建新的节点，以当前根节点的值
        HeroNode2 newRoot = new HeroNode2(root.no);
        //把新的节点的左子树设置成当前节点的左子树
        newRoot.left = root.left;
        //把新的节点的右子树设置成带你过去节点的右子树的左子树
        newRoot.right = root.right.left;
        //把当前节点的值替换成右子节点的值
        root.no = root.right.no;
        //把当前节点的右子树设置成当前节点右子树的右子树
        root.right = root.right.right;
        //把当前节点的左子树(左子节点)设置成新的节点
        root.left = newRoot;
    }

    /**
     * 右旋转
     * @param root
     */
    public static void rightRotate(HeroNode2 root) {
        HeroNode2 newRoot = new HeroNode2(root.no);
        newRoot.right = root.right;
        newRoot.left = root.left.right;
        root.no = root.left.no;
        root.left = root.left.left;
        root.right = newRoot;
    }

    public static int getTreeHeight(HeroNode2 root){
        if (root == null) return 0;
        int leftHeight = getTreeHeight(root.left);
        int rightHeight = getTreeHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}